package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.exception.code.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    //홈 화면 조회
    @GetMapping("/v1/member/home")
    public ApiResponse<MemberResDTO.HomeInfo> getHome(
            @RequestParam Long memberId
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.HOME_INFO_SUCCESS, memberService.getHome(memberId));
    }

    //마이페이지 조회
    //나중에 GETMapping으로 바꾸기
    @GetMapping("/v2/users/me")
    public ApiResponse<MemberResDTO.MyPage> getMyPage(
            @AuthenticationPrincipal AuthMember member
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.MYPAGE_SUCCESS, memberService.getMyPage(member));
    }
}
