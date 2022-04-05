package com.ssafy.perfumee.controller;

import com.ssafy.perfumee.model.dto.perfume.PerfumeDto.SearchDto;
import com.ssafy.perfumee.model.dto.perfume.PerfumeDto.PerfumePagingRes;
import com.ssafy.perfumee.model.dto.perfume.PerfumeDto.PerfumeRes;
import com.ssafy.perfumee.service.perfume.PerfumeService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/perfume")
@RequiredArgsConstructor
public class PerfumeController {
    private final PerfumeService perfumeService;

    @GetMapping("")
    public ResponseEntity<Page<PerfumePagingRes>> getPerfumeList(
            @RequestParam(value = "brand", required = false) String brand, @RequestParam(value = "note", required = false) String note,
            @RequestParam(value = "gender", required = false) String gender) {
        Pageable pageable = PageRequest.of(0, 10);
        SearchDto searchDto = new SearchDto();

        if(brand!=null){
            List<String> brands = new ArrayList<>();
            String[] brandSplits = brand.split(",");
            for(String brandSplit : brandSplits){
                brands.add(brandSplit);
            }
            searchDto.setBrand(brands);
        }

        if(gender!=null){
            List<String> genders = new ArrayList<>();
            String[] genderSplits = gender.split(",");
            for(String genderSplit : genderSplits){
                genders.add(genderSplit);
            }
            searchDto.setGender(genders);
        }

        if(note!=null){
            List<String> notes = new ArrayList<>();
            String[] noteSplits = note.split(",");
            for(String noteSplit : noteSplits){
                notes.add(noteSplit);
            }
            searchDto.setNote(notes);
        }

        List<PerfumePagingRes> perfumes = perfumeService.getList(searchDto);
        Page<PerfumePagingRes> response = new PageImpl<>(perfumes, pageable, perfumes.size());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Page<PerfumePagingRes>> getPerfumeSearchList(
            @RequestParam("type") String type, @RequestParam("value") String value) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<PerfumePagingRes> response = perfumeService.getSearchList(type, value, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/detail/{perfumeNo}")
    public ResponseEntity<PerfumeRes> getPerfumeDetail(@PathVariable("perfumeNo") Integer perfumeNo) {
        PerfumeRes response = perfumeService.getDetail(perfumeNo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{gender}")
    public ResponseEntity<Page<PerfumePagingRes>> getPerfumeGenderList(@PathVariable String gender) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<PerfumePagingRes> response = perfumeService.getGenderList(gender, pageable);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
