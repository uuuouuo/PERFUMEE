package com.ssafy.perfumee.controller;

import com.ssafy.perfumee.model.dto.notice.NoticeDto.UpdateReq;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.UpdateRes;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.NoticeReq;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.NoticeRes;
import com.ssafy.perfumee.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notice")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
public class NoticeController {

    private final NoticeService noticeService;

    @PostMapping("")
    public ResponseEntity<NoticeRes> createNotice(
            @RequestBody NoticeReq request) {

        NoticeRes response = noticeService.writeNotice(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<Page<NoticeRes>> getNoticeList(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<NoticeRes> response = noticeService.getList();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{noticeNo}")
    public ResponseEntity<NoticeRes> getNoticeDetail(@PathVariable("noticeNo") Integer noticeNo) {
        NoticeRes response = noticeService.getDetail(noticeNo);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{noticeNo}")
    public ResponseEntity<UpdateRes> updateNotice(
            @PathVariable("noticeNo") Integer noticeNo,
            @RequestBody UpdateReq request){

        UpdateRes response = noticeService.updateNotice(noticeNo, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<String> deleteNotice(
            @PathVariable("noticeNo") Integer noticeNo) {

        noticeService.deleteNotice(noticeNo);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }


}
