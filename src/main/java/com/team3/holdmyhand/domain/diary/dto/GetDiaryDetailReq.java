package com.team3.holdmyhand.domain.diary.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetDiaryDetailReq {

    private int day;
    private String questionText;
    private String userName;
    private String userText;
    private String partnerName;
    private String partnerText;

}
