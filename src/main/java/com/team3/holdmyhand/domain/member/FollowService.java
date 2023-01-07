package com.team3.holdmyhand.domain.member;

import com.team3.holdmyhand.domain.member.entity.Follow;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.global.error.ErrorCode;
import com.team3.holdmyhand.global.error.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FollowService {
    private final FollowRepository followRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public String makeFollow(Long fromUserId, Long toUserId) {
        Member fromMember = memberRepository.findById(fromUserId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));
        Member toMember = memberRepository.findById(toUserId)
                .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));

        Follow follow = Follow.builder()
                .fromUserId(fromMember)
                .toUserId(toMember)
                .build();
        followRepository.save(follow);

        return fromMember.getNickname()+"이 "+toMember.getNickname()+"을 팔로우 하였습니다.";
    }
}
