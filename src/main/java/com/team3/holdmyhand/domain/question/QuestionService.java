package com.team3.holdmyhand.domain.question;

import com.team3.holdmyhand.domain.member.MemberRepository;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.domain.question.dto.GetQuestionRes;
import com.team3.holdmyhand.domain.question.entity.Question;
import com.team3.holdmyhand.global.error.ErrorCode;
import com.team3.holdmyhand.global.error.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    /* 질문 받아오기 API */
    @Transactional
    public GetQuestionRes findQuestionByQuestionDay(String email, int day) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));

        Question question = questionRepository.findQuestionByQuestionDay(day);
        return new GetQuestionRes(question);
    }
}
