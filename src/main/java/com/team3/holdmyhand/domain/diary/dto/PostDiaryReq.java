package com.team3.holdmyhand.domain.diary.dto;

import lombok.Data;

@Data

public class PostDiaryReq {


    private Long partnerID;
    private String diaryText;
    private String diaryImageURL;
    private int makeUpDay; //화해한지 몇일째인지

}
