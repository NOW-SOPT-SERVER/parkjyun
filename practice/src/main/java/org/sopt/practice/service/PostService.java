package org.sopt.practice.service;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.entity.Blog;
import org.sopt.practice.entity.Post;
import org.sopt.practice.exception.ForbiddenException;
import org.sopt.practice.repository.PostRespository;
import org.sopt.practice.service.dto.request.CreatePostRequest;
import org.sopt.practice.service.dto.response.PostGetResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PostService {

    private final PostRespository postRespository;
    private final BlogService blogService;

    @Transactional
    public String createPost(final CreatePostRequest createPostRequest, final Long blogId, final Long memberId) {
        Blog blog = blogService.findById(blogId);
        if (!blogService.isBlogOwner(blogId, memberId)) throw new ForbiddenException(ErrorMessage.MEMBER_FORBIDDEN);
        return postRespository.save(Post.of(createPostRequest, blog)).getId().toString();
    }

    public PostGetResponse getPost(final Long postId) {
        Post post = postRespository.findById(postId).orElseThrow(() -> new ForbiddenException(ErrorMessage.POST_NOT_FOUND));
        return PostGetResponse.of(post);
    }
}
