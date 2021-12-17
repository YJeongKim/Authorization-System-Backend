package dev.yjeong.user.dto.request;

import dev.yjeong.user.domain.User;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class SignUpRequest {

    @Email
    @NotBlank
    private final String email;

    @NotBlank
    private final String password;

    @NotBlank
    private final String name;

    @NotBlank
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
