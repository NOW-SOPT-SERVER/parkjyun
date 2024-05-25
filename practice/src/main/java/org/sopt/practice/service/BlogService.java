package org.sopt.practice.service;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.common.dto.SuccessStatusResponse;
import org.sopt.practice.entity.Blog;
import org.sopt.practice.entity.Member;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.BlogRepository;
import org.sopt.practice.service.dto.BlogCreateRequest;
import org.sopt.practice.service.dto.BlogTitleUpdateRequest;
import org.sopt.practice.service.dto.response.BlogResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BlogService {

    private final MemberService memberService;
    private final BlogRepository blogRepository;

    @Transactional
    public String create(Long memberId, BlogCreateRequest request) {
        Member member = memberService.findById(memberId);
        Blog blog = blogRepository.save(Blog.create(member, request));
        return blog.getId().toString();
    }

    @Transactional
    public void updateTitle(Long blogId, BlogTitleUpdateRequest request) {
        Blog blog = findById(blogId);
        blog.updateTitle(request.title());
    }

    protected Blog findById(Long blogId) {
        return blogRepository.findById(blogId).orElseThrow(() -> new NotFoundException(ErrorMessage.BLOG_NOT_FOUND));
    }

    public BlogResponse getBlog(Long blogId) {
        Blog blog = findById(blogId);
        return BlogResponse.of(blog.getMember(), blog);
    }

    protected boolean isBlogOwner(Long blogId, Long memberId) {
        Member member = memberService.findById(memberId);
        return blogRepository.existsByIdAndMember(blogId, member);
    }
}
