package dev.yjeong.user.dto.response;

import dev.yjeong.user.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UserInfoResponse {

    private final String email;

    private final String name;

    private final String nickname;

    @Builder
    private UserInfoResponse(String email, String name, String nickname) {
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }

    public static UserInfoResponse of(User user) {
        return UserInfoResponse.builder()
                .email(user.getEmail())
                .name(user.getName())
                .nickname(user.getNickname())
                .build();
    }

}
