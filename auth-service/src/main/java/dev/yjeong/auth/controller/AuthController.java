package dev.yjeong.auth.controller;

import dev.yjeong.auth.controller.feignclient.UserFeignClient;
import dev.yjeong.auth.dto.request.SignInRequest;
import dev.yjeong.auth.dto.response.SignInResponse;
import dev.yjeong.auth.dto.response.TokenResponse;
import dev.yjeong.auth.service.AuthService;
import dev.yjeong.auth.util.CookieProvider;
import dev.yjeong.auth.util.JwtProvider;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    private final CookieProvider cookieProvider;

    private final UserFeignClient userFeignClient;

    @GetMapping("/token")
    public ResponseEntity<SignInResponse> issue(@RequestBody @Valid SignInRequest signInRequest,
                                                HttpServletResponse httpServletResponse) {
        SignInResponse signInResponse = userFeignClient.signIn(signInRequest);
        TokenResponse tokenResponse = authService.issueToken(signInResponse);

        Cookie accessToken = cookieProvider.createCookie(JwtProvider.ACCESS_TOKEN, tokenResponse.getAccessToken());
        Cookie refreshToken = cookieProvider.createCookie(JwtProvider.REFRESH_TOKEN, tokenResponse.getRefreshToken());
        httpServletResponse.addCookie(accessToken);
        httpServletResponse.addCookie(refreshToken);

        return ResponseEntity.ok().body(signInResponse);
    }

}
