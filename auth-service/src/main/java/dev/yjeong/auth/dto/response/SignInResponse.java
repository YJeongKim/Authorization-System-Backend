package dev.yjeong.auth.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignInResponse {

    private final Long id;

    private final String nickname;

    @Builder
    public SignInResponse(Long id, String nickname) {
        this.id = id;
        this.nickname = nickname;
    }

}
