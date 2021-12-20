package dev.yjeong.auth.service;

import dev.yjeong.auth.dto.response.SignInResponse;
import dev.yjeong.auth.dto.response.TokenResponse;
import dev.yjeong.auth.util.JwtProvider;
import dev.yjeong.auth.util.RedisManager;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    private final RedisManager redisManager;

    public TokenResponse issueToken(SignInResponse signInResponse) {
        Long userId = signInResponse.getId();
        String accessToken = jwtProvider.createAccessToken(userId.toString());
        String refreshToken = jwtProvider.createRefreshToken(userId.toString());

        redisManager.setValue(
                generateKeyName(userId, JwtProvider.ACCESS_TOKEN), accessToken, JwtProvider.ACCESS_TOKEN_VALIDITY);
        redisManager.setValue(
                generateKeyName(userId, JwtProvider.REFRESH_TOKEN), refreshToken, JwtProvider.REFRESH_TOKEN_VALIDITY);
        return TokenResponse.of(accessToken, refreshToken);
    }

    private String generateKeyName(long userId, String token) {
        return userId + "-" + token;
    }

}
