package com.example.umc10th.domain.member.dto;

import java.util.List;
import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record HomeInfo(
            String location,
            Integer completedMissionCount,
            List<OngoingMission> ongoingMissions
    ) {}

    public record OngoingMission(
            Long missionId,
            String storeName,
            String storeDescription,
            String missionContent,
            Integer reward,
            Integer remainingDays
    ) {}

    public record MyPage(
            String name,
            String email,
            String phoneNumber,
            Boolean isPhoneVerified,
            Integer point,
            Boolean notificationEnabled,
            List<MyReview> reviews
    ) {}

    public record MyReview(
            Long reviewId,
            String storeName,
            Integer rating,
            String content
    ) {}

    public record Login(
            String accessToken
    ) {}
}
