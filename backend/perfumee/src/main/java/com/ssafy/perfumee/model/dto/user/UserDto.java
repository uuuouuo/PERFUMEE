package com.ssafy.perfumee.model.dto.user;

import com.ssafy.perfumee.model.entity.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserDto {

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class SignUpReq {

    private String id;
    private String password;
    private String email;
    private String nickname;
    private String gender;

    public SignUpReq(String id, String password, String email,
        String nickname, String gender) {
      this.id = id;
      this.password = password;
      this.email = email;
      this.nickname = nickname;
      this.gender = gender;
    }

  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class LoginReq {

    private String id;
    private String password;

    public LoginReq(String no, String id, String password) {
      this.id = id;
      this.password = password;
    }

  }

  @Getter
  @NoArgsConstructor(access = AccessLevel.PROTECTED)
  public static class UpdateReq {

    private String password;
    private String email;
    private String nickname;

    public UpdateReq(String password, String email, String nickname) {
      this.password = password;
      this.email = email;
      this.nickname = nickname;
    }

  }

  @Getter
  public static class UpdateRes {

    private String password;
    private String email;
    private String nickname;

    public UpdateRes(UpdateReq request) {
      this.password = request.getPassword();
      this.email = request.getEmail();
      this.nickname = request.nickname;
    }

  }

  @Getter
  public static class FindRes {

    private String id;
    private String email;
    private String nickname;
    private String gender;

    public FindRes(User user) {
      this.id = user.getId();
      this.email = user.getEmail();
      this.nickname = user.getNickname();
      this.gender = user.getGender();
    }

  }

}
