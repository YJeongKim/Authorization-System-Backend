package dev.yjeong.user.dto.response;

import dev.yjeong.user.domain.User;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResponse {

    private final Long id;

    private final String nickname;

    @Builder
    private SignInResponse(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

    public static SignInResponse of(User user) {
        return SignInResponse.builder()
                .id(user.getId())
                .nickname(user.getNickname())
                .build();
    }

}
