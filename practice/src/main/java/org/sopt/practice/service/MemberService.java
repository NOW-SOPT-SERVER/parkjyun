package org.sopt.practice.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.sopt.practice.auth.UserAuthentication;
import org.sopt.practice.common.dto.ErrorMessage;
import org.sopt.practice.common.jwt.JwtTokenProvider;
import org.sopt.practice.entity.Member;
import org.sopt.practice.exception.NotFoundException;
import org.sopt.practice.repository.MemberRepository;
import org.sopt.practice.service.dto.MemberCreateDto;
import org.sopt.practice.service.dto.MemberFindDto;
import org.sopt.practice.service.dto.response.MemberJoinResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @Transactional
    public MemberJoinResponse createMember(MemberCreateDto memberCreate) {
        Member member = memberRepository.save(Member.create(memberCreate.name(), memberCreate.part(), memberCreate.age()));
        Long memberId = member.getId();

        String accessToken = jwtTokenProvider.issueAccessToken(UserAuthentication.createUserAuthentication(memberId));
        return MemberJoinResponse.of(accessToken, memberId.toString());
    }

    public Member findById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException(ErrorMessage.MEMBER_NOT_FOUND));
    }

    public MemberFindDto findMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        return MemberFindDto.of(member);
    }

    @Transactional
    public void deleteMemberById(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new EntityNotFoundException("Member not found"));
        memberRepository.delete(member);
    }

    public List<MemberFindDto> findMembers() {
        return memberRepository.findAll().stream().map(MemberFindDto::of).collect(Collectors.toList());
    }
}
