package com.ssafy.perfumee.model.entity.perfume;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Note {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "NOTE_NO")
  private Integer no;

  @Column(nullable = false, length = 45)
  private String name;

}
