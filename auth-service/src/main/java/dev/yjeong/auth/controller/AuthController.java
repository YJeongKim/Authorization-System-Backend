package dev.yjeong.auth.controller;

import dev.yjeong.auth.dto.response.SignInResponse;
import dev.yjeong.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/token")
    public ResponseEntity<SignInResponse> issue() {
        SignInResponse signInResponse = authService.issueToken();
        return ResponseEntity.ok().body(signInResponse);
    }

}
