package com.ssafy.perfumee.service.review;

import com.ssafy.perfumee.model.entity.perfume.Perfume;
import com.ssafy.perfumee.model.entity.review.Review;
import com.ssafy.perfumee.model.entity.user.User;
import com.ssafy.perfumee.repository.perfume.PerfumeRepository;
import com.ssafy.perfumee.repository.review.ReviewRepository;
import com.ssafy.perfumee.repository.user.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ReviewServiceTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PerfumeRepository perfumeRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    //@Rollback(false)
    public void 리뷰작성() throws Exception {
        User user = userRepository.findById(1).get();
        Perfume perfume = perfumeRepository.findByNo(1).get();
        String content = "저장되나";
        BigDecimal rating = BigDecimal.valueOf(3.5);
        Review review = new Review(user, perfume, content, rating);
        reviewRepository.save(review);
    }

    @Test
    //@Rollback(false)
    public void 리뷰수정() throws Exception {
        // test 시 아래 reviewNo 값 변경해야함
        Review review = reviewRepository.findByNo(4).get();
        String content = "수정되나";
        BigDecimal rating = BigDecimal.valueOf(2.5);
        review.updateReview(content, rating);
    }

    @Test
    //@Rollback(false)
    public void 리뷰삭제() throws Exception {
        // test 시 아래 reviewNo 값 변경해야함
        Review review = reviewRepository.findByNo(5).get();
        review.deleteReview();
    }
}