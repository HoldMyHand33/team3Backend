package com.team3.holdmyhand.domain.makeup.dto;

import com.team3.holdmyhand.domain.makeup.entity.Comment;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentRes {
    @ApiModelProperty(example = "1")
    private int commentId;
    @ApiModelProperty(example = "부모와 사소한 말다툼")
    private String makeUpComment;

    public GetCommentRes(Comment comment) {
        commentId = comment.getCommentId();
        makeUpComment = comment.getComment();
    }
}
