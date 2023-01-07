package com.team3.holdmyhand.domain.diary.entity;

import com.team3.holdmyhand.domain.member.entity.Member;
import lombok.Builder;

import javax.persistence.*;
import java.util.Optional;

@Entity

public class PostDiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postDiaryId;

    @ManyToOne(fetch= FetchType.LAZY) //N:1단방향
    @JoinColumn(name="memberId")
    private Member member;

    @ManyToOne(fetch=FetchType.LAZY) //N:1단방향
    @JoinColumn(name="diaryId")
    private Diary diary;

    @Builder
    public PostDiary(Member member,Diary diary){
        this.member=member;
        this.diary=diary;
    }


    public PostDiary() {

    }
}
