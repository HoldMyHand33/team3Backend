package com.team3.holdmyhand.domain.member.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ValidateEmail {
    @ApiModelProperty(example = "jimin112688@gmail.com")
    private String email;
}
