package post.post.domain.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import post.post.domain.domain.entity.PostEntity;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity,Long> { //왼쪽에 엔티티, 오른쪽에 PK 자료형
    @Query("select p from PostEntity p where p.contents = :contents")
    List<PostEntity> findByContents(String contents);
}

// * 역할:
// * - `PostEntity`에 대한 CRUD 및 조회를 담당하는 Spring Data JPA 리포지토리 인터페이스.
// * - `JpaRepository`를 상속하여 기본적인 save, findAll, findById, deleteById 등의 메서드를 자동 제공.
// * - 추가로 `contents`로 조회하는 커스텀 쿼리 메서드 `findByContents`를 정의함.
// */