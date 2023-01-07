package com.team3.holdmyhand.domain.diary;

import com.team3.holdmyhand.domain.diary.dto.GetDiaryDetailReq;
import com.team3.holdmyhand.domain.diary.dto.GetMemberReq;
import com.team3.holdmyhand.domain.diary.dto.PostDiaryReq;
import com.team3.holdmyhand.domain.diary.dto.PostDiaryRes;
import com.team3.holdmyhand.domain.member.MemberRepository;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.global.CommonApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;
import java.util.Optional;

@Api(tags="교환하자")
@RestController
@RequestMapping("api/diary")
public class DiaryController {

    private final DiaryService diaryService;
    private final MemberRepository memberRepository;

    public DiaryController(DiaryService diaryService, MemberRepository memberRepository) {
        this.diaryService = diaryService;
        this.memberRepository = memberRepository;
    }

    // 일기 쓰기
    @ApiOperation(value = "일기 쓰기")
    @PostMapping("")
    public ResponseEntity<CommonApiResponse<PostDiaryRes>> postDiary(@RequestBody PostDiaryReq postDiaryReq,@ApiIgnore Authentication authentication){

        String userEmail= authentication.getName();
        //optional 예외처리
        Member member=memberRepository.findByEmail(userEmail).orElseThrow();

        PostDiaryRes postDiaryRes=diaryService.postDiary(member.getMemberId(),postDiaryReq);
        return ResponseEntity.ok(CommonApiResponse.of(postDiaryRes));



    }

    // 친구목록 조회하기 (나를 제외한 친구목록)
    @ApiOperation(value = "일기목록 조회하기")
    @GetMapping("/user")
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


    // 일기 상세
    @ApiOperation(value = "일기 상세조회")
    @GetMapping("/day/{day}/partner/{partnerEmail}")
    public ResponseEntity<CommonApiResponse<GetDiaryDetailReq>>getDiaryDetail(@PathVariable("day")int day,@PathVariable("partnerEmail")String partnerEmail,@ApiIgnore Authentication authentication){

        String userEmail= authentication.getName();
        //optional 예외처리
        Member member=memberRepository.findByEmail(userEmail).orElseThrow();
        Long memberId=member.getMemberId();
        Member partner=memberRepository.findByEmail(partnerEmail).orElseThrow();
        Long partnerId=partner.getMemberId();
        GetDiaryDetailReq getDiaryDetailReq=diaryService.getDiaryDetail(day,memberId,partnerId);

        return ResponseEntity.ok(CommonApiResponse.of(getDiaryDetailReq));
    }

}
