package com.team3.holdmyhand.domain.question;

import com.team3.holdmyhand.domain.question.dto.GetQuestionRes;
import com.team3.holdmyhand.domain.question.entity.Question;
import com.team3.holdmyhand.global.CommonApiResponse;
import com.team3.holdmyhand.global.error.GlobalExceptionHandler;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/questions")
@Api(tags = "교환일기 질문")
public class QuestionController {
    private final QuestionService questionService;

    /* 질문 받아오기 API */
    @GetMapping("/{day}")
    @ApiOperation(value = "날짜별 질문 받아오기")
    public ResponseEntity<CommonApiResponse<GetQuestionRes>> getQuestion(@PathVariable int day) {
        return ResponseEntity.ok(CommonApiResponse.of(questionService.findQuestionByQuestionDay(day)));
    }
}
