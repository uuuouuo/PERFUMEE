package com.ssafy.perfumee.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.perfumee.model.entity.user.User;
import com.ssafy.perfumee.repository.user.UserRepository;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class Validation {

  private final JwtProvider jwtProvider;
  private final UserRepository userRepository;

  public boolean check(String jwtToken) {
    return isExpired(jwtToken) && isExist(jwtToken);
  }

  public boolean isExpired(String jwtToken){
    Date expiresAt = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
        .build().verify(jwtToken).getExpiresAt();

    if(expiresAt.compareTo(new Date()) < 0) {
      return false;
    }
    return true;
  }

  public boolean isExist(String jwtToken){
    String id = jwtProvider.getId(jwtToken);
    Optional<User> userOptional = userRepository.findById(id);

    if(userOptional.isEmpty()) {
      return false;
    }
    return true;
  }

  public User findUser(Optional<User> optional) {

    User user;
    if (optional.isPresent()) {
      user = optional.get();
    } else {
      throw new IllegalStateException("존재하지 않은 회원입니다.");
    }
    return user;
  }

}
