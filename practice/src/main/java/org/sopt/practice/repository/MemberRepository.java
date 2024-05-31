package org.sopt.practice.repository;

import org.sopt.practice.entity.Member;
import org.sopt.practice.entity.Part;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Boolean existsByNameAndPartAndAge(String name, Part part, int age);

    Optional<Member> findByNameAndPartAndAge(String name, Part part, int age);
}
