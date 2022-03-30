package com.ssafy.perfumee.service.notice;

import com.ssafy.perfumee.model.dto.notice.NoticeDto.UpdateReq;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.UpdateRes;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.NoticeReq;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.NoticeRes;
import com.ssafy.perfumee.model.entity.notice.Notice;
import com.ssafy.perfumee.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeRes writeNotice(NoticeReq request){
        Notice notice = new Notice(request.getSubject(), request.getSubject());
        noticeRepository.save(notice);
        NoticeRes response = new NoticeRes(notice.getSubject(), notice.getContent());
        return response;
    }

    public UpdateRes updateNotice(Integer noticeNO, UpdateReq request){
        Notice prevNotice = noticeRepository.findByNo(noticeNO);
        prevNotice.updateNotice(request.getSubject(), request.getSubject());
        noticeRepository.save(prevNotice);
        UpdateRes response = new UpdateRes(prevNotice.getSubject(), prevNotice.getContent());
        return response;
    }

    public void deleteNotice(Integer noticeNO){
        Notice prevNotice = noticeRepository.findByNo(noticeNO);
        noticeRepository.delete(prevNotice);
    }
}
