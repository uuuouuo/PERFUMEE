package com.ssafy.perfumee.service.review;


import com.ssafy.perfumee.model.dto.review.ReviewDto;
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
import com.ssafy.perfumee.service.validation.ValidateExist;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final PerfumeRepository perfumeRepository;
    private ValidateExist validateExist = new ValidateExist();

    public ReviewRes writeReview(ReviewReq request){
        User user = userRepository.findById(request.getUserId()).get();
        Optional<Perfume> perfumeOptional = perfumeRepository.findByNo(request.getPerfumeNo());
        Perfume perfume = validateExist.findPerfume(perfumeOptional);
        Review review = new Review(user, perfume, request.getContent(), request.getRating());
        reviewRepository.save(review);
        ReviewRes reviewRes = new ReviewRes(review.getNo(), review.getUser(), review.getPerfume(), review.getContent(), review.getRating(), review.getRegDate(), review.getUpdateDate());
        return reviewRes;
    }

    public List<ReviewRes> getReview(Integer perfumeNo){
        Optional<Perfume> perfumeOptional = perfumeRepository.findByNo(perfumeNo);
        Perfume perfume = validateExist.findPerfume(perfumeOptional);
        Optional<List<Review>> reviewListOptional = reviewRepository.findByPerfumeAndIsExist(perfume, true);
        List<Review> reviewList = validateExist.findReviewList(reviewListOptional);
        List<ReviewRes> reviewResList = new ArrayList<>();
        for(Review review : reviewList){
            ReviewRes reviewRes = new ReviewRes(review.getNo(), review.getUser(), review.getPerfume(), review.getContent(), review.getRating(), review.getRegDate(), review.getUpdateDate());
            reviewResList.add(reviewRes);
        }
        return reviewResList;
    }

    public Boolean check(Integer reviewNo, ReviewDto.checkReviewReq request){
        Optional<Review> reviewOptional = reviewRepository.findByNo(reviewNo);
        Review review = validateExist.findReview(reviewOptional);
        String authUserId = review.getUser().getId();
        if(authUserId.equals(request.getUserId()))
            return true;
        else
            return false;
    }

    public UpdateReviewRes updateReview(Integer reviewNo, UpdateReviewReq request){
        Optional<Review> reviewOptional = reviewRepository.findByNo(reviewNo);
        Review review = validateExist.findReview(reviewOptional);
        review.updateReview(request.getContent(), request.getRating());
        UpdateReviewRes updateReviewRes = new UpdateReviewRes(review.getNo(), review.getUser(), review.getPerfume(), review.getContent(), review.getRating(), review.getRegDate(), review.getUpdateDate());
        return updateReviewRes;
    }

    public void deleteReview(Integer reviewNo){
        Optional<Review> reviewOptional = reviewRepository.findByNo(reviewNo);
        Review review = validateExist.findReview(reviewOptional);
        review.deleteReview();
    }
}
