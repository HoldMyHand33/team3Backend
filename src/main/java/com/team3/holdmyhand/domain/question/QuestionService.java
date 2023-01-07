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

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final MemberRepository memberRepository;

    /* 질문 받아오기 API */
    public GetQuestionRes findQuestionByQuestionDay(String email, int day) {
        try {
            Member member = memberRepository.findByEmail(email)
                    .orElseThrow(() -> new BadRequestException(ErrorCode.MEMBER_NOT_FOUND));

            Optional<Question> question = questionRepository.findQuestionByQuestionDay(day);
            return new GetQuestionRes(member, question.get());
        } catch(NoSuchElementException noSuchElementException) {
            throw new NotFoundException(noSuchElementException.getMessage());
        }

    }
}
