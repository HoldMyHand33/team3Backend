package com.team3.holdmyhand.domain.diary;

import com.team3.holdmyhand.domain.diary.dto.GetMemberReq;
import com.team3.holdmyhand.domain.diary.dto.PostDiaryReq;
import com.team3.holdmyhand.domain.diary.dto.PostDiaryRes;
import com.team3.holdmyhand.domain.diary.entity.Diary;
import com.team3.holdmyhand.domain.diary.entity.PostDiary;
import com.team3.holdmyhand.domain.member.MemberRepository;
import com.team3.holdmyhand.domain.member.entity.Member;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    private final PostDiaryRepository postDiaryRepository;

    public DiaryService(DiaryRepository diaryRepository, MemberRepository memberRepository, PostDiaryRepository postDiaryRepository) {
        this.diaryRepository = diaryRepository;
        this.memberRepository = memberRepository;
        this.postDiaryRepository = postDiaryRepository;
    }

    
    // 일기 쓰기
    public PostDiaryRes postDiary(PostDiaryReq postDiaryReq){

        Member member=memberRepository.findById(postDiaryReq.getMemberID()).orElseThrow();
        System.out.println(member.getMemberId());

        Member partner=memberRepository.findById(postDiaryReq.getPartnerID()).orElseThrow();
        System.out.println(partner.getMemberId());


        //member(memberRepository.findById(postDiaryReq.getMemberID())
        //                                .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND))
        Diary diary=diaryRepository.save( Diary.builder()

                .diaryText(postDiaryReq.getDiaryText())
                .diaryImgURL(postDiaryReq.getDiaryImageURL())
                .build());

        postDiaryRepository.save(new PostDiary(member,diary));
        postDiaryRepository.save(new PostDiary(partner,diary));



        List<PostDiary> postDiaryList=postDiaryRepository.findByIdList(diary.getDiaryId());





        PostDiaryRes postDiaryRes=PostDiaryRes.builder()
                .diaryId(diary.getDiaryId())
                .postDiaryList(postDiaryList)
                .build();

        return postDiaryRes;



    }

    //친구조회

    public List<GetMemberReq> getMemberList(Long userIdx) {

        List<Member> memberList = diaryRepository.findMemberList(userIdx);

        List<GetMemberReq> getMemberReqList = memberList.stream()
                .map(d -> GetMemberReq.builder()
                        .memberId(d.getMemberId())
                        .nickName(d.getNickname()).build())
                .collect(Collectors.toList());

        return getMemberReqList;
    }

}
