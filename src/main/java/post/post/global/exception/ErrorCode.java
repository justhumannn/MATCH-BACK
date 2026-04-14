package post.post.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500", "서버 에러가 발생했습니다."),
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "COMMON_400", "잘못된 입력값입니다."),
    NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON_404", "해당 리소스를 찾을 수 없습니다."),

    // User
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_404", "해당 유저를 찾을 수 없습니다."),
    INSUFFICIENT_BALANCE(HttpStatus.BAD_REQUEST, "USER_400", "잔액이 부족합니다."),

    // Betting
    BETTING_NOT_FOUND(HttpStatus.NOT_FOUND, "BETTING_404", "해당 배팅을 찾을 수 없습니다."),
    BETTING_CARD_NOT_FOUND(HttpStatus.NOT_FOUND, "BETTING_404", "해당 배팅 카드를 찾을 수 없습니다."),
    BETTING_ALREADY_ENDED(HttpStatus.BAD_REQUEST, "BETTING_400", "이미 종료된 배팅입니다."),
    BETTING_ALREADY_RESOLVED(HttpStatus.BAD_REQUEST, "BETTING_400", "이미 결과가 처리된 배팅입니다."),

    // Security
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "AUTH_401", "인증되지 않은 사용자입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "AUTH_403", "권한이 없습니다."),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "AUTH_401", "유효하지 않은 토큰입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
