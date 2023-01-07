package com.team3.holdmyhand.domain.question;

import com.team3.holdmyhand.domain.question.dto.GetQuestionRes;
import com.team3.holdmyhand.global.CommonApiResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/questions")
@Api(tags = "교환일기 질문")
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping("/{day}")
    @ApiOperation(value = "날짜별 질문 받아오기")
    public ResponseEntity<CommonApiResponse<GetQuestionRes>> getQuestion(
            @ApiIgnore Authentication authentication,
            @PathVariable int day) {

        // 현재 로그인 되어 있는 유저 정보 가져오기
        String email = authentication.name();

        return ResponseEntity.ok(CommonApiResponse.of(questionService.findQuestionByQuestionDay(email, day)));
    }
}
