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
public class Comment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;
    private int targetId;
    private int typeId;
    @NotNull
    private String comment;
    @ColumnDefault("A")
    private String status;

    @Builder
    public Comment(int commentId, int targetId, int typeId, String comment, String status) {
        this.commentId = commentId;
        this.targetId = targetId;
        this.typeId = typeId;
        this.comment = comment;
        this.status = status;
    }
}