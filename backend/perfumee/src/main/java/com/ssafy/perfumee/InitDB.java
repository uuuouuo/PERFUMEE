//package com.ssafy.perfumee;
//
//import com.ssafy.perfumee.model.entity.perfume.Contain;
//import com.ssafy.perfumee.model.entity.perfume.Note;
//import com.ssafy.perfumee.model.entity.perfume.Perfume;
//import com.ssafy.perfumee.model.entity.survey.Survey;
//import com.ssafy.perfumee.repository.perfume.ContainRepository;
//import com.ssafy.perfumee.repository.perfume.NoteRepository;
//import com.ssafy.perfumee.repository.perfume.PerfumeRepository;
//import com.ssafy.perfumee.repository.survey.SurveyRepository;
//import java.io.BufferedReader;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Optional;
//import java.util.Set;
//import javax.annotation.PostConstruct;
//import lombok.RequiredArgsConstructor;
//import org.apache.poi.xssf.usermodel.XSSFCell;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class InitDB {
//
//  private final PerfumeRepository perfumeRepository;
//  private final NoteRepository noteRepository;
//  private final ContainRepository containRepository;
//  private final SurveyRepository surveyRepository;
//
//  @PostConstruct
//  void init() throws IOException {
//    initAllNotes();
//    initSurveyList();
//    initPerfume();
//  }
//
//  void initAllNotes() throws FileNotFoundException {
//    BufferedReader file = new BufferedReader(
//        new FileReader("C:\\Users\\somyu\\Desktop\\특화PRJ_자료\\"
//            + "notes.csv"));
//    String line = "";
//
//    try {
//      Set<String> set = new HashSet<>();
//      while ((line = file.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
//        List<String> aLine = new ArrayList<>();
//        String[] arr = line.split(","); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
//
//        for (String s : arr) {
//          set.add(s.replace("\"", ""));
//        }
//
//        for (String s : set) {
//          Note note = new Note(s);
//          noteRepository.save(note);
//        }
//
//      }
//
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    } finally {
//      try {
//        if (file != null) {
//          file.close();
//        }
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//
//  }
//
//  void initSurveyList() throws FileNotFoundException {
//    BufferedReader file = new BufferedReader(
//        new FileReader("C:\\Users\\somyu\\Desktop\\특화PRJ_자료\\"
//            + "best_notes.csv"));
//    String line = "";
//
//    try {
//
//      while ((line = file.readLine()) != null) {
//        List<String> aLine = new ArrayList<>();
//        String[] arr = line.split(",");
//
//        for (String s : arr) {
//          System.out.println(s);
//        }
//
//        Optional<Note> note = noteRepository.findByNameLike(arr[1].trim().toLowerCase());
//        Survey survey = new Survey(note.get(), arr[2]);
//        surveyRepository.save(survey);
//      }
//
//    } catch (FileNotFoundException e) {
//      e.printStackTrace();
//    } catch (IOException e) {
//      e.printStackTrace();
//    } finally {
//      try {
//        if (file != null) {
//          file.close();
//        }
//      } catch (IOException e) {
//        e.printStackTrace();
//      }
//    }
//
//  }
//
//  void initPerfume() throws IOException {
//
//    FileInputStream file = new FileInputStream("C:\\Users\\somyu\\Desktop\\특화PRJ_자료\\"
//        + "perfume_data_reduction.xlsx");
//    XSSFWorkbook workbook = new XSSFWorkbook(file); // 엑셀 파일 파싱
//
//    XSSFSheet sheet = workbook.getSheetAt(0); // 엑셀 파일의 첫번째 (0) 시트지
//    int rows = sheet.getPhysicalNumberOfRows(); // 행의 수
//
//    for (int r = 0; r < rows; r++) {
//      XSSFRow row = sheet.getRow(r);
//
//      String name = "";
//      String brand = "";
//      BigDecimal rating = BigDecimal.valueOf(0);
//      String gender = "";
//      String top = "";
//      String middle = "";
//      String base = "";
//      String image = "";
//
//      if (row != null) { // 행이 비어있지 않을 때
//        int cells = row.getPhysicalNumberOfCells(); // 열의 수
//
//        for (int c = 0; c < cells; c++) {
//          XSSFCell cell = row.getCell(c);
//          String value = "";
//
//          if (cell == null) { // r열 c행의 cell이 비어있을 때
//            continue;
//          } else { // 타입별로 내용 읽기
//            switch (cell.getCellType()) {
//              case FORMULA:
//                value = cell.getCellFormula();
//                break;
//              case NUMERIC:
//                value = cell.getNumericCellValue() + "";
//                break;
//              case STRING:
//                value = cell.getStringCellValue() + "";
//                break;
//              case BLANK:
//                value = cell.getBooleanCellValue() + "";
//                break;
//              case ERROR:
//                value = cell.getErrorCellValue() + "";
//                break;
//            }
//
//          }
//
//          if (c == 1) {
//            name = value;
//          } else if(c == 2) {
//            brand = value;
//          } else if(c == 3) {
//            rating = new BigDecimal(value);
//          } else if(c == 4) {
//            gender = value;
//          } else if (c == 5) {
//            top = value;
//          } else if (c == 6) {
//            middle = value;
//          } else if (c == 7) {
//            base = value;
//          } else if (c == 8) {
//            image = value;
//          }
//          System.out.println(r + "번 행 : " + c + "번 열 값은: " + value);
//        }
//
//        Perfume perfume = new Perfume(name, brand, rating, gender, image);
//        perfumeRepository.save(perfume);
//
//        String[] strT = top.replace(" | ","|").split("\\|");
//        for (String s : strT) {
//          if(s.isEmpty()) continue;
//          Optional<Note> note = noteRepository.findByName(s.trim().toLowerCase().replace("\"", ""));
//          Contain contain = new Contain(perfume, "top", note.get());
//          containRepository.save(contain);
//        }
//
//        String[] strM = middle.replace(" | ","|").split("\\|");
//        for (String s : strM) {
//          if(s.isEmpty()) continue;
//          Optional<Note> note = noteRepository.findByName(s.trim().toLowerCase().replace("\"", ""));
//          Contain contain = new Contain(perfume, "top", note.get());
//          containRepository.save(contain);
//        }
//
//        String[] strB = base.replace(" | ","|").split("\\|");
//        for (String s : strB) {
//          if(s.isEmpty()) continue;
//          Optional<Note> note = noteRepository.findByName(s.trim().toLowerCase().replace("\"", ""));
//          Contain contain = new Contain(perfume, "top", note.get());
//          containRepository.save(contain);
//        }
//
//      }
//
//    }
//
//    //      BufferedReader file = new BufferedReader(
////          new FileReader("C:\\Users\\somyu\\Desktop\\특화PRJ_자료\\"
////              + "perfume_data_reduction.cell"));
////    String line = "";
//
////    try {
//
////      while ((line = file.readLine()) != null) {
////        List<String> aLine = new ArrayList<>();
////        String[] arr = line.split(",");
////
////        System.out.println("============");
////        System.out.println(arr[3]);
////        System.out.println("============");
////
////        BigDecimal rating = new BigDecimal(arr[3]);
////        Perfume perfume = new Perfume(arr[1], arr[2], rating, arr[4], arr[8]);
////        perfumeRepository.save(perfume);
////
////        for(int i = 5; i < 8; i++) {
////          arr[i] = arr[i].replace(" | ","|");
////          String[] str = arr[i].split("\\|");
////
////          for (String s : str) {
////            if(s.isEmpty()) continue;
////            Optional<Note> note = noteRepository.findByName(s.trim().toLowerCase());
////
////            String type = "";
////            if(i == 5) {
////              type = "top";
////              top.put(note.get(), perfume);
////            } else if(i == 6) {
////              type = "middle";
////              middle.put(note.get(), perfume);
////            } else if(i == 7) {
////              type = "base";
////              base.put(note.get(), perfume);
////            }
////          }
////
////        }
////      }
//
////      Contain contain = new Contain(perfume, type, note.get());
////      containRepository.save(contain);
//
////    } catch (FileNotFoundException e) {
////      e.printStackTrace();
////    } catch (IOException e) {
////      e.printStackTrace();
////    } finally {
////      try {
////        if (file != null) {
////          file.close();
////        }
////      } catch(IOException e) {
////        e.printStackTrace();
////      }
////    }
//
//  }
//
//}
