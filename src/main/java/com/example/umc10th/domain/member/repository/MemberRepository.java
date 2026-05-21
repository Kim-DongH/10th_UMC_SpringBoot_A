package com.example.umc10th.domain.member.repository;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.enums.SocialType;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    boolean existsByEmailAndDeletedAtIsNull(String email);

    Optional<Member> findByEmail(String email);

    Optional<Member> findBySocialTypeAndSocialUid(
            SocialType socialType,
            String socialUid
    );
}
