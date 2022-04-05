package com.ssafy.perfumee.service.notice;

import com.ssafy.perfumee.model.dto.notice.NoticeDto.UpdateReq;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.UpdateRes;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.NoticeReq;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.NoticeRes;
import com.ssafy.perfumee.model.entity.notice.Notice;
import com.ssafy.perfumee.repository.notice.NoticeRepository;
import com.ssafy.perfumee.service.validation.ValidateExist;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = false)
public class NoticeService {
    private final NoticeRepository noticeRepository;
    private ValidateExist validateExist = new ValidateExist();

    public NoticeRes getDetail(Integer noticeNo){
        Optional<Notice> noticeOptional = noticeRepository.findByNo(noticeNo);
        Notice notice = validateExist.findNotice(noticeOptional);
        NoticeRes response = new NoticeRes(notice.getNo(), notice.getSubject(), notice.getContent());
        return response;
    }

    public Page<NoticeRes> getList() {
        List<Notice> notices = noticeRepository.findAll();
        Page<Notice> noticePage = new PageImpl<>(notices);
        Page<NoticeRes> noticeResPage = noticePage.map(
                notice -> new NoticeRes(
                        notice.getNo(), notice.getSubject(), notice.getContent()
                )
        );
        return noticeResPage;
    }

    public NoticeRes writeNotice(NoticeReq request){
        Notice notice = new Notice(request.getSubject(), request.getContent());
        noticeRepository.save(notice);
        NoticeRes response = new NoticeRes(notice.getNo(), notice.getSubject(), notice.getContent());
        return response;
    }

    public UpdateRes updateNotice(Integer noticeNo, UpdateReq request){
        Optional<Notice> noticeOptional = noticeRepository.findByNo(noticeNo);
        Notice prevNotice = validateExist.findNotice(noticeOptional);
        prevNotice.updateNotice(request.getSubject(), request.getContent());
        noticeRepository.save(prevNotice);
        UpdateRes response = new UpdateRes(prevNotice.getSubject(), prevNotice.getContent());
        return response;
    }

    public void deleteNotice(Integer noticeNo){
        Optional<Notice> noticeOptional = noticeRepository.findByNo(noticeNo);
        Notice prevNotice = validateExist.findNotice(noticeOptional);
        noticeRepository.delete(prevNotice);
    }

}
