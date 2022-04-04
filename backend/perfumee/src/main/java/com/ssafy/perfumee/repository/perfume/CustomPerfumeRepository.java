package com.ssafy.perfumee.repository.perfume;

import com.ssafy.perfumee.model.dto.perfume.PerfumeDto.SearchDto;
import com.ssafy.perfumee.model.entity.perfume.Perfume;

import java.util.List;

public interface CustomPerfumeRepository {

    public List<Perfume> search(SearchDto searchDto);
}
