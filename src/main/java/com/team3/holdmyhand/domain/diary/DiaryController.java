package com.team3.holdmyhand.domain.diary;

import com.team3.holdmyhand.domain.diary.dto.PostDiaryReq;
import com.team3.holdmyhand.domain.diary.dto.PostDiaryRes;
import com.team3.holdmyhand.global.config.CommonApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiaryController {

    private final DiaryService diaryService;

    public DiaryController(DiaryService diaryService) {
        this.diaryService = diaryService;
    }

    // 일기 쓰기
    @PostMapping("/diary")
    public CommonApiResponse<PostDiaryRes> postDiary(@RequestBody PostDiaryReq postDiaryReq) throws Exception{

        PostDiaryRes postDiaryRes=diaryService.postDiary(postDiaryReq);
        return new CommonApiResponse<>(200,true,postDiaryRes);



    }

    // 친구목록 조회하기

    // 일기 목록 조회하기

    // 일기 확인하기
}
