package org.sopt.practice.auth.redis.domain;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash(value = "", timeToLive = 24 * 60 * 60 * 1000L * 1)
@AllArgsConstructor
@Getter
@Builder
public class Token {

    @Id
    private Long id;

    @Indexed//해당 어노테이션 사용시 이 값으로 객ㄱ체 값을 찾을 수 있음
    private String refreshToken;

    public static Token of(final Long id, final String refreshToken) {
        return Token.builder()
                .id(id)
                .refreshToken(refreshToken)
                .build();
    }
}
