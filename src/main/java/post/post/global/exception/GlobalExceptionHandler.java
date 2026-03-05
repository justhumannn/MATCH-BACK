package post.post.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // 전역 예외 처리기
    // - 컨트롤러에서 발생한 `MethodArgumentNotValidException` (유효성 검사 실패) 를 잡아
    //   `ErrorResponse` 형태로 클라이언트에 반환한다.
    // - 필요하면 다른 예외 타입에 대한 핸들러를 추가하여 공통 응답 포맷을 유지할 수 있다.

    // @ExceptionHandler: 지정한 예외 타입을 처리할 메서드를 설정
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