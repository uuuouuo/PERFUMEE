package com.ssafy.perfumee.model.entity.user;

import static javax.persistence.FetchType.LAZY;

import com.ssafy.perfumee.model.entity.perfume.Note;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Taste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TASTE_NO")
    private Integer no;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "USER_NO")
    private User user;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "NOTE_NO")
    private Note note;

    public void createTaste(User user, Note note){
        this.user = user;
        this.note = note;
    }
    public String getNoteName(){
        return note.getName();
    }
    public int getNoteId(){
        return note.getNo();
    }
}

