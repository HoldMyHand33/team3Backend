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
public class LoginDto {
    @ApiModelProperty(example = "jimin112688@gmail.com")
    private String email;
    @ApiModelProperty(example = "rejin0421")
    private String password;
}
