package com.team3.holdmyhand.domain.diary;

import com.team3.holdmyhand.domain.diary.dto.GetMemberReq;
import com.team3.holdmyhand.domain.diary.dto.PostDiaryReq;
import com.team3.holdmyhand.domain.diary.dto.PostDiaryRes;
import com.team3.holdmyhand.domain.member.MemberRepository;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.global.CommonApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
public class DiaryController {

    private final DiaryService diaryService;
    private final MemberRepository memberRepository;

    public DiaryController(DiaryService diaryService, MemberRepository memberRepository) {
        this.diaryService = diaryService;
        this.memberRepository = memberRepository;
    }

    // 일기 쓰기
    @PostMapping("/diary")
    public ResponseEntity<CommonApiResponse<PostDiaryRes>> postDiary(@RequestBody PostDiaryReq postDiaryReq){

        PostDiaryRes postDiaryRes = diaryService.postDiary(postDiaryReq);
        return ResponseEntity.ok(CommonApiResponse.of(postDiaryRes));




    }

    // 친구목록 조회하기 (나를 제외한 친구목록)
    @GetMapping("/diary/user")
    public ResponseEntity<CommonApiResponse<List<GetMemberReq>>>getUserList(
            @ApiIgnore Authentication authentication
    ){
        // 현재 로그인 되어 이는 유저 정보 가져오기
        // 스프링 컨텍스트 홀더에 있는 이메일 정보 가져오기
        String userEmail= authentication.getName();
        //optional 예외처리
        Member member=memberRepository.findByEmail(userEmail).orElseThrow();
        Long userIdx=member.getMemberId();
        List<GetMemberReq> getMemberReqList=diaryService.getMemberList(userIdx);

        return ResponseEntity.ok(CommonApiResponse.of(getMemberReqList));

        ///
    }


    // 일기 목록 조회하기

    // 일기 확인하기
}
