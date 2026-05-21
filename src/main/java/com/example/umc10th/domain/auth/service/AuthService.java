package com.example.umc10th.domain.auth.service;

import com.example.umc10th.domain.auth.dto.AuthReqDTO;
import com.example.umc10th.domain.auth.dto.AuthResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResDTO.SignUpResult signUp(AuthReqDTO.SignUp dto)
    {
        if (memberRepository.existsByEmailAndDeletedAtIsNull(dto.email())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        Member member = Member.builder()
                .email(dto.email())
                .password(passwordEncoder.encode(dto.password()))
                .build();

        Member savedMember = memberRepository.save(member);
        AuthMember authMember = new AuthMember(savedMember);
        String accessToken = jwtUtil.createAccessToken(authMember);

        return new AuthResDTO.SignUpResult(
                savedMember.getId(),
                savedMember.getEmail(),
                accessToken
        );
    }
}
