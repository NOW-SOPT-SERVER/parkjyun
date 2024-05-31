package org.sopt.practice.service;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.entity.Member;
import org.sopt.practice.entity.Part;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.dto.request.MemberCreateDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberHelper {

    private final MemberRepository memberRepository;

    protected Member saveMember(Member member) {
        return memberRepository.save(member);
    }

    protected Member findByNameAndPartAndAge(String name, Part part, int age) {
        return memberRepository.findByNameAndPartAndAge(name, part, age).orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }

    protected boolean isExistMember(MemberCreateDto memberCreateDto) {//이름, 파트, 나이가 같은 회원이 존재하는지 확인
        return memberRepository.existsByNameAndPartAndAge(memberCreateDto.name(), memberCreateDto.part(), memberCreateDto.age());
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }
}
