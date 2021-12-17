package dev.yjeong.user.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class SignInRequest {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

}
