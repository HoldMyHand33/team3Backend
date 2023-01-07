package com.team3.holdmyhand.domain.diary;

import com.team3.holdmyhand.domain.diary.dto.PostDiaryReq;
import com.team3.holdmyhand.domain.diary.dto.PostDiaryRes;
import com.team3.holdmyhand.domain.diary.entity.Diary;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;

    public DiaryService(DiaryRepository diaryRepository) {
        this.diaryRepository = diaryRepository;
    }

    public PostDiaryRes postDiary(PostDiaryReq postDiaryReq){


        Diary diary=diaryRepository.save( Diary.builder()
                .diaryText(postDiaryReq.getDiaryText())
                .diaryImgURL(postDiaryReq.getDiaryImageURL())
                .build());
        Long diaryId=diaryRepository.findByDiaryId(diary.getDiaryId()).getDiaryId();

        PostDiaryRes postDiaryRes=PostDiaryRes.builder()
                .diaryId(diaryId)
                .build();

        return postDiaryRes;



    }
}
