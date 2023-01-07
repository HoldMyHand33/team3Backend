package com.team3.holdmyhand.domain.makeup.dto;

import com.team3.holdmyhand.domain.makeup.entity.Target;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTargetsRes {
    @ApiModelProperty("1")
    private int targetId;
    @ApiModelProperty("부모")
    private String targetText;

    public GetTargetsRes(Target target) {
        targetId = target.getTargetId();
        targetText = target.getTarget();
    }
}
