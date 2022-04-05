package com.ssafy.perfumee.controller;


import com.ssafy.perfumee.model.dto.review.ReviewDto.checkReviewReq;
import com.ssafy.perfumee.model.dto.review.ReviewDto.ReviewReq;
import com.ssafy.perfumee.model.dto.review.ReviewDto.ReviewRes;
import com.ssafy.perfumee.model.dto.review.ReviewDto.UpdateReviewRes;
import com.ssafy.perfumee.model.dto.review.ReviewDto.UpdateReviewReq;
import com.ssafy.perfumee.service.review.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("")
    public ResponseEntity<ReviewRes> createReview(
            @RequestBody ReviewReq request) {

        ReviewRes response = reviewService.writeReview(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{perfumeNo}")
    public ResponseEntity<List<ReviewRes>> getReviewList(
            @PathVariable("perfumeNo") Integer perfumeNo) {
        List<ReviewRes> response = reviewService.getReview(perfumeNo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/check/{reviewNo}")
    public ResponseEntity<String> updateCheck(
            @PathVariable("reviewNo") Integer reviewNo,
            @RequestBody checkReviewReq request) {

        boolean valid = reviewService.check(reviewNo, request);
        if(valid)
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        else
            return new ResponseEntity<>("FAIL", HttpStatus.BAD_REQUEST);
    }


    @PutMapping("/{reviewNo}")
    public ResponseEntity<UpdateReviewRes> updateReview(
            @PathVariable("reviewNo") Integer reviewNo,
            @RequestBody UpdateReviewReq request) {

        UpdateReviewRes response = reviewService.updateReview(reviewNo, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{reviewNo}")
    public void deleteReview(
            @PathVariable("reviewNo") Integer reviewNo) {

        reviewService.deleteReview(reviewNo);
    }
}
