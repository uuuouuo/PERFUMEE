package com.ssafy.perfumee;

import com.ssafy.perfumee.model.entity.perfume.Contain;
import com.ssafy.perfumee.model.entity.perfume.Note;
import com.ssafy.perfumee.model.entity.perfume.Perfume;
import com.ssafy.perfumee.model.entity.survey.Survey;
import com.ssafy.perfumee.repository.perfume.ContainRepository;
import com.ssafy.perfumee.repository.perfume.NoteRepository;
import com.ssafy.perfumee.repository.perfume.PerfumeRepository;
import com.ssafy.perfumee.repository.survey.SurveyRepository;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitDB {

  private final PerfumeRepository perfumeRepository;
  private final NoteRepository noteRepository;
  private final ContainRepository containRepository;
  private final SurveyRepository surveyRepository;

  @PostConstruct
  void init() throws FileNotFoundException {
    initAllNotes();
    initSurveyList();
    initPerfume();
  }

  void initAllNotes() throws FileNotFoundException {
    BufferedReader file = new BufferedReader(
        new FileReader("C:\\Users\\somyu\\Desktop\\특화PRJ_자료\\"
            + "perfume_data_reduction.csv"));
    String line = "";

    try {
      while ((line = file.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
        List<String> aLine = new ArrayList<>();
        String[] arr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.

        for(int i = 5; i < 8; i++) {
          String[] str = arr[i].split(" | ");
          for (String s : str) {

          }
        }

      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (file != null) {
          file.close();
        }
      } catch(IOException e) {
        e.printStackTrace();
      }
    }

  }

  void initSurveyList() throws FileNotFoundException {
    BufferedReader file = new BufferedReader(
        new FileReader("C:\\Users\\somyu\\Desktop\\특화PRJ_자료\\"
            + "best_notes.csv"));
    String line = "";

    try {
      while ((line = file.readLine()) != null) {
        List<String> aLine = new ArrayList<>();
        String[] arr = line.split(",");

        Optional<Note> note = noteRepository.findByName(arr[1]);
        Survey survey = new Survey(note.get(), arr[2]);
        surveyRepository.save(survey);
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (file != null) {
          file.close();
        }
      } catch(IOException e) {
        e.printStackTrace();
      }
    }

  }

  void initPerfume() throws FileNotFoundException {

    BufferedReader file = new BufferedReader(
        new FileReader("C:\\Users\\somyu\\Desktop\\특화PRJ_자료\\"
            + "perfume_data_reduction.csv"));
    String line = "";

    try {
      while ((line = file.readLine()) != null) {
        List<String> aLine = new ArrayList<>();
        String[] arr = line.split(",");

//        for (String s : arr) {
//          System.out.println(s);
//        }

        BigDecimal rating = new BigDecimal(arr[3]);
        Perfume perfume = new Perfume(arr[1], arr[2], rating, arr[4], arr[8]);
        perfumeRepository.save(perfume);

        for(int i = 5; i < 8; i++) {
          String[] str = arr[i].split(" | ");
          for (String s : str) {
            Optional<Note> note = noteRepository.findByName(s);

            String type = "";
            if(i == 5) {
              type = "top";
            } else if(i == 6) {
              type = "middle";
            } else if(i == 7) {
              type = "base";
            }
            Contain contain = new Contain(perfume, type, note.get());
            containRepository.save(contain);

          }
        }
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (file != null) {
          file.close();
        }
      } catch(IOException e) {
        e.printStackTrace();
      }
    }

  }

}
