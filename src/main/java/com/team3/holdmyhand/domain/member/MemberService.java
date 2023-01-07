package com.team3.holdmyhand.domain.member;

import com.team3.holdmyhand.domain.member.dto.LoginDto;
import com.team3.holdmyhand.domain.member.dto.MemberRequestDto;
import com.team3.holdmyhand.domain.member.dto.MemberResponseDto;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.global.CommonApiResponse;
import com.team3.holdmyhand.global.config.security.dto.TokenResponseDto;
import com.team3.holdmyhand.global.config.security.jwt.TokenProvider;
import com.team3.holdmyhand.global.error.ErrorCode;
import com.team3.holdmyhand.global.error.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    // 일반 회원가입
    @Transactional
    public MemberResponseDto makeMember(MemberRequestDto memberRequestDto) {
        Optional<Member> checkMember = memberRepository.findByEmail(memberRequestDto.getEmail());
        if (checkMember.isPresent()) {
            throw new BadRequestException(ErrorCode.MEMBER_ALREADY_EXIST);
        }

        Member member = Member.builder()
                .nickname(memberRequestDto.getNickname())
                .email(memberRequestDto.getEmail())
                .password(passwordEncoder.encode(memberRequestDto.getPassword()))
                .phoneNum(memberRequestDto.getPhoneNum())
                .build();
        memberRepository.save(member);
        return MemberResponseDto.of(member);
    }

    // 일반 로그인
    @Transactional
    public ResponseEntity<CommonApiResponse<MemberResponseDto>> loginMember(LoginDto loginDto) {
        Optional<Member> checkMember = memberRepository.findByEmail(loginDto.getEmail());
        if (checkMember.isEmpty()) {
            throw new BadRequestException(ErrorCode.MEMBER_NOT_FOUND);
        }

        HttpHeaders httpHeaders = new HttpHeaders();
        TokenResponseDto tokenResponseDTO = tokenProvider.generateToken(loginDto.getEmail());

        Member member = memberRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));
        httpHeaders.add("Authorization", "Bearer " + tokenResponseDTO.getAccessToken());

        return new ResponseEntity<>(CommonApiResponse.of(MemberResponseDto.of(member, tokenResponseDTO)), httpHeaders, HttpStatus.OK);
    }
}