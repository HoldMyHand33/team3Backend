package com.team3.holdmyhand.domain.question;

import com.team3.holdmyhand.domain.member.dto.MemberResponseDto;
import com.team3.holdmyhand.domain.member.entity.Member;
import com.team3.holdmyhand.domain.question.dto.GetQuestionRes;
import com.team3.holdmyhand.domain.question.entity.Question;
import com.team3.holdmyhand.domain.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    /* 질문 받아오기 API */
    public GetQuestionRes findQuestionByQuestionDay(int day) {
        Question question = questionRepository.findQuestionByQuestionDay(day);
        return new GetQuestionRes(question);
    }
}
