package com.example.carrotclone.service;

import com.example.carrotclone.dto.request.MemberCreateRequest;
import com.example.carrotclone.dto.response.MemberCreateResponse;
import com.example.carrotclone.entity.Member;
import com.example.carrotclone.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberCommandService {
    
    private final MemberRepository memberRepository;

    public MemberCreateResponse createMember(MemberCreateRequest request) {
        long memberId = memberRepository.save(Member.of(request.nickname())).getId();
        return MemberCreateResponse.of(memberId);
    }
}
