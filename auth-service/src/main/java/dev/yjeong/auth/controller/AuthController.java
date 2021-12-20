package dev.yjeong.auth.controller;

import dev.yjeong.auth.dto.response.SignInResponse;
import dev.yjeong.auth.dto.response.TokenResponse;
import dev.yjeong.auth.service.AuthService;
import dev.yjeong.auth.util.CookieProvider;
import dev.yjeong.auth.util.JwtProvider;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    private final CookieProvider cookieProvider;

    @GetMapping("/token")
    public ResponseEntity<SignInResponse> issue(HttpServletResponse httpServletResponse) {
        SignInResponse signInResponse = new SignInResponse(1L, "닉네임"); // TODO: User Server 에서 받아오기
        TokenResponse tokenResponse = authService.issueToken(signInResponse);

        Cookie accessToken = cookieProvider.createCookie(JwtProvider.ACCESS_TOKEN, tokenResponse.getAccessToken());
        Cookie refreshToken = cookieProvider.createCookie(JwtProvider.REFRESH_TOKEN, tokenResponse.getRefreshToken());
        httpServletResponse.addCookie(accessToken);
        httpServletResponse.addCookie(refreshToken);

        return ResponseEntity.ok().body(signInResponse);
    }

}
