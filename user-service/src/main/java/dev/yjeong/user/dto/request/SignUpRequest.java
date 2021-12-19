package dev.yjeong.user.dto.request;

import dev.yjeong.user.domain.User;

import dev.yjeong.user.exception.NotValidExceptionMessage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class SignUpRequest {

    @Email(message = NotValidExceptionMessage.INVALID_FORMAT_EMAIL)
    @NotBlank(message = NotValidExceptionMessage.NOT_BLANK_EMAIL)
    private final String email;

    @NotBlank(message = NotValidExceptionMessage.NOT_BLANK_PASSWORD)
    private final String password;

    @NotBlank(message = NotValidExceptionMessage.NOT_BLANK_NAME)
    private final String name;

    @NotBlank(message = NotValidExceptionMessage.NOR_BLANK_NICKNAME)
    private final String nickname;

    public User toEntity(String encryptedPassword) {
        return User.builder()
                .email(email)
                .password(encryptedPassword)
                .name(name)
                .nickname(nickname)
                .build();
    }

}
