package dev.yjeong.user.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Getter;

@Getter
public class UserInfoRequest {

    private final Long id;

    @JsonCreator
    public UserInfoRequest(Long id) {
        this.id = id;
    }

}
