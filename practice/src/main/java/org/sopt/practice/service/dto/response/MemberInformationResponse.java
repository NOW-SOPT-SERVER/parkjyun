package org.sopt.practice.service.dto.response;

import org.sopt.practice.entity.Member;
import org.sopt.practice.entity.Part;

public record MemberInformationResponse(Long id, Part part, String name, int age) {
    public static MemberInformationResponse of(Member member) {
        return new MemberInformationResponse(member.getId(), member.getPart(), member.getName(), member.getAge());
    }
}
