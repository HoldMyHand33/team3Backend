package com.team3.holdmyhand.domain.diary;

import com.team3.holdmyhand.domain.diary.entity.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories

public interface DiaryRepository extends JpaRepository<Diary, Long> {

    public Diary findByDiaryId(Long diaryIdx);





}
