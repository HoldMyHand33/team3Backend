package com.team3.holdmyhand.domain.diary;

import com.team3.holdmyhand.domain.diary.entity.Diary;
import com.team3.holdmyhand.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories

public interface DiaryRepository extends JpaRepository<Diary, Long> {

     Optional<Diary> findByDiaryId(Long diaryIdx);


     @Query(value= "select m.nickname from Member m where m.memberId != :userIdx",nativeQuery = true)
     List<Member> findMemberList(@Param("userIdx")Long userIdx);


     @Query(value="select d.diaryText from Diary d join PostDiary pd on pd.diary.diaryId=d.diaryId where d.question.questionDay=:day and pd.member.memberId=:memberIdx and pd.partner.memberId=:partnerIdx")
     String findDiaryByMemberAndQuestion(@Param("day")int day,@Param("memberIdx")Long memberIdx,@Param("partnerIdx")Long partnerIdx);










}
