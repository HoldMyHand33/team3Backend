package com.team3.holdmyhand.domain.question;

import com.team3.holdmyhand.domain.question.dto.GetQuestionRes;
import com.team3.holdmyhand.domain.question.entity.Question;
import com.team3.holdmyhand.global.error.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;

    /* 질문 받아오기 API */
    public GetQuestionRes findQuestionByQuestionDay(int day) {
        try {
            Optional<Question> question = questionRepository.findQuestionByQuestionDay(day);
            return new GetQuestionRes(question.get());
        } catch(NoSuchElementException noSuchElementException) {
            throw new NotFoundException(noSuchElementException.getMessage());
        }

    }
}
