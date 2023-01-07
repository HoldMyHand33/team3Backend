package com.team3.holdmyhand.domain.question.entity;

import com.sun.istack.NotNull;
import com.team3.holdmyhand.global.config.entity.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Question extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private int questionId;
    private int questionDay;
    @NotNull
    private String questionText;
    @ColumnDefault("A")
    private String status;

    @Builder
    public Question(int questionId, int questionDay, String questionText, String status) {
        this.questionId = questionId;
        this.questionDay = questionDay;
        this.questionText = questionText;
        this.status = status;
    }
}
