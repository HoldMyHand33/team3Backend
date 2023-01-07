package com.team3.holdmyhand.domain.question;

import com.team3.holdmyhand.domain.question.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    Optional<Question> findQuestionByQuestionDay(int day);
}
