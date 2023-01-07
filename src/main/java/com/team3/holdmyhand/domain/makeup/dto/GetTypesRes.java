package com.team3.holdmyhand.domain.makeup.dto;

import com.team3.holdmyhand.domain.makeup.entity.Target;
import com.team3.holdmyhand.domain.makeup.entity.Type;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetTypesRes {
    @ApiModelProperty("1")
    private int typeId;
    @ApiModelProperty("약속을 안 지켰을때")
    private String typeText;

    public GetTypesRes(Type type) {
        typeId = type.getTypeId();
        typeText = type.getType();
    }
}
