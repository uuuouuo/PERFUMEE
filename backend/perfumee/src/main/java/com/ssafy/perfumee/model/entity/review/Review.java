package com.ssafy.perfumee.model.entity.review;

import static javax.persistence.FetchType.LAZY;

import com.ssafy.perfumee.model.entity.perfume.Perfume;
import com.ssafy.perfumee.model.entity.user.User;
import java.math.BigDecimal;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;

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

  @Column(nullable = false, precision = 3, scale = 2)
  private BigDecimal rating;

  @Column(nullable = false)
  @CreationTimestamp
  private Timestamp regDate;

  @Column(nullable = false)
  @CreationTimestamp
  private Timestamp updateDate;

  @Column(nullable = false)
  private Boolean isExist;

}
