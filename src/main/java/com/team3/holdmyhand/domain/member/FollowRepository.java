package com.team3.holdmyhand.domain.member;

import com.team3.holdmyhand.domain.member.entity.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, Long> {

}
