package com.team3.holdmyhand.domain.diary.dto;

import lombok.Data;

@Data

public class PostDiaryReq {

    private Long memberID;
    private Long partnerID;
    private String diaryText;
    private String diaryImageURL;

}
