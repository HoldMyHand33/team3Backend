package com.team3.holdmyhand.domain.makeup.repository;

import com.team3.holdmyhand.domain.makeup.entity.Target;
import com.team3.holdmyhand.domain.makeup.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TypeRepository extends JpaRepository<Type, Integer> {
    Optional<Type> findByTypeId(int typeId);
    List<Type> findAllByTargetId(int targetId);

}
