package com.team3.holdmyhand.domain.diary;

import com.team3.holdmyhand.domain.diary.entity.PostDiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDiaryRepository extends JpaRepository<PostDiary, Long> {

    @Query("select pd.member.memberId from PostDiary pd where pd.postDiaryId = :idx")
    public List<PostDiary> findByIdList(@Param("idx") Long idx);

}
