package post.post.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ErrorResponse.builder()
                .code(HttpStatus.BAD_REQUEST.name())
                .message(
                        e.getBindingResult().getFieldError() != null
                                ? e.getBindingResult().getFieldError().getDefaultMessage()
                                : "Validation error occurred"
                )
                .build();
    }
}