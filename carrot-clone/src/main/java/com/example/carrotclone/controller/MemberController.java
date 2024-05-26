package com.example.carrotclone.controller;

import com.example.carrotclone.common.code.success.MemberSuccessCode;
import com.example.carrotclone.common.dto.CarrotResponse;
import com.example.carrotclone.dto.request.MemberCreateRequest;
import com.example.carrotclone.service.MemberCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberCommandService memberCommandService;

    @PostMapping
    public CarrotResponse<?> createMember(@RequestBody @Valid MemberCreateRequest request) {
        return CarrotResponse.success(MemberSuccessCode.MEMBER_CREATE_SUCCESS, memberCommandService.createMember(request));
    }
}
