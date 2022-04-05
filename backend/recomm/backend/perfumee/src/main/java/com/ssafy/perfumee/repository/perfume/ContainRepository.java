package com.ssafy.perfumee.repository.perfume;

import com.ssafy.perfumee.model.entity.perfume.Contain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContainRepository extends JpaRepository<Contain, Integer> {
    Optional<List<Contain>> findByPerfumeNo(Integer perfumeNo);
}
