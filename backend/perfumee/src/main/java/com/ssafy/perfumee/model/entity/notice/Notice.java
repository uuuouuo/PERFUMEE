package com.ssafy.perfumee.model.entity.notice;

import javax.persistence.*;

@Entity
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTICE_NO")
    private Integer no;

    @Column(nullable = false, length = 45)
    private String subject;

    @Column(nullable = false, length = 1000)
    private String content;
}
