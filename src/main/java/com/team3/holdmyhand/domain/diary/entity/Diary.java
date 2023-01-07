package com.team3.holdmyhand.domain.diary.entity;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter

public class Diary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long diaryId;

    private String diaryText;

    private String diaryImgURL;

    @Builder
    Diary(Long diaryId,String diaryText,String diaryImgURL){
        this.diaryId=diaryId;
        this.diaryText=diaryText;
        this.diaryImgURL=diaryImgURL;

    }



}


