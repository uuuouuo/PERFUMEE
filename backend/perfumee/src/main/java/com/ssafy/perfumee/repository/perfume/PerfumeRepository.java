package com.ssafy.perfumee.repository.perfume;

import com.ssafy.perfumee.model.entity.perfume.Perfume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfumeRepository extends JpaRepository<Perfume, Integer> {

    Optional<Perfume> findByNo(Integer perfumeNo);
    Optional<Page<Perfume>> findByNameLike(String name, Pageable pageable);
    Optional<Page<Perfume>> findByBrandLike(String brand, Pageable pageable);
    Optional<Page<Perfume>> findByGenderOrderByRatingDesc(String gender, Pageable pageable);
}
