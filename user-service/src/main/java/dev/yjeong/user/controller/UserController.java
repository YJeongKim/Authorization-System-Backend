package dev.yjeong.user.controller;

import dev.yjeong.user.dto.request.SignInRequest;
import dev.yjeong.user.dto.response.SignInResponse;
import dev.yjeong.user.dto.request.SignUpRequest;
import dev.yjeong.user.dto.response.SignUpResponse;
import dev.yjeong.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = userService.signUpUser(signUpRequest);
        return ResponseEntity.ok().body(signUpResponse);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResponse signInResponse = userService.signInUser(signInRequest);
        return ResponseEntity.ok().body(signInResponse); // TODO: auth-service 호출 후 token 발급
    }

}
