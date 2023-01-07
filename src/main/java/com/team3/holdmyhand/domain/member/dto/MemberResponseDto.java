package com.team3.holdmyhand.domain.member.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.global.config.security.dto.TokenResponseDto;
import lombok.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    private String reconciliationDate;
    private String accessToken;
    public static MemberResponseDto of(Member member) {
        String diffDays = null;
        if (member.getReconciliationDate() == null) {
            diffDays = "아직 화해를 결심하지 않았어요.";
        } else {
            Date now = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

            String date1 = formatter.format(now);
            String date2 = member.getReconciliationDate();
            try {
                Date format1 = new SimpleDateFormat("yyyy/MM/dd").parse(date1);
                Date format2 = new SimpleDateFormat("yyyy/MM/dd").parse(date2);

                long diffSec = (format1.getTime() - format2.getTime()) / 1000;
                diffDays = String.valueOf(diffSec / (24 * 60 * 60)) + "일";
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //일자수 차이

        return MemberResponseDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .phoneNum(member.getPhoneNum())
                .profileImage(member.getProfileImg())
                .reconciliationDate(diffDays)
                .build();
    }
    public static MemberResponseDto of(Member member, TokenResponseDto tokenResponseDto) {
        return MemberResponseDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .profileImage(member.getProfileImg())
                .reconciliationDate(member.getReconciliationDate())
                .accessToken(tokenResponseDto.getAccessToken())
                .build();
    }

    public static MemberResponseDto of(Member member, ReconciliationRequestDto reconciliationRequestDto) throws ParseException {
        Long diffDays = null;
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

        String date1 = formatter.format(now);
        String date2 = reconciliationRequestDto.getReconciliationDate();

        try {
            Date format1 = new SimpleDateFormat("yyyy/MM/dd").parse(date1);
            Date format2 = new SimpleDateFormat("yyyy/MM/dd").parse(date2);

            long diffSec = (format1.getTime() - format2.getTime()) / 1000;
            diffDays = diffSec / (24 * 60 * 60); //일자수 차이
        } catch (Exception e) {
            e.printStackTrace();
        }

        return MemberResponseDto.builder()
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .profileImage(member.getProfileImg())
                .reconciliationDate(diffDays + "일")
                .build();
    }
}
