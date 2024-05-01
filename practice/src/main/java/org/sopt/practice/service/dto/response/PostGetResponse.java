package org.sopt.practice.service.dto.response;

import org.sopt.practice.entity.Post;

public record PostGetResponse(Long id, String name, String content) {
    public static PostGetResponse of(Post post) {
        return new PostGetResponse(post.getId(), post.getName(), post.getContent());
    }
}
