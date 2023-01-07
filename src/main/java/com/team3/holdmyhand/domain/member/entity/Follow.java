package com.team3.holdmyhand.domain.member.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long followId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member fromUserId;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member toUserId;

    @Builder
    public Follow(Long followId, Member fromUserId, Member toUserId) {
        this.followId = followId;
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }
}
