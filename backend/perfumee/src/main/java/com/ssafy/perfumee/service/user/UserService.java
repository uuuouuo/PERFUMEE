package com.ssafy.perfumee.service.user;

import com.ssafy.perfumee.config.jwt.JwtProvider;
import com.ssafy.perfumee.model.dto.recom.Notes;
import com.ssafy.perfumee.model.dto.recom.Recom;
import com.ssafy.perfumee.model.dto.user.UserDto.FindRes;
import com.ssafy.perfumee.model.dto.user.UserDto.LoginReq;
import com.ssafy.perfumee.model.dto.user.UserDto.SignUpReq;
import com.ssafy.perfumee.model.dto.user.UserDto.UpdateReq;
import com.ssafy.perfumee.model.dto.user.UserDto.UpdateRes;
import com.ssafy.perfumee.model.entity.perfume.Note;
import com.ssafy.perfumee.model.entity.perfume.Perfume;
import com.ssafy.perfumee.model.entity.user.Taste;
import com.ssafy.perfumee.model.entity.user.User;
import com.ssafy.perfumee.repository.perfume.NoteRepository;
import com.ssafy.perfumee.repository.perfume.PerfumeRepository;
import com.ssafy.perfumee.repository.taste.TasteRepository;
import com.ssafy.perfumee.repository.user.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

  private final UserRepository userRepository;
  private final NoteRepository noteRepository;
  private final PerfumeRepository perfumeRepository;
  private final TasteRepository tasteRepository;
  private final JwtProvider jwtProvider;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  @Transactional
  public void signUp(SignUpReq request) {

    String rawPassword = request.getPassword();
    String encPassword = bCryptPasswordEncoder.encode(rawPassword);
//    String imageUrl = getImage(image, request.getId());

    User user = new User();
    user.createUser(request, encPassword);

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
  public UpdateRes editUser(String id, UpdateReq request) {

    User user = getUser(id);
    String rawPassword = request.getPassword();
    String encPassword = bCryptPasswordEncoder.encode(rawPassword);

//    String imageUrl = getImage(image, id);
//    user.changeImage(imageUrl);

    user.updateUser(request, encPassword);

    UpdateRes response = new UpdateRes(request);

    return response;

  }

  @Transactional
  public void deleteUser(String id) {

    User user = getUser(id);
    user.deleteUser();

  }

  public List<String> getNotes() {

    List<Note> noteList = noteRepository.findAll();

    List<String> noteNames = new ArrayList<>();
    noteList.stream().forEach(n -> noteNames.add(n.getName()));

    return noteNames;

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

//  public String getImage(MultipartFile image, String id) {
//
//    String imageUrl = "";
//    if(image.isEmpty()){
//      imageUrl = "C://images/profile/basic_profile.png";
//    }
//    else {
//      String fileName = "C://images/profile/" + id + ".png";
//
//      File dest = new File(fileName);
//      try {
//        image.transferTo(dest);
//        imageUrl = fileName;
//      } catch (IllegalStateException e) {
//        e.printStackTrace();
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//
//    return imageUrl;
//
//  }

  @Transactional
  public void setRecomm(Notes notes){

    User user = userRepository.findById(notes.getUserId()).get();

    for(int note : notes.getNoteIds()) {
      Taste taste = new Taste();
      Note tasteNote = noteRepository.findById(note).get();
      taste.createTaste(user,tasteNote);
      tasteRepository.save(taste);
    }
  }

  public List<Perfume> getRecomm(String userId){

    User user = userRepository.findById(userId).get();

    ArrayList<String> notesName = new ArrayList<>();
    List<Taste> personalTaste = tasteRepository.findAllByUser(user);

    for(Taste taste : personalTaste){
      String note = taste.getNoteName();
      notesName.add(note);
    }

    Recom perfumes = recomPerfumes(notesName);

    List<Perfume> recomPerfume = new ArrayList<>();
    for(int perfumeId : perfumes.getPerfumes()) {
      Perfume perfume = perfumeRepository.findByNo(perfumeId).get();
      recomPerfume.add(perfume);
    }

    return recomPerfume;
  }

  public Recom recomPerfumes(ArrayList<String> notes_names){

//    ArrayList<Integer> notes_name = notes.getNoteIds();

    NotesDto notes = new NotesDto();
    notes.setNotes_names(notes_names);

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    WebClient webClient = WebClient.builder()
        .baseUrl("j6c101.p.ssafy.io:8081")
        .build();

    Recom perfumes = webClient.post()
        .uri("j6c101.p.ssafy.io:8000/perfume/recomm")
        .accept(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(notes))
        .retrieve()
        //     아래의 onStatus는 error handling
        .bodyToMono(Recom.class) // KAKAO의 유저 정보를 넣을 Dto 클래스
        .block();

    return perfumes;
  }

  @Data
  public static class NotesDto{
    ArrayList<String> notes_names;

  }

}
