package com.team3.holdmyhand.domain.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReconciliationRequestDto {
    @ApiModelProperty(example = "2023/01/01")
    private String reconciliationDate;
}
