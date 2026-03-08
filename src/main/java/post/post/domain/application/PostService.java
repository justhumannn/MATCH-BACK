package post.post.domain.application;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import post.post.domain.domain.entity.PostEntity;
import post.post.domain.domain.repository.PostRepository;
import post.post.domain.presentation.dto.req.PostAddRequestDto;
import post.post.domain.presentation.dto.req.PostUpdateRequestDto;
import post.post.domain.presentation.dto.res.PostResponseDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // @Service: 비즈니스 로직을 수행하는 서비스 빈을 선언
    // @RequiredArgsConstructor: final 또는 @NonNull 필드를 파라미터로 받는 생성자를 Lombok이 생성
    //   -> `postRepository`가 생성자를 통해 주입된다.
    /*
     * 역할:
     * - 비즈니스 로직을 담는 서비스 계층 클래스
     * - 컨트롤러와 리포지토리 사이에서 데이터 변환, 트랜잭션 관리, 예외 흐름 제어를 담당
     */

    public List<PostResponseDto> getPosts() {
        List<PostEntity> posts = postRepository.findAll();
        return posts.stream().map(
                post -> PostResponseDto.builder()
                        .title(post.getTitle())
                        .writer(post.getWriter())
                        .contents(post.getContents())
                        .build()
        ).toList();
    }

    public PostResponseDto getPostById(Long postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return PostResponseDto.from(post);
    }

    @Transactional
    public void save(PostAddRequestDto postAddRequestDto) {
        // DTO -> Entity 변환 후 저장
        postRepository.save(PostEntity.saveBuilder()
                        .title(postAddRequestDto.getTitle())
                        .contents(postAddRequestDto.getContents())
                        .writer(postAddRequestDto.getWriter())
                .build());
    }

    @Transactional
    public void update(PostUpdateRequestDto postUpdateRequestDto) {
        PostEntity postEntity = postRepository.findById(
                postUpdateRequestDto.getId())
                .orElseThrow(IllegalArgumentException::new);
        // 엔티티의 상태 변경 후 저장
        postEntity.changePostEntity(postUpdateRequestDto.getTitle(), postUpdateRequestDto.getWriter(), postUpdateRequestDto.getContents());
        postRepository.save(postEntity);
    }

    @Transactional
    public void delete(Long postId) {
        postRepository.deleteById(postId);
    }
}



