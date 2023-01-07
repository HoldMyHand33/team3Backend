package com.team3.holdmyhand.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.global.config.security.dto.TokenResponseDto;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) //NULL 필드 가림
public class MemberResponseDto {
    private String nickname;

    private String email;

    private String password;

    private String phoneNum;

    private String profileImage;

    private String accessToken;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .phoneNum(member.getPhoneNum())
                .profileImage(member.getProfileImg())
                .build();
    }

    public static MemberResponseDto of(Member member, TokenResponseDto tokenResponseDto) {
        return MemberResponseDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .profileImage(member.getProfileImg())
                .accessToken(tokenResponseDto.getAccessToken())
                .build();
    }
}
