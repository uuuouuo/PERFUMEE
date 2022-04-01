package com.ssafy.perfumee.controller;

import com.ssafy.perfumee.config.jwt.JwtProperties;
import com.ssafy.perfumee.model.dto.user.UserDto.FindRes;
import com.ssafy.perfumee.model.dto.user.UserDto.LoginReq;
import com.ssafy.perfumee.model.dto.user.UserDto.SignUpReq;
import com.ssafy.perfumee.model.dto.user.UserDto.UpdateReq;
import com.ssafy.perfumee.model.dto.user.UserDto.UpdateRes;
import com.ssafy.perfumee.service.user.UserService;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;


  @PostMapping(value = "/signup", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  public void  createUser(@RequestPart(value = "request") SignUpReq signUpReq,
      @RequestPart(value = "image") MultipartFile image) {

    userService.signUp(signUpReq, image);

  }

  @PostMapping(value = "/login")
  public ResponseEntity<String> loginUser(@RequestBody LoginReq loginReq, HttpServletResponse response) {

    String jwtToken = userService.login(loginReq);

    response.addHeader(JwtProperties.HEADER_STRING,
        JwtProperties.TOKEN_PREFIX + jwtToken);

    return ResponseEntity.ok().body("JWT 생성 완료");

  }

  @PostMapping(value = "/refresh")
  public ResponseEntity<String> refresh(@RequestBody LoginReq loginReq, HttpServletResponse response) {

    String jwtToken = userService.updateToken(loginReq.getId());

    response.addHeader(JwtProperties.HEADER_STRING,
        JwtProperties.TOKEN_PREFIX + jwtToken);

    return ResponseEntity.ok().body("JWT 재생성 완료");

  }

  @GetMapping(value = "/{userId}")
  public ResponseEntity<FindRes> findUser (@PathVariable("userId") String id) {

    FindRes response = userService.searchUser(id);
    return new ResponseEntity<>(response, HttpStatus.OK);

  }

  @PutMapping(value = "/{userId}", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<UpdateRes> updateUser (
      @PathVariable("userId") String id,
      @RequestPart(value="request") UpdateReq request,
      @RequestPart(value="image") MultipartFile image) {

    UpdateRes response = userService.editUser(id, request, image);
    return new ResponseEntity<>(response, HttpStatus.OK);

  }

  @DeleteMapping("{userId}")
  public ResponseEntity<String> deleteUser (@PathVariable("userId") String id) {

    userService.deleteUser(id);
    return ResponseEntity.ok().body("회원 탈퇴 완료");

  }

}
