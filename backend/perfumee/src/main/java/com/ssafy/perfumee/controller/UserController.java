package com.ssafy.perfumee.controller;

import com.ssafy.perfumee.config.jwt.JwtProperties;
import com.ssafy.perfumee.model.dto.recom.Notes;
import com.ssafy.perfumee.model.dto.user.UserDto.FindRes;
import com.ssafy.perfumee.model.dto.user.UserDto.LoginReq;
import com.ssafy.perfumee.model.dto.user.UserDto.SignUpReq;
import com.ssafy.perfumee.model.dto.user.UserDto.UpdateReq;
import com.ssafy.perfumee.model.dto.user.UserDto.UpdateRes;
import com.ssafy.perfumee.model.entity.perfume.Perfume;
import com.ssafy.perfumee.service.user.UserService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping(value = "/signup")
  public void  createUser(@RequestBody SignUpReq signUpReq) {

    userService.signUp(signUpReq);

  }

  @PostMapping(value = "/login")
  public ResponseEntity<String> loginUser(@RequestBody LoginReq loginReq, HttpServletResponse response) {

    String jwtToken = userService.login(loginReq);

    response.addHeader(JwtProperties.HEADER_STRING,
        JwtProperties.TOKEN_PREFIX + jwtToken);

    return ResponseEntity.ok().body(jwtToken);

  }

  @PostMapping(value = "/refresh")
  public ResponseEntity<String> refresh(@RequestBody LoginReq loginReq, HttpServletResponse response) {

    String jwtToken = userService.updateToken(loginReq.getId());

    response.addHeader(JwtProperties.HEADER_STRING,
        JwtProperties.TOKEN_PREFIX + jwtToken);

    return ResponseEntity.ok().body("JWT 재생성 완료");

  }

  @GetMapping("/{userId}")
  public ResponseEntity<FindRes> findUser (@PathVariable("userId") String id) {

    FindRes response = userService.searchUser(id);
    return new ResponseEntity<>(response, HttpStatus.OK);

  }

  @PutMapping("/{userId}")
  public ResponseEntity<UpdateRes> updateUser (
      @PathVariable("userId") String id, @RequestBody UpdateReq request) {

    UpdateRes response = userService.editUser(id, request);
    return new ResponseEntity<>(response, HttpStatus.OK);

  }

  @DeleteMapping("{userId}")
  public ResponseEntity<String> deleteUser (@PathVariable("userId") String id) {

    userService.deleteUser(id);
    return ResponseEntity.ok().body("회원 탈퇴 완료");

  }

  @GetMapping("/survey")
  public ResponseEntity<List<String>> findNotes () {

    List<String> notes = userService.getNotes();
    return new ResponseEntity<>(notes, HttpStatus.OK);

  }

  @PostMapping("/recomm")
  public ResponseEntity<String> findNotes (@RequestBody Notes notes) {

    userService.setRecomm(notes);
    return ResponseEntity.ok().body("관심 향 설정 완료");

  }

  @PostMapping("/recommper")
  public ResponseEntity<List<Perfume>> findperfumes (@RequestBody RecomUserId userId) {

    String id = userId.getUserId();
    List<Perfume> perfumes = userService.getRecomm(id);

    return new ResponseEntity<>(perfumes,HttpStatus.OK);

  }

  @Data
  public static class RecomUserId{

    String userId;

  }
}
