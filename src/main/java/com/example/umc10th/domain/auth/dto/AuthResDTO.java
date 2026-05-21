package com.example.umc10th.domain.auth.dto;

public class AuthResDTO {

    public record SignUpResult (
            Long memberId,
            String email,
            String accessToken
    ){}

    public record OnboardingResult(
            Long memberId,
            String email,
            String name
    ){}
}
