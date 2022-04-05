package com.ssafy.perfumee.model.dto.review;

import com.ssafy.perfumee.model.entity.perfume.Perfume;
import com.ssafy.perfumee.model.entity.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ReviewDto {
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReviewReq {
        private String userId;
        private Integer perfumeNo;
        private String content;
        private BigDecimal rating;

        public ReviewReq(String userId, Integer perfumeNo, String content, BigDecimal rating) {
            this.userId = userId;
            this.perfumeNo = perfumeNo;
            this.content = content;
            this.rating = rating;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class ReviewRes {
        private Integer no;
        private User user;
        private Perfume perfume;
        private String content;
        private BigDecimal rating;
        private Timestamp regDate;
        private Timestamp updateDate;

        public ReviewRes(Integer no, User user, Perfume perfume, String content, BigDecimal rating, Timestamp regDate, Timestamp updateDate) {
            this.no = no;
            this.user = user;
            this.perfume = perfume;
            this.content = content;
            this.rating = rating;
            this.regDate = regDate;
            this.updateDate = updateDate;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateReviewReq {
        private String content;
        private BigDecimal rating;


        public UpdateReviewReq(String content, BigDecimal rating) {
            this.content = content;
            this.rating = rating;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateReviewRes {
        private Integer no;
        private User user;
        private Perfume perfume;
        private String content;
        private BigDecimal rating;
        private Timestamp regDate;
        private Timestamp updateDate;

        public UpdateReviewRes(Integer no, User user, Perfume perfume, String content, BigDecimal rating, Timestamp regDate, Timestamp updateDate) {
            this.no = no;
            this.user = user;
            this.perfume = perfume;
            this.content = content;
            this.rating = rating;
            this.regDate = regDate;
            this.updateDate = updateDate;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class checkReviewReq {
        private String userId;

        public checkReviewReq(String userId) {
            this.userId = userId;
        }
    }
}
