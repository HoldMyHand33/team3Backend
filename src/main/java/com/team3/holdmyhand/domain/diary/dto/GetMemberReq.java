package com.team3.holdmyhand.domain.diary.dto;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GetMemberReq {

    private Long memberId;
    private String nickName;
}
