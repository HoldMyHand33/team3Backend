package com.team3.holdmyhand.domain.member.entity;

import com.team3.holdmyhand.global.config.entity.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    private String nickname;

    private String password;

    private String email;

    private String phoneNum;

    private String status;

    private String profileImg;

    private String code;

    private String reconciliationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    private Member parent;

    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Member> memberList;

    @Builder
    public Member(Long memberId, String nickname, String password,
                  String email, String phoneNum, String status, String profileImg,
                  String code,
                  String reconciliationDate, Member parent, List<Member> memberList) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.status = status;
        this.profileImg = profileImg;
        this.code = code;
        this.reconciliationDate = reconciliationDate;
        this.parent = parent;
        this.memberList = memberList;
    }
    public void updateReconciliationDate(String reconciliationDate) {
        this.reconciliationDate = reconciliationDate;
    }

    public void updateParent(Member parent) {
        this.parent = parent;
    }
}
