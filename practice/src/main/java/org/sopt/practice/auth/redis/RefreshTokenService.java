package org.sopt.practice.auth.redis;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.auth.redis.domain.Token;
import org.sopt.practice.auth.redis.repository.TokenRepository;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.exception.NotFoundException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final TokenRepository tokenRepository;

    @Transactional
    public void saveRefreshToken(final Long userId, final String refreshToken) {
        tokenRepository.save(Token.of(userId, refreshToken));
    }

    public Long findUserIdByRefreshToken(final String refreshToken) {
        Token token = tokenRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.REFRESHTOKEN_NOT_FOUND));
        return token.getId();
    }

    @Transactional
    public void deleteRefreshToken(final Long userId) {
        Token token = tokenRepository.findById(userId).orElseThrow(() -> new NotFoundException(ErrorMessage.REFRESHTOKEN_NOT_FOUND));
        tokenRepository.delete(token);
    }
}
