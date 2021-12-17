package dev.yjeong.user.exception;

import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ExceptionResponse> handleBaseException(BaseException exception) {
        printLog(exception);
        return ResponseEntity
                .status(exception.getExceptionType().getHttpStatus())
                .body(ExceptionResponse.builder().message(exception.getExceptionType().getMessage()).build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> handleRuntimeException(RuntimeException exception) {
        printLog(exception);
        exception.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .body(ExceptionResponse.builder().message(exception.getMessage()).build());
    }

    private void printLog(Exception exception) {
        log.error(LocalDateTime.now() + " - " + exception);
    }

}
