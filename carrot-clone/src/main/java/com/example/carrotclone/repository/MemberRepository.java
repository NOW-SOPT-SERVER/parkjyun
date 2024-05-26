package com.example.carrotclone.repository;

import com.example.carrotclone.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
