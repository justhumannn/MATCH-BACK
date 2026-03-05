package post.post.global.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorResponse {
    // Lombok의 @Getter/@Builder는 아래 필드에 대해 getter와 빌더를 자동 생성
    // `code`: 에러를 식별하는 코드 또는 HTTP 상태명
    private String code;    // 예: BAD_REQUEST, INTERNAL_SERVER_ERROR
    // `message`: 사용자에게 보여줄 에러 상세 메시지
    private String message; // 사용자에게 반환할 에러 메시지
}
