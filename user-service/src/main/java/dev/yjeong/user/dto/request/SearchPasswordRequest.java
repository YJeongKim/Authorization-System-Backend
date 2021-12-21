package dev.yjeong.user.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;

import dev.yjeong.user.exception.NotValidExceptionMessage;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class SearchPasswordRequest {

    @Email(message = NotValidExceptionMessage.INVALID_FORMAT_EMAIL)
    @NotBlank(message = NotValidExceptionMessage.NOT_BLANK_EMAIL)
    private final String email;

    @JsonCreator
    public SearchPasswordRequest(String email) {
        this.email = email;
    }

}
