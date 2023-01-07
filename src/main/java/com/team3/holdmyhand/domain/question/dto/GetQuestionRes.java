package com.team3.holdmyhand.domain.question.dto;

import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.domain.question.entity.Question;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.QueryEval;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetQuestionRes {
    @ApiModelProperty(example = "1")
    private Long memberId;
    @ApiModelProperty(example = "1")
    private int questionId;
    @ApiModelProperty(example = "1")
    private int questionDay;
    @ApiModelProperty(example = "사이가 멀어진 당시에 어떤 상황이었나요?")
    private String questionText;

    public GetQuestionRes(Member member, Question question) {
        memberId = member.getMemberId();
        questionId = question.getQuestionId();
        questionDay = question.getQuestionDay();
        questionText = question.getQuestionText();
    }
}
