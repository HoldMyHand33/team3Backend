package com.team3.holdmyhand.domain.member;

import com.team3.holdmyhand.domain.member.dto.*;
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

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    // 일반 회원가입
    @Transactional
    public MemberResponseDto makeMember(MemberRequestDto memberRequestDto) throws ParseException {
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

    // 화해 날짜 결심
    @Transactional
    public MemberResponseDto modReconciliationDate(String email, ReconciliationRequestDto reconciliationRequestDto) throws ParseException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));
        member.updateReconciliationDate(reconciliationRequestDto.getReconciliationDate());
        memberRepository.save(member);

        return MemberResponseDto.of(member, reconciliationRequestDto);
    }

    // 홈 화면
    @Transactional(readOnly = true)
    public MemberResponseDto showReconciliationDate(String email) throws ParseException {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));

        return MemberResponseDto.of(member);
    }

    // 코드로 친구추가
    @Transactional
    public MemberResponseDto makeFriend(String email, CodeRequest codeRequest) throws ParseException {
        // email 부모 code 자식
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));
        Member codeMember = memberRepository.findByCode(codeRequest.getCode());
        codeMember.updateParent(member);
        memberRepository.save(codeMember);
        return MemberResponseDto.of(member);
    }

    // 친구 보기
    @Transactional(readOnly = true)
    public List<MemberResponseDto> showFriends(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));
        return member.getMemberList().stream()
                .map(MemberResponseDto::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public Boolean validateEmail(ValidateEmail validateEmail) {
        return memberRepository.existsByEmail(validateEmail.getEmail());
    }
}
