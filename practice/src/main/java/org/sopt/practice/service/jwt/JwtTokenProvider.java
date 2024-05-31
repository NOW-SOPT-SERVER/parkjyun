package org.sopt.practice.service.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.exception.UnauthorizedException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider implements InitializingBean {

    public static final String USER_ID = "userId";
    public static final String TOKEN_TYPE = "tokenType";

    private static final Long ACCESS_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L * 14;
    private static final Long REFRESH_TOKEN_EXPIRATION_TIME = 24 * 60 * 60 * 1000L * 1;

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    private Key key;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(JWT_SECRET);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public String issueAccessToken(final Authentication authentication) {
        return createToken(authentication, ACCESS_TOKEN_EXPIRATION_TIME, TokenType.ACCESS);
    }

    public String issueRefreshToken(final Authentication authentication) {
        return createToken(authentication, REFRESH_TOKEN_EXPIRATION_TIME, TokenType.REFRESH);
    }

    public Long getUserFromJwt(String token) {
        Claims claims = getBody(token);
        return Long.valueOf(claims.get(USER_ID).toString());
    }


    public JwtValidationType validateToken(String token) {
        try {
            final Claims claims = getBody(token);
            if (claims.get(TOKEN_TYPE).equals(TokenType.ACCESS.getValue())) {
                return JwtValidationType.VALID_ACCESS;
            } else if (claims.get(TOKEN_TYPE).equals(TokenType.REFRESH.getValue())) {
                return JwtValidationType.VALID_REFRESH;
            }
            throw new UnauthorizedException(ErrorMessage.INVALID_JWT_SIGNATURE);
        } catch (MalformedJwtException ex) {
            throw new UnauthorizedException(ErrorMessage.INVALID_JWT_SIGNATURE);
        } catch (ExpiredJwtException ex) {
            throw new UnauthorizedException(ErrorMessage.EXPIRED_JWT_TOKEN);
        } catch (UnsupportedJwtException ex) {
            throw new UnauthorizedException(ErrorMessage.UNSUPPORTED_JWT_TOKEN);
        } catch (IllegalArgumentException ex) {
            throw new UnauthorizedException(ErrorMessage.EMPTY_JWT);
        }
    }

    public Claims getBody(final String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private String createToken(Authentication authentication, Long tokenExpirationTime, TokenType tokenType) {
        final Date now = new Date();
        final Claims claims = Jwts.claims()
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + tokenExpirationTime));
        claims.put(USER_ID, authentication.getPrincipal());

        switch (tokenType) {
            case ACCESS:
                claims.put(TOKEN_TYPE, TokenType.ACCESS.getValue());
                break;
            case REFRESH:
                claims.put(TOKEN_TYPE, TokenType.REFRESH.getValue());
                break;
        }

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }
}
