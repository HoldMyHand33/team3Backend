package com.team3.holdmyhand.domain.member;

import com.team3.holdmyhand.domain.member.dto.*;
import com.team3.holdmyhand.global.CommonApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.text.ParseException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/members")
@Api(tags = "일반 회원가입, 로그인")
public class MemberApiController {
    private final MemberService memberService;

    @PostMapping("signup")
    @ApiOperation(value = "일반 회원가입")
    public ResponseEntity<CommonApiResponse<MemberResponseDto>> makeMember(
            @RequestBody MemberRequestDto memberRequestDto) throws ParseException {
        return ResponseEntity.ok(CommonApiResponse.of(memberService.makeMember(memberRequestDto)));
    }

    @PostMapping("login")
    @ApiOperation(value = "일반 로그인")
    public ResponseEntity<CommonApiResponse<MemberResponseDto>> loginMember(
            @RequestBody LoginDto loginDto) {
        return memberService.loginMember(loginDto);
    }

    @GetMapping
    @ApiOperation(value = "홈화면 회원정보")
    public ResponseEntity<CommonApiResponse<MemberResponseDto>> showMember(
            @ApiIgnore Authentication authentication) throws ParseException {
        return ResponseEntity.ok(CommonApiResponse.of(memberService.showReconciliationDate(authentication.getName())));
    }

    @PatchMapping
    @ApiOperation(value = "화해날짜 결심")
    public ResponseEntity<CommonApiResponse<MemberResponseDto>> modReconciliationDate(
            @ApiIgnore Authentication authentication,
            @RequestBody ReconciliationRequestDto reconciliationRequestDto) throws ParseException {
        return ResponseEntity.ok(CommonApiResponse.of(memberService.modReconciliationDate(authentication.getName(), reconciliationRequestDto)));
    }

    @PatchMapping("friends")
    @ApiOperation(value = "친구추가")
    public ResponseEntity<CommonApiResponse<MemberResponseDto>> makeFreind(
            @ApiIgnore Authentication authentication,
            @RequestBody CodeRequest codeRequest) throws ParseException {
        return ResponseEntity.ok(CommonApiResponse.of(memberService.makeFriend(authentication.getName(), codeRequest)));
    }

    @GetMapping("friends")
    @ApiOperation(value = "친구보기")
    public ResponseEntity<CommonApiResponse<List<MemberResponseDto>>> showFriends(
            @ApiIgnore Authentication authentication) {
        return ResponseEntity.ok(CommonApiResponse.of(memberService.showFriends(authentication.getName())));
    }

    @PostMapping("email")
    @ApiOperation("이메일 중복")
    public ResponseEntity<CommonApiResponse<Boolean>> validateEmail(
        @RequestBody ValidateEmail validateEmail) {
        return ResponseEntity.ok(CommonApiResponse.of(memberService.validateEmail(validateEmail)));
    }
}