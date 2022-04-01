package com.ssafy.perfumee.service.review;


import com.ssafy.perfumee.model.dto.review.ReviewDto.ReviewReq;
import com.ssafy.perfumee.model.dto.review.ReviewDto.ReviewRes;
import com.ssafy.perfumee.model.dto.review.ReviewDto.UpdateReviewRes;
import com.ssafy.perfumee.model.dto.review.ReviewDto.UpdateReviewReq;
import com.ssafy.perfumee.model.entity.perfume.Perfume;
import com.ssafy.perfumee.model.entity.review.Review;
import com.ssafy.perfumee.model.entity.user.User;
import com.ssafy.perfumee.repository.perfume.PerfumeRepository;
import com.ssafy.perfumee.repository.review.ReviewRepository;
import com.ssafy.perfumee.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PerfumeRepository perfumeRepository;

    public ReviewRes writeReview(ReviewReq request){
        User user = userRepository.findById(request.getUser().getNo()).get();
        Perfume perfume = perfumeRepository.findByNo(request.getPerfume().getNo()).get();
        Review review = new Review(user, perfume, request.getContent(), request.getRating());
        reviewRepository.save(review);
        ReviewRes reviewRes = new ReviewRes(review.getNo(), review.getUser(), review.getPerfume(), review.getContent(), review.getRating(), review.getRegDate(), review.getUpdateDate());
        return reviewRes;
    }

    public List<ReviewRes> getReview(Integer perfumeNo){
        Perfume perfume = perfumeRepository.findByNo(perfumeNo).get();
        List<Review> reviewList = reviewRepository.findByPerfumeAndIsExist(perfume, true);
        List<ReviewRes> reviewResList = new ArrayList<>();
        for(Review review : reviewList){
            ReviewRes reviewRes = new ReviewRes(review.getNo(), review.getUser(), review.getPerfume(), review.getContent(), review.getRating(), review.getRegDate(), review.getUpdateDate());
            reviewResList.add(reviewRes);
        }
        return reviewResList;
    }


    public UpdateReviewRes updateReview(Integer reviewNo, UpdateReviewReq request){
        Review review = reviewRepository.findByNo(reviewNo).get();
        review.updateReview(request.getContent(), request.getRating());
        UpdateReviewRes updateReviewRes = new UpdateReviewRes(review.getNo(), review.getUser(), review.getPerfume(), review.getContent(), review.getRating(), review.getRegDate(), review.getUpdateDate());
        return updateReviewRes;
    }

    public void deleteReview(Integer reviewNo){
        Review review = reviewRepository.findByNo(reviewNo).get();
        review.deleteReview();
    }
}
