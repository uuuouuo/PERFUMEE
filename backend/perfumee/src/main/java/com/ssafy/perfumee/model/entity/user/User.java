package com.ssafy.perfumee.model.entity.user;

import com.ssafy.perfumee.model.dto.user.UserDto.SignUpReq;
import com.ssafy.perfumee.model.dto.user.UserDto.UpdateReq;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Getter
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

  public void createUser(SignUpReq request, String password) {
    this.id = request.getId();
    this.password = password;
    this.nickname = request.getNickname();
    this.email = request.getEmail();
//    this.image = image;
    this.gender = request.getGender();
    this.role = "user";
    this.isExist = true;
  }

  public void updateUser(UpdateReq request, String password) {
    this.password = password;
    this.nickname = request.getNickname();
    this.email = request.getEmail();
  }

//  public void changeImage(String image){
//    this.image = image;
//  }

  public void deleteUser(){
    this.isExist = false;
  }

}
