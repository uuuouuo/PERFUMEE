package com.ssafy.perfumee.service.user;

import com.ssafy.perfumee.config.jwt.JwtProvider;
import com.ssafy.perfumee.model.dto.user.UserDto.FindRes;
import com.ssafy.perfumee.model.dto.user.UserDto.LoginReq;
import com.ssafy.perfumee.model.dto.user.UserDto.SignUpReq;
import com.ssafy.perfumee.model.dto.user.UserDto.UpdateReq;
import com.ssafy.perfumee.model.dto.user.UserDto.UpdateRes;
import com.ssafy.perfumee.model.entity.user.User;
import com.ssafy.perfumee.repository.user.UserRepository;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;
  private final JwtProvider jwtProvider;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Transactional
  public void signUp(SignUpReq request, MultipartFile image) {

    String rawPassword = request.getPassword();
    String encPassword = bCryptPasswordEncoder.encode(rawPassword);

    String imageUrl = getImage(image, request.getId());

    User user = new User();
    user.createUser(request, encPassword, imageUrl);

    userRepository.save(user);

  }

  public String login(LoginReq request) {

    Optional<User> userOptional = userRepository.findById(request.getId());
    User user = getUser(request.getId());

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    if(!bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
      throw new IllegalStateException("비밀번호 일치하지 않습니다.");
    }

    String jwtToken = jwtProvider.createToken(user);

    return jwtToken;

  }

  public String updateToken(String id) {

    User user = getUser(id);
    String newToken = jwtProvider.createToken(user);

    return newToken;

  }

  public FindRes searchUser(String id) {

    User user = getUser(id);
    FindRes response = new FindRes(user);

    return response;

  }

  @Transactional
  public UpdateRes editUser(String id, UpdateReq request, MultipartFile image) {

    User user = getUser(id);

    String imageUrl = getImage(image, id);
    user.changeImage(imageUrl);

    UpdateRes response = new UpdateRes(request, imageUrl);

    return response;

  }

  @Transactional
  public void deleteUser(String id) {

    User user = getUser(id);
    user.deleteUser();

  }

  public User getUser(String id) {

    Optional<User> optional = userRepository.findById(id);
    User user;

    if (optional.isPresent()) {
      user = optional.get();
    } else {
      throw new IllegalStateException("존재하지 않은 회원입니다.");
    }

    return user;

  }

  public String getImage(MultipartFile image, String id) {

    String imageUrl = "";
    if(image.isEmpty()){
      imageUrl = "C://images/profile/basic_profile.png";
    }
    else {
      String fileName = "C://images/profile/" + id + ".png";

      File dest = new File(fileName);
      try {
        image.transferTo(dest);
        imageUrl = fileName;
      } catch (IllegalStateException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    return imageUrl;

  }

}
