package com.ssafy.perfumee.model.entity.notice;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTICE_NO")
    private Integer no;

    @Column(length = 45)
    private String subject;

    @Column(length = 1000)
    private String content;

    public Notice(String subject, String content){
        this.subject = subject;
        this.content = content;
    }

    public void updateNotice(String subject, String content){
        this.subject = subject;
        this.content = content;
    }
}
