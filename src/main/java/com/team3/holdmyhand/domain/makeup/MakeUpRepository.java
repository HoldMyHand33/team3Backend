package com.team3.holdmyhand.domain.makeup;

import com.team3.holdmyhand.domain.makeup.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MakeUpRepository extends JpaRepository<Comment, Integer> {
    Optional<Comment> findByTargetIdaAndTypeId(int targetId, int typeId);
}
