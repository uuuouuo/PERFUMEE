package com.ssafy.perfumee.repository.review;

import com.ssafy.perfumee.model.entity.perfume.Perfume;
import com.ssafy.perfumee.model.entity.review.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    Optional<Review> findByNo(Integer reviewNo);
    List<Review> findByPerfumeAndIsExist(Perfume perfume, Boolean isExist);
}
