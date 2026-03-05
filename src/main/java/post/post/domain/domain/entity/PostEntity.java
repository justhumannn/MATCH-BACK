package post.post.domain.domain.entity;

import jakarta.persistence.*;
import lombok.*;

// @Entity: JPA 엔티티임을 나타냄 (해당 클래스는 DB 테이블에 매핑됨)
// @Getter: Lombok 어노테이션, 모든 필드에 대한 getter 메서드를 자동 생성
// @NoArgsConstructor(access = AccessLevel.PROTECTED): 기본 생성자를 생성하되 접근 제한을 protected로 함
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // DB에서 자동으로 생성되는 고유 식별자
    // @Column: 필드를 DB 컬럼에 매핑, nullable=false 등 제약을 설정할 수 있음
    @Column(name = "title", nullable = false)
    private String title; // 게시글 제목

    @Column(name = "writer", nullable = false)
    private String writer; // 작성자

    @Column(name = "contents", nullable = false)
    private String contents; // 본문 내용

    // @Builder: Lombok 빌더 패턴 자동 생성. builderMethodName으로 커스텀 빌더 이름 지정
    @Builder(builderMethodName = "saveBuilder")
    public PostEntity(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }

    // 엔티티의 필드를 변경할 때 사용하는 유틸리티 메서드
    // - 영속성 컨텍스트 내에서는 변경 감지(dirty checking)를 통해 DB에 반영된다.
    public void changePostEntity(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }
}

