package com.team3.holdmyhand.domain.member.dto;

import com.team3.holdmyhand.domain.member.entity.Member;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberRequestDto {
    @ApiModelProperty(example = "jimin112688@gmail.com")
    private String email;
    @ApiModelProperty(example = "rejin0421")
    private String password;

    @ApiModelProperty(example = "강냉이")
    private String nickname;

    @ApiModelProperty(example = "01063471084")
    private String phoneNum;

    public Member toMember(MemberRequestDto memberRequestDto) {
        return Member.builder()
                .email(memberRequestDto.getEmail())
                .password(memberRequestDto.getPassword())
                .nickname(memberRequestDto.getNickname())
                .phoneNum(memberRequestDto.getPhoneNum())
                .build();
    }
}
