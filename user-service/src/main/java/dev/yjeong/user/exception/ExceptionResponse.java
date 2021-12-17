package dev.yjeong.user.exception;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ExceptionResponse {

    private String message;

}
