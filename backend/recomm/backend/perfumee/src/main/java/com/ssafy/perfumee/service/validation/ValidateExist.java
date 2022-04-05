package com.ssafy.perfumee.service.validation;

import com.ssafy.perfumee.model.entity.notice.Notice;
import com.ssafy.perfumee.model.entity.perfume.Contain;
import com.ssafy.perfumee.model.entity.perfume.Note;
import com.ssafy.perfumee.model.entity.perfume.Perfume;
import com.ssafy.perfumee.model.entity.review.Review;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public class ValidateExist {

    public Perfume findPerfume(Optional<Perfume> optional){
        Perfume perfume;
        if(optional.isPresent()){
            perfume = optional.get();
        } else {
            throw new IllegalStateException("존재하지 않는 향수입니다.");
        }
        return perfume;
    }

    public Page<Perfume> findPagePerfume(Optional<Page<Perfume>> optional) {
        Page<Perfume> perfumes = null;
        if(optional.isPresent()) {
            perfumes = optional.get();
        }
        return perfumes;
    }

    public List<Contain> findContainList(Optional<List<Contain>> optional) {
        List<Contain> containList = null;
        if(optional.isPresent()) {
            containList = optional.get();
        }
        return containList;
    }

    public List<Note> findNoteList(Optional<List<Note>> optional) {
        List<Note> noteList = null;
        if(optional.isPresent()) {
            noteList = optional.get();
        }
        return noteList;
    }

    public Review findReview(Optional<Review> optional) {
        Review review;
        if(optional.isPresent()) {
            review = optional.get();
        } else {
            throw new IllegalStateException("존재하지 않는 리뷰입니다.");
        }
        return review;
    }

    public List<Review> findReviewList(Optional<List<Review>> optional) {
        List<Review> reviewList = null;
        if(optional.isPresent()) {
            reviewList = optional.get();
        }
        return reviewList;
    }

    public Notice findNotice(Optional<Notice> optional) {
        Notice notice;
        if(optional.isPresent()) {
            notice = optional.get();
        } else {
            throw new IllegalStateException("존재하지 않는 공지사항입니다.");
        }
        return notice;
    }
}
