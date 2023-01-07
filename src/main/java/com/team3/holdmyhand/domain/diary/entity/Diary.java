package com.team3.holdmyhand.domain.diary.entity;

import com.team3.holdmyhand.domain.question.entity.Question;
import com.team3.holdmyhand.global.config.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter

public class Diary extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;


    @OneToMany(mappedBy="diary")
    private List<PostDiary> postDiaryList=new ArrayList<>();

    @ManyToOne
    private Question question;

    private String diaryText;

    private String diaryImgURL;

    @Builder
    Diary(Long diaryId,List<PostDiary> postDiaryList,String diaryText,String diaryImgURL,Question question){
        this.diaryId=diaryId;
        this.postDiaryList=postDiaryList;
        this.question=question;
        this.diaryText=diaryText;
        this.diaryImgURL=diaryImgURL;

    }

    public void setPostDiaryList(List<PostDiary>postDiaryList){
        this.postDiaryList=postDiaryList;
    }


    public Diary() {

    }
}


