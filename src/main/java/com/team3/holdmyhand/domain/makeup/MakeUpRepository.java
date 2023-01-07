package com.team3.holdmyhand.domain.makeup;

import com.team3.holdmyhand.domain.makeup.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MakeUpRepository extends JpaRepository<Type, Integer> {
    Optional<Type> findByTypeId(int typeId);
}
