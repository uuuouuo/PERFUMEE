package com.ssafy.perfumee.service.perfume;

import com.ssafy.perfumee.model.dto.perfume.NoteDto.NoteRes;
import com.ssafy.perfumee.model.dto.perfume.PerfumeDto.SearchDto;
import com.ssafy.perfumee.model.dto.perfume.PerfumeDto.PerfumePagingRes;
import com.ssafy.perfumee.model.dto.perfume.PerfumeDto.PerfumeRes;
import com.ssafy.perfumee.model.entity.perfume.Contain;
import com.ssafy.perfumee.model.entity.perfume.Note;
import com.ssafy.perfumee.model.entity.perfume.Perfume;
import com.ssafy.perfumee.repository.perfume.ContainRepository;
import com.ssafy.perfumee.repository.perfume.CustomPerfumeRepositoryImpl;
import com.ssafy.perfumee.repository.perfume.NoteRepository;
import com.ssafy.perfumee.repository.perfume.PerfumeRepository;
import com.ssafy.perfumee.service.validation.ValidateExist;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class PerfumeService {
    private final PerfumeRepository perfumeRepository;
    private final CustomPerfumeRepositoryImpl customPerfumeRepository;
    private final ContainRepository containRepository;
    private final NoteRepository noteRepository;

    private ValidateExist validateExist = new ValidateExist();

    public PerfumeRes getDetail(Integer perfumeNo){
        Optional<Perfume> perfumeOptional = perfumeRepository.findByNo(perfumeNo);
        Perfume perfume = validateExist.findPerfume(perfumeOptional);
        Optional<List<Contain>> containOptional = containRepository.findByPerfumeNo(perfume.getNo());
        List<Contain> contains = validateExist.findContainList(containOptional);
        List<NoteRes> notes = new ArrayList<>();
        for(Contain contain : contains) {
            Note note = noteRepository.findByNo(contain.getNote().getNo()).get();
            NoteRes addNote = new NoteRes(contain.getType(), note.getName());
            notes.add(addNote);
        }
        PerfumeRes perfumeRes = new PerfumeRes(
                perfume.getNo(), perfume.getName(), perfume.getBrand(),
                perfume.getGender(), perfume.getImage(), perfume.getRating(), notes);

        return perfumeRes;
    }

    public Page<PerfumePagingRes> getGenderList(String gender, Pageable pageable){
        Optional<Page<Perfume>> perfumeOptional = perfumeRepository.findByGenderOrderByRatingDesc(gender, pageable);
        Page<Perfume> perfumes = validateExist.findPagePerfume(perfumeOptional);
        Page<PerfumePagingRes> perfumeResPage = perfumes.map(
                perfume -> new PerfumePagingRes(
                        perfume.getNo(),perfume.getName(),
                        perfume.getBrand(),perfume.getGender(),
                        perfume.getImage(),perfume.getRating()
                )
        );
        return perfumeResPage;
    }

    public Page<PerfumePagingRes> getSearchList(String type, String value, Pageable pageable){
        Optional<Page<Perfume>> perfumeOptional;
        if(type.equals("name")){
            perfumeOptional = perfumeRepository.findByNameLike(value, pageable);
        } else if(type.equals("brand")){
            perfumeOptional = perfumeRepository.findByBrandLike(value, pageable);
        } else {
            throw new IllegalStateException("잘못된 형식입니다.");
        }

        Page<Perfume> perfumes = validateExist.findPagePerfume(perfumeOptional);
        Page<PerfumePagingRes> perfumeResPage = perfumes.map(
                perfume -> new PerfumePagingRes(
                        perfume.getNo(),perfume.getName(),
                        perfume.getBrand(),perfume.getGender(),
                        perfume.getImage(),perfume.getRating()
                )
        );
        return perfumeResPage;
    }

    public List<PerfumePagingRes> getList(SearchDto searchDto){
        List<Perfume> perfumes = customPerfumeRepository.search(searchDto);
        List<PerfumePagingRes> response = new ArrayList<>();
        for(Perfume perfume : perfumes) {
                PerfumePagingRes perfumePagingRes = new PerfumePagingRes(
                        perfume.getNo(),perfume.getName(),
                        perfume.getBrand(),perfume.getGender(),
                        perfume.getImage(),perfume.getRating()
                );
                response.add(perfumePagingRes);
        }
        return response;
    }
}
