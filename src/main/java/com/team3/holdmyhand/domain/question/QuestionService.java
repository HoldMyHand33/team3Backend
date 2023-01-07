package com.team3.holdmyhand.domain.question;

import com.team3.holdmyhand.domain.member.MemberRepository;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.domain.question.dto.GetQuestionRes;
import com.team3.holdmyhand.domain.question.entity.Question;
import com.team3.holdmyhand.global.error.ErrorCode;
import com.team3.holdmyhand.global.error.exception.BadRequestException;
import com.team3.holdmyhand.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    /* 질문 받아오기 API */
    public GetQuestionRes getQuestion(String email, int day) {
        try {
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));

            Optional<Question> question = questionRepository.findQuestionByQuestionDay(day);
            return new GetQuestionRes(member, question.get());
        } catch(NoSuchElementException noSuchElementException) {
            throw new NotFoundException(noSuchElementException.getMessage());
        }
    }

    /* 질문 리스트 받아오기 API */
    public List<GetQuestionRes> getQuestions(String email) {
        try {
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));

            List<Question> question = questionRepository.findQuestionsBy();
            List<GetQuestionRes> getQuestionRes = question.stream()
                    .map(d -> GetQuestionRes.builder()
                            .memberId(member.getMemberId())
                            .questionId(d.getQuestionId())
                            .questionDay(d.getQuestionDay())
                            .questionText(d.getQuestionText()).build())
                    .collect(Collectors.toList());

            return getQuestionRes;
        } catch(NoSuchElementException noSuchElementException) {
            throw new NotFoundException(noSuchElementException.getMessage());
        }
    }
}
