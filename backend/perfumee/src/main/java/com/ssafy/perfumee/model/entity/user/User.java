package com.ssafy.perfumee.model.entity.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "USER_NO")
  private Integer no;

  @Column(nullable = false, length = 45)
  private String id;

  @Column(nullable = false, length = 100)
  private String password;

  @Column(nullable = false, length = 45, unique = true)
  private String nickname;

  @Column(nullable = false, length = 45)
  private String email;

  @Column(name = "PROFILE_IMAGE", nullable = false, length = 100)
  private String image;

  @Column(nullable = false, length = 45)
  private String gender;

  @Column(nullable = false, columnDefinition = "VARCHAR(45) DEFAULT 'user'")
  private String role;

  @Column(nullable = false)
  private Boolean isExist;

}
