package com.team3.holdmyhand.domain.diary.entity;

import com.team3.holdmyhand.domain.member.entity.Member;

import javax.persistence.*;

@Entity
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;

    @OneToOne
    private Member member;



    private Relation relation;
}
