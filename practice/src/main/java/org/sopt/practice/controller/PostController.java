package org.sopt.practice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.SuccessMessage;
import org.sopt.practice.common.dto.SuccessStatusResponse;
import org.sopt.practice.controller.headers.Headers;
import org.sopt.practice.service.PostService;
import org.sopt.practice.service.dto.request.CreatePostRequest;
import org.sopt.practice.service.dto.response.PostGetResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequestMapping("/api/v1")
@RequiredArgsConstructor
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping("/blogs/{blogId}/posts")
    public ResponseEntity<SuccessStatusResponse<Void>> createPost(@RequestBody @Valid final CreatePostRequest createPostRequest,
                                                                  @PathVariable final Long blogId,
                                                                  @RequestHeader(name = Headers.MEMBER_ID) final Long memberId) {
        return ResponseEntity.created(URI.create(postService.createPost(createPostRequest, blogId, memberId))).build();
    }

    @GetMapping("/posts/{postId}")
    public ResponseEntity<SuccessStatusResponse<PostGetResponse>> getPost(@PathVariable final Long postId) {
        return ResponseEntity.ok(SuccessStatusResponse.of(SuccessMessage.POST_GET_SUCCESS, postService.getPost(postId)));
    }

}
