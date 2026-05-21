package com.example.umc10th.domain.member.converter;

import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.global.security.dto.OAuthDTO;
import java.util.List;

public class MemberConverter {
    public static MemberResDTO.MyPage toGetInfo(Member member){
        return new MemberResDTO.MyPage(
                member.getName(),
                member.getEmail(),
                member.getPhoneNumber(),
                member.getIsPhoneVerified(),
                member.getPoint(),
                member.getNotificationEnabled(),
                List.of()
        );
    }

    public static MemberResDTO.Login toLogin(String accessToken) {
        return new MemberResDTO.Login(accessToken);
    }

    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .email(dto.getSocialEmail())
                .name(dto.getName())
                .socialType(dto.getSocialType())
                .socialUid(dto.getSocialUid())
                .build();
    }
}

