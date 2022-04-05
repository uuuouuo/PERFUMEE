package com.ssafy.perfumee.repository.notice;

import com.ssafy.perfumee.model.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Optional<Notice> findByNo(Integer noticeNo);
}
