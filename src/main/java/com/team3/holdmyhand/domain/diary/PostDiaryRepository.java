package com.team3.holdmyhand.domain.diary;

import com.team3.holdmyhand.domain.diary.entity.Diary;
import com.team3.holdmyhand.domain.diary.entity.PostDiary;
import com.team3.holdmyhand.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostDiaryRepository extends JpaRepository<PostDiary, Long> {



    @Query("select pd.diary from PostDiary pd where pd.member.memberId= :memberId and pd.partner.memberId= :partnerId")
    public Diary findDiaryByMemberId(@Param("memberId")Long memberId,@Param("partnerId")Long partnerId);



}
