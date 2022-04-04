package com.ssafy.perfumee.model.entity.perfume;

import static javax.persistence.FetchType.LAZY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import org.hibernate.type.descriptor.sql.TinyIntTypeDescriptor;

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

}
