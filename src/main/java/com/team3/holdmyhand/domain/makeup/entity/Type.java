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
    private int targetId;
    @NotNull
    private String type;
    @NotNull
    private String comment;
    @ColumnDefault("A")
    private String status;

    @Builder
    public Type(int typeId, int targetId, String type, String comment, String status) {
        this.typeId = typeId;
        this.targetId = targetId;
        this.type = type;
        this.comment = comment;
        this.status = status;
    }
}