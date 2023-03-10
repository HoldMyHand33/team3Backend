package com.team3.holdmyhand.domain.member;

import com.team3.holdmyhand.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByEmail(String email);

    Member findByCode(String code);

    boolean existsByEmail(String email);
}
