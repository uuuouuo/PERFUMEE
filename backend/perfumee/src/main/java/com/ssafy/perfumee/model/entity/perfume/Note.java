package com.ssafy.perfumee.model.entity.perfume;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "NOTE_NO")
  private Integer no;

  @Column(nullable = false, length = 100)
  private String name;

  public Note() {}

  public Note(String name) {
    this.name = name;
  }

}
