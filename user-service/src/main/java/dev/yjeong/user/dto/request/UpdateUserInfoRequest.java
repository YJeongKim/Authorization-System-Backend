package dev.yjeong.user.dto.request;

import dev.yjeong.user.exception.NotValidExceptionMessage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@RequiredArgsConstructor
public class UpdateUserInfoRequest {

    private final Long id;

    @NotBlank(message = NotValidExceptionMessage.NOR_BLANK_NICKNAME)
    private final String nickname;

}
