package post.post.domain.presentation.dto.req;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostAddRequestDto {
    @NotNull(message = "제목은 NULL이 되서는 안 됩니다.")
    // @NotNull: Jakarta Bean Validation 어노테이션, 값이 null이면 검증 실패
    private String title; // 추가 요청 시 반드시 필요한 제목
    @NotNull(message = "내용은 NULL이 되서는 안 됩니다.")
    private String contents; // 추가 요청 시 본문
    @NotNull(message = "작가는 NULL이 되서는 안 됩니다.")
    private String writer; // 작성자
}
