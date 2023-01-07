package com.team3.holdmyhand.domain.makeup.entity;

import com.sun.istack.NotNull;
import com.team3.holdmyhand.global.config.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Type extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int typeId;
    @NotNull
    private String type;
    @ColumnDefault("A")
    private String status;

    @Builder
    public Type(int typeId, String type, String status) {
        this.typeId = typeId;
        this.type = type;
        this.status = status;
    }
}