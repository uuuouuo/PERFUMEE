//package com.ssafy.perfumee;
//
//import com.ssafy.perfumee.repository.perfume.NoteRepository;
//import com.ssafy.perfumee.repository.perfume.PerfumeRepository;
//import java.io.BufferedReader;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import javax.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class InitDB {
//
//  private final PerfumeRepository perfumeRepository;
//  private final NoteRepository noteRepository;
//
//  @PostConstruct
//  void initPerfume() throws FileNotFoundException {
//
//    BufferedReader file = new BufferedReader(
//        new FileReader("C:\\Users\\somyu\\Desktop\\특화PRJ_자료\\"
//            + "perfume_data_reduction.csv"));
//    String line = "";
//
//    try {
//      while ((line = file.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
//        List<String> aLine = new ArrayList<>();
//        String[] lineArr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
//
//        for (String s : lineArr) {
////          System.out.println(s);
//
//        }
//
//
//      }
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    } finally {
//      try {
//        if (file != null) {
//          file.close(); // 사용 후 BufferedReader를 닫아준다.
//        }
//      } catch(IOException e) {
//        e.printStackTrace();
//      }
//    }
//
//  }
//
//}
