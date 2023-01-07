package com.team3.holdmyhand.domain.member.entity;

import com.team3.holdmyhand.global.config.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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

    private String reconciliationDate;

    @Builder
    public Member(Long memberId, String nickname, String password,
                  String email, String phoneNum, String status, String profileImg,
                  String  reconciliationDate) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.phoneNum = phoneNum;
        this.status = status;
        this.profileImg = profileImg;
        this.reconciliationDate = reconciliationDate;
    }
    public void updateReconciliationDate(String reconciliationDate) {
        this.reconciliationDate = reconciliationDate;
    }


}
