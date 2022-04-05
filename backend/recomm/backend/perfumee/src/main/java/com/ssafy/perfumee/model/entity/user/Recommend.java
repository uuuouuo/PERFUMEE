package com.ssafy.perfumee.model.entity.user;

import com.ssafy.perfumee.model.entity.perfume.Perfume;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Recommend {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECOMMEND_NO")
    private Integer no;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_NO")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PERFUME_NO")
    private Perfume perfume;
}
