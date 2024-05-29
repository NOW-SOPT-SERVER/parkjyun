package org.sopt.practice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.auth.PrincipalHandler;
import org.sopt.practice.common.dto.SuccessMessage;
import org.sopt.practice.common.dto.SuccessStatusResponse;
import org.sopt.practice.controller.headers.Headers;
import org.sopt.practice.service.BlogService;
import org.sopt.practice.service.dto.BlogCreateRequest;
import org.sopt.practice.service.dto.BlogTitleUpdateRequest;
import org.sopt.practice.service.dto.response.BlogResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class BlogController {

    private final BlogService blogService;
    private final PrincipalHandler principalHandler;

    @GetMapping("/blogs/{blogId}")
    ResponseEntity<SuccessStatusResponse<BlogResponse>> getBlog(@PathVariable final Long blogId) {
        return ResponseEntity.ok(SuccessStatusResponse.of(SuccessMessage.BLOG_GET_SUCCESS, blogService.getBlog(blogId)));
    }

    @PostMapping("/blogs")
    ResponseEntity<SuccessStatusResponse> createBlog(@RequestBody final BlogCreateRequest request) {
        Long id = principalHandler.getUserIdFromPrincipal();
        System.out.println(id);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", blogService.create(id, request))
                .body(SuccessStatusResponse.of(SuccessMessage.BLOG_CREATE_SUCCESS));
    }

    @PatchMapping("/blogs/{blogId}/title")
    public ResponseEntity updateBlogTitle(@PathVariable Long blogId, @RequestBody @Valid BlogTitleUpdateRequest request) {
        blogService.updateTitle(blogId, request);
        return ResponseEntity.noContent().build();
    }
}
