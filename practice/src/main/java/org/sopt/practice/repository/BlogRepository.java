package org.sopt.practice.repository;

import org.sopt.practice.entity.Blog;
import org.sopt.practice.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Long> {
    boolean existsByIdAndMember(Long blogId, Member member);
}
