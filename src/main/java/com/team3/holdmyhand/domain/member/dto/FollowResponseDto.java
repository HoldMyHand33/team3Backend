package com.team3.holdmyhand.domain.member.dto;

import com.team3.holdmyhand.domain.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FollowResponseDto {
    private Long followId;
    private Long fromUserIdMemberId;
    private Long toUserIdMemberId;
    private Long matpal;
}
