package com.ssafy.perfumee.model.entity.user;

import com.ssafy.perfumee.model.entity.perfume.Note;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Taste {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASTE_NO")
    private Integer no;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_NO", nullable = false)
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "NOTE_NO", nullable = false)
    private Note note;
}
