package dev.yjeong.auth.service;

import dev.yjeong.auth.config.jwt.JwtProvider;
import dev.yjeong.auth.dto.response.SignInResponse;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final JwtProvider jwtProvider;

    public SignInResponse issueToken() {
        SignInResponse response = new SignInResponse(1L, "닉네임"); // TODO: User Server 에서 받아오기
        String accessToken = jwtProvider.createAccessToken(response.getId().toString());
        String refreshToken = jwtProvider.createRefreshToken(response.getId().toString());
        System.out.println("access: " + accessToken + ", refresh: " + refreshToken); // TODO: Cookie, Cache 에 저장
        return response;
    }

}
