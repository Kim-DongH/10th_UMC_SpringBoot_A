package com.example.umc10th.domain.member.exception.code;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON404_1", "유저를 찾을 수 없습니다."),
    LOCATION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER400_2", "존재하지 않는 지역입니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER400_3", "존재하지 않는 미션입니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.NOT_FOUND, "MEMBER400_4", "지원하지 않는 소셜 로그인입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
