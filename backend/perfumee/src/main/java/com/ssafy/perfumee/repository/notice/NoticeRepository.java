package com.ssafy.perfumee.repository.notice;

import com.ssafy.perfumee.model.entity.notice.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    Notice findByNo(Integer noticeNo);
}
