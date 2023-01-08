package com.team3.holdmyhand.domain.diary;

import com.team3.holdmyhand.domain.diary.dto.GetDiaryDetailReq;
import com.team3.holdmyhand.domain.diary.dto.GetMemberReq;
import com.team3.holdmyhand.domain.diary.dto.PostDiaryReq;
import com.team3.holdmyhand.domain.diary.dto.PostDiaryRes;
import com.team3.holdmyhand.domain.diary.entity.Diary;
import com.team3.holdmyhand.domain.diary.entity.PostDiary;
import com.team3.holdmyhand.domain.member.MemberRepository;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.domain.question.QuestionRepository;
import com.team3.holdmyhand.domain.question.entity.Question;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final MemberRepository memberRepository;

    private final PostDiaryRepository postDiaryRepository;

    private final QuestionRepository questionRepository;

    public DiaryService(DiaryRepository diaryRepository, MemberRepository memberRepository, PostDiaryRepository postDiaryRepository, QuestionRepository questionRepository) {
        this.diaryRepository = diaryRepository;
        this.memberRepository = memberRepository;
        this.postDiaryRepository = postDiaryRepository;
        this.questionRepository = questionRepository;
    }

    // 일기 쓰기

    @Transactional
    public PostDiaryRes postDiary(Long memberIdx,PostDiaryReq postDiaryReq){

        Member member=memberRepository.findById(memberIdx).orElseThrow();

        Member partner=memberRepository.findById(postDiaryReq.getPartnerID()).orElseThrow();
        System.out.println(partner.getMemberId());

        Question question=questionRepository.findQuestionByQuestionDay(postDiaryReq.getMakeUpDay()).orElseThrow();

        System.out.println(question.getQuestionText());
        //member(memberRepository.findById(postDiaryReq.getMemberID())
        //                                .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND))
        Diary diary=diaryRepository.save( Diary.builder()
                        .question(question)
                .diaryText(postDiaryReq.getDiaryText())
                .diaryImgURL(postDiaryReq.getDiaryImageURL())
                .build());

        PostDiary postDiary= postDiaryRepository.save(new PostDiary(member,partner,diary));



        PostDiaryRes postDiaryRes=PostDiaryRes.builder()
                .diaryId(diary.getDiaryId())
                .partnerId(postDiary.getPartner().getMemberId())
                .memberId(postDiary.getMember().getMemberId())
                .build();

        return postDiaryRes;



    }

    //친구조회

    @Transactional
    public List<GetMemberReq> getMemberList(Long userIdx) {

        List<Member> memberList = diaryRepository.findMemberList(userIdx);

        List<GetMemberReq> getMemberReqList = memberList.stream()
                .map(d -> GetMemberReq.builder()
                        .memberId(d.getMemberId())
                        .nickName(d.getNickname()).build())
                .collect(Collectors.toList());

        return getMemberReqList;
    }

    // 일기 상세
    @Transactional
    public GetDiaryDetailReq getDiaryDetail(int day,Long memberId,Long partnerId){

        Diary diary=postDiaryRepository.findDiaryByMemberId(memberId,partnerId);
        Member member=memberRepository.findById(memberId).orElseThrow();
        Member partner=memberRepository.findById(partnerId).orElseThrow();




        return GetDiaryDetailReq.builder()
                .day(diary.getQuestion().getQuestionDay())
                .questionText(diary.getQuestion().getQuestionText())
                .userName(member.getNickname())
                .userText(diaryRepository.findDiaryByMemberAndQuestion(day,memberId,partnerId))
                .partnerName(partner.getNickname())
                .partnerText(diaryRepository.findDiaryByMemberAndQuestion(day,partnerId,memberId))
                .build();

    }


}
