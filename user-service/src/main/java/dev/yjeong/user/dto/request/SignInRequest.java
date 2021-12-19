package dev.yjeong.user.dto.request;

import dev.yjeong.user.exception.NotValidExceptionMessage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class SignInRequest {

    @Email(message = NotValidExceptionMessage.INVALID_FORMAT_EMAIL)
    @NotBlank(message = NotValidExceptionMessage.NOT_BLANK_EMAIL)
    private final String email;

    @NotBlank(message = NotValidExceptionMessage.NOT_BLANK_PASSWORD)
    private final String password;

}
