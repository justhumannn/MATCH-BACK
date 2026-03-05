package post.post.domain.presentation.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import post.post.domain.application.PostService;
import post.post.domain.presentation.dto.req.PostAddRequestDto;
import post.post.domain.presentation.dto.req.PostUpdateRequestDto;
import post.post.domain.presentation.dto.res.PostResponseDto;

import java.util.List;
import java.util.Optional;

// @RestController: @Controller + @ResponseBody를 합친 어노테이션
// - 컨트롤러의 모든 메서드가 JSON 등을 응답 바디로 반환하도록 함
// @RequestMapping("/post"): 이 컨트롤러의 기본 URL 경로를 지정
// @RequiredArgsConstructor: final 필드를 인자로 받는 생성자를 Lombok이 생성하여 의존성 주입을 간편하게 함
@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /*
     * 역할:
     * - HTTP 요청을 받아 서비스 계층을 호출하고 응답을 반환하는 프레젠테이션 계층(컨트롤러)
     * - 각 엔드포인트는 CRUD 동작을 제공
     */

    @GetMapping("/")
    public List<PostResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{postId}")
    public PostResponseDto getPost(@PathVariable Long postId) {
        return postService.getPostById(postId);
    }

    @PostMapping("/")
    public void addPost(@RequestBody @Valid PostAddRequestDto postAddRequestDto) {
        postService.save(postAddRequestDto);
    }

    @PatchMapping("/")
    public void updatePost(@RequestBody @Valid PostUpdateRequestDto postUpdateRequestDto) {
        postService.update(postUpdateRequestDto);
    }

    @DeleteMapping("/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.delete(postId);
    }
}