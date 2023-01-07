package com.team3.holdmyhand.domain.makeup.dto;

import com.team3.holdmyhand.domain.makeup.entity.Type;
import com.team3.holdmyhand.domain.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GetCommentRes {
    @ApiModelProperty(example = "1")
    private Long memberId;
    @ApiModelProperty(example = "1")
    private int targetId;
    @ApiModelProperty(example = "1")
    private int typeId;
    @ApiModelProperty(example = "부모와 사소한 말다툼")
    private String makeUpComment;

    public GetCommentRes(Member member, Type type) {
        memberId = member.getMemberId();
        targetId = type.getTargetId();
        typeId = type.getTypeId();
        makeUpComment = type.getComment();
    }
}
