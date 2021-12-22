package dev.yjeong.user.controller;

import dev.yjeong.user.dto.request.*;
import dev.yjeong.user.dto.response.SignInResponse;
import dev.yjeong.user.dto.response.SignUpResponse;
import dev.yjeong.user.dto.response.UserInfoResponse;
import dev.yjeong.user.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/api/users")
@RestController
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserInfoResponse> lookUpUserInfo(@RequestBody UserInfoRequest userInfoRequest) {
        // TODO: auth-service token 검증 및 id 받아오기
        UserInfoResponse userInfoResponse = userService.lookUpInfo(userInfoRequest);
        return ResponseEntity.ok().body(userInfoResponse);
    }

    @PutMapping()
    public ResponseEntity<UserInfoResponse> modifyUserInfo(@RequestBody @Valid UpdateUserInfoRequest userInfoRequest) {
        // TODO: auth-service token 검증 및 id 받아오기
        UserInfoResponse userInfoResponse = userService.modifyInfo(userInfoRequest);
        return ResponseEntity.ok().body(userInfoResponse);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<SignUpResponse> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
        SignUpResponse signUpResponse = userService.signUpUser(signUpRequest);
        return ResponseEntity.ok().body(signUpResponse);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<SignInResponse> signIn(@RequestBody @Valid SignInRequest signInRequest) {
        SignInResponse signInResponse = userService.signInUser(signInRequest);
        return ResponseEntity.ok().body(signInResponse);
    }

    @PostMapping("/password")
    public ResponseEntity<Void> searchPassword(@RequestBody @Valid SearchPasswordRequest passwordRequest) {
        userService.searchPassword(passwordRequest);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/password")
    public ResponseEntity<Void> changePassword(@RequestBody @Valid UpdatePasswordRequest passwordRequest) {
        userService.changePassword(passwordRequest);
        return ResponseEntity.ok().build();
    }

}
