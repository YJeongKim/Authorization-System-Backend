package dev.yjeong.user.exception;

public class BadRequestException extends BaseException {

    public BadRequestException(ExceptionType exceptionType) {
        super(exceptionType);
    }

}
