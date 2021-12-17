package dev.yjeong.user.dto;

import dev.yjeong.user.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpResponse {

    private final String email;

    @Builder
    public SignUpResponse(String email) {
        this.email = email;
    }

    public static SignUpResponse of(User user) {
        return SignUpResponse.builder()
                .email(user.getEmail())
                .build();
    }

}
