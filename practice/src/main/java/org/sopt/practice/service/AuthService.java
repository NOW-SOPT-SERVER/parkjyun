package org.sopt.practice.service;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.auth.UserAuthentication;
import org.sopt.practice.auth.redis.domain.Token;
import org.sopt.practice.auth.redis.repository.TokenRepository;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.service.jwt.JwtTokenProvider;
import org.sopt.practice.service.jwt.TokenType;
import org.sopt.practice.entity.Member;
import org.sopt.practice.exception.BadRequestException;
import org.sopt.practice.service.dto.request.MemberCreateDto;
import org.sopt.practice.service.dto.response.TokenResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String BEARER_PREFIX = "Bearer ";

    private final MemberHelper memberHelper;
    private final JwtTokenProvider jwtTokenProvider;
    private final TokenRepository tokenRepository;


    @Transactional
    public TokenResponse login(final MemberCreateDto memberCreate) {
        final Member member = loadOrCreateMember(memberCreate);
        TokenResponse tokenResponse = createTokenResponse(member.getId());
        updateRefreshToken(member.getId(), tokenResponse.refreshToken());
        return tokenResponse;
    }

    @Transactional
    public TokenResponse reissue(String token) {
        String refreshToken = getToken(token);
        Claims claims = jwtTokenProvider.getBody(refreshToken);
        if (isAccessToken(claims)) {
            throw new BadRequestException(ErrorMessage.INVALID_JWT_TOKEN);
        }
        Long memberId = claims.get(jwtTokenProvider.USER_ID, Long.class);

        Token savedRefreshToken = tokenRepository.findById(memberId).orElseThrow(() -> new NotFoundException(ErrorMessage.REFRESHTOKEN_NOT_FOUND));
        if (!isSameRefreshToken(refreshToken, savedRefreshToken)) {
            throw new BadRequestException(ErrorMessage.INVALID_JWT_TOKEN);
        }

        TokenResponse tokenResponse = createTokenResponse(memberId);
        updateRefreshToken(memberId, tokenResponse.refreshToken());
        return tokenResponse;
    }

    private boolean isSameRefreshToken(final String refreshToken, final Token savedRefreshToken) {
        return savedRefreshToken.getRefreshToken().equals(refreshToken);
    }

    private boolean isAccessToken(Claims claims) {
        return claims.get(jwtTokenProvider.TOKEN_TYPE).equals(TokenType.ACCESS.getValue());
    }

    private TokenResponse createTokenResponse(final Long memberId) {
        final String accessToken = jwtTokenProvider.issueAccessToken(UserAuthentication.createUserAuthentication(memberId));
        final String refreshToken = jwtTokenProvider.issueRefreshToken(UserAuthentication.createUserAuthentication(memberId));
        return TokenResponse.of(accessToken, refreshToken, memberId);
    }

    private Member loadOrCreateMember(final MemberCreateDto memberCreate) {

        Member member;

        if (!isExistMember(memberCreate)) {
            member = memberHelper.saveMember(Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age()));
        } else {
            member = memberHelper.findByNameAndPartAndAge(memberCreate.name(), memberCreate.part(), memberCreate.age());
        }
        return member;
    }

    private boolean isExistMember(final MemberCreateDto memberCreateDto) {//이름, 파트, 나이가 같은 회원이 존재하는지 확인
        return memberHelper.isExistMember(memberCreateDto);
    }

    private String getToken(String token) {
        if (token.startsWith(BEARER_PREFIX)) {
            return token.substring(BEARER_PREFIX.length());
        } else {
            return token;
        }
    }

    private void updateRefreshToken(final Long memberId, final String refreshToken) {
        tokenRepository.deleteById(memberId);
        tokenRepository.save(Token.of(memberId, refreshToken));
    }
}
