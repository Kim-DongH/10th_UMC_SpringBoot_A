package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberException;
import com.example.umc10th.domain.member.exception.code.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.domain.mission.entity.mapping.MemberMission;
import com.example.umc10th.domain.mission.enums.MissionCompleteStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import com.example.umc10th.domain.review.repository.ReviewRepository;
import com.example.umc10th.global.security.entity.AuthMember;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {

    public final MemberRepository memberRepository;
    public final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;

    //홈화면 조회 서비스
    public MemberResDTO.HomeInfo getHome(Long memberId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Integer completedMissionCount =
                memberMissionRepository.countByMember_IdAndIsCompleted(
                        member,
                        MissionCompleteStatus.COMPLETED
                );

        List<MemberMission> memberMissions =
                memberMissionRepository.findByMemberAndIsCompleted(
                        member,
                        MissionCompleteStatus.ONGOING
                );

        List<MemberResDTO.OngoingMission> ongoingMissions = memberMissions.stream()
                .map(memberMission -> new MemberResDTO.OngoingMission(
                        memberMission.getMission().getId(),
                        memberMission.getStore().getStoreName(),
                        memberMission.getStore().getStoreDescription(),
                        memberMission.getMission().getMissionContent(),
                        memberMission.getMission().getReward(),
                        7 // 일단 임시값
                ))
                .toList();

        return new MemberResDTO.HomeInfo(
                "청파동",//member.getLocation().getLocationName(),
                completedMissionCount,
                ongoingMissions
        );
    }

     //마이페이지 서비스
     public MemberResDTO.MyPage getMyPage(Long memberId) {

         Member member = memberRepository.findById(memberId)
                 .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

         List<MemberResDTO.MyReview> reviews = reviewRepository.findByMember(member)
                 .stream()
                 .map(review -> new MemberResDTO.MyReview(
                         review.getId(),
                         review.getStore().getStoreName(),
                         review.getStar(),
                         review.getReviewContent()
                 ))
                 .toList();

         return new MemberResDTO.MyPage(
                 member.getName(),
                 member.getEmail(),
                 member.getPhoneNumber(),
                 member.getIsPhoneVerified(),
                 member.getPoint(),
                 member.getNotificationEnabled(),
                 reviews
         );
     }

     public MemberResDTO.MyPage getMyPage(
             AuthMember member
     ){
        return MemberConverter.toGetInfo(member.getMember());
     }

}
