package org.sopt.practice.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.practice.controller.headers.Headers;
import org.sopt.practice.service.AuthService;
import org.sopt.practice.service.dto.request.MemberCreateDto;
import org.sopt.practice.service.dto.response.TokenResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody MemberCreateDto memberCreateDto) {
        TokenResponse tokenResponse = authService.login(memberCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("Location", tokenResponse.memberId().toString())
                .body(tokenResponse);
    }

    @PostMapping("/reissue")
    public ResponseEntity<TokenResponse> reissue(@RequestHeader(Headers.AUTHOTIZATION_HEADER) String refreshToken) {
        return ResponseEntity.ok(authService.reissue(refreshToken));
    }
}

