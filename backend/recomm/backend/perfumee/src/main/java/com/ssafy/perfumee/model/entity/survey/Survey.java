package com.ssafy.perfumee.model.entity.survey;

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
public class Survey {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "SURVEY_NO")
  private Integer no;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "NOTE_NO")
  private Note note;

  @Column(nullable = false, length = 45)
  private String noteKor;

  public Survey() {}

  public Survey(Note note, String noteKor) {
    this.note = note;
    this.noteKor = noteKor;
  }

}
