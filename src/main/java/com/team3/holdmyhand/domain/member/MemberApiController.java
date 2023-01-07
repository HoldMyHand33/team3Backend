package com.team3.holdmyhand.domain.member;

import com.team3.holdmyhand.domain.member.dto.LoginDto;
import com.team3.holdmyhand.domain.member.dto.MemberRequestDto;
import com.team3.holdmyhand.domain.member.dto.MemberResponseDto;
import com.team3.holdmyhand.global.CommonApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
@Api(tags = "일반 회원가입, 로그인")
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("signup")
    @ApiOperation(value = "일반 회원가입")
    public ResponseEntity<CommonApiResponse<MemberResponseDto>> makeMember(
            @RequestBody MemberRequestDto memberRequestDto) {
        return ResponseEntity.ok(CommonApiResponse.of(memberService.makeMember(memberRequestDto)));
    }

    @PostMapping("login")
    @ApiOperation(value = "일반 로그인")
    public ResponseEntity<CommonApiResponse<MemberResponseDto>> loginMember(
            @RequestBody LoginDto loginDto) {
        return memberService.loginMember(loginDto);
    }
}