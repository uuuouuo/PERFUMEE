package com.ssafy.perfumee.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.ssafy.perfumee.model.entity.user.User;
import com.ssafy.perfumee.repository.user.UserRepository;
import java.util.Date;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtProvider {

  private final UserRepository userRepository;
  private Validation validation;

  public String createToken(User user) {
    String jwtToken = JWT.create()
        .withSubject("JWT토큰")
        .withExpiresAt(new Date(System.currentTimeMillis()
            + JwtProperties.EXPIRATION_TIME))
        .withClaim("id", user.getId())
        .sign(Algorithm.HMAC512(JwtProperties.SECRET));

    return jwtToken;
  }

  public String getId(String jwtToken) {
    String id = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET))
        .build().verify(jwtToken).getClaim("id").asString();

    return id;
  }

  public Authentication getAuthentication(String jwtToken) {
    if(validation.check(jwtToken)) {
      String userId = getId(jwtToken);
      Optional<User> userOptional = userRepository.findById(userId);
      User user = validation.findUser(userOptional);

      PrincipalDetails principalDetails = new PrincipalDetails(user);

      return new UsernamePasswordAuthenticationToken(principalDetails, jwtToken);
    }
    return null;
  }

}
