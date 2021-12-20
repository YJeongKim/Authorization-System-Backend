package dev.yjeong.user.exception;

public class InternalServerException extends BaseException {

    public InternalServerException(ExceptionType exceptionType) {
        super(exceptionType);
    }

}
