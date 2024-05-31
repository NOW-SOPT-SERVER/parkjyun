package org.sopt.practice.service.dto.response;

import org.sopt.practice.entity.Member;
import org.sopt.practice.entity.Part;

public record MemberFindDto(Long id, String name, Part part, int age) {
    public static MemberFindDto of(Member member) {
        return new MemberFindDto(member.getId(), member.getName(), member.getPart(), member.getAge());
    }
}
