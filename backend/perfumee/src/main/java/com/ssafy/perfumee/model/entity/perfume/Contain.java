package com.ssafy.perfumee.model.entity.perfume;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;

@Entity
@Getter
public class Contain {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CONTAIN_NO")
  private Integer no;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "PERFUME_NO", nullable = false)
  private Perfume perfume;

  @ManyToOne(fetch = LAZY)
  @JoinColumn(name = "NOTE_NO", nullable = false)
  private Note note;

  @Column(nullable = false, length = 10)
  private String type;

  public Contain(){}

  public Contain(Perfume perfume, String type, Note note) {
    this.perfume = perfume;
    this.type = type;
    this.note = note;
  }

}
