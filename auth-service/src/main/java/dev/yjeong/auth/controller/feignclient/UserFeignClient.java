package dev.yjeong.auth.controller.feignclient;

import dev.yjeong.auth.dto.request.SignInRequest;
import dev.yjeong.auth.dto.response.SignInResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(name = "user-service")
public interface UserFeignClient {

    @PostMapping("/api/users/sign-in")
    SignInResponse signIn(@RequestBody @Valid SignInRequest signInRequest);

}
