package org.sopt.practice.service.dto.response;

import org.sopt.practice.entity.Blog;
import org.sopt.practice.entity.Member;

public record BlogResponse(Long id, MemberInformationResponse memberInformationResponse, String title, String description) {
    public static BlogResponse of(Member member, Blog blog) {
        return new BlogResponse(blog.getId(), MemberInformationResponse.of(member), blog.getTitle(), blog.getDescription());
    }

}
