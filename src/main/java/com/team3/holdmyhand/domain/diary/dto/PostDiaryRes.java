package com.team3.holdmyhand.domain.diary.dto;

import com.team3.holdmyhand.domain.diary.entity.PostDiary;
import com.team3.holdmyhand.domain.member.entity.Member;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class PostDiaryRes {

    private Long diaryId;
    private Long memberId;
    private Long partnerId;


}
