package com.ssafy.perfumee.model.entity.review;

import com.ssafy.perfumee.model.entity.perfume.Perfume;
import com.ssafy.perfumee.model.entity.user.User;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REVIEW_NO")
    private Integer no;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_NO", nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PERFUME_NO", nullable = false)
    private Perfume perfume;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp regDate;

    @Column(nullable = false)
    @CreationTimestamp
    private Timestamp updateDate;

    @Column(nullable = false)
    private Double rating;

    @Column(nullable = false)
    private int isExist;
}
