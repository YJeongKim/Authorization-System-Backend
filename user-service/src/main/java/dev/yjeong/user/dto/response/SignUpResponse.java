package dev.yjeong.user.dto.response;

import dev.yjeong.user.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUpResponse {

    private final String email;

    @Builder
    private SignUpResponse(String email) {
        this.email = email;
    }

    public static SignUpResponse of(User user) {
        return SignUpResponse.builder()
                .email(user.getEmail())
                .build();
    }

}
