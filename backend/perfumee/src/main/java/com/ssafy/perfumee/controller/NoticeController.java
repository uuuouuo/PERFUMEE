package com.ssafy.perfumee.controller;

import com.ssafy.perfumee.model.dto.notice.NoticeDto.UpdateReq;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.UpdateRes;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.NoticeReq;
import com.ssafy.perfumee.model.dto.notice.NoticeDto.NoticeRes;
import com.ssafy.perfumee.service.notice.NoticeService;
import lombok.RequiredArgsConstructor;
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
            @RequestPart(value="request") NoticeReq request) {

        NoticeRes response = noticeService.writeNotice(request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{noticeNo}")
    public ResponseEntity<UpdateRes> updateNotice(
            @PathVariable Integer noticeNo,
            @RequestPart(value="request") UpdateReq request){

        UpdateRes response = noticeService.updateNotice(noticeNo, request);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{noticeNo}")
    public ResponseEntity<String> deleteNotice(
            @PathVariable Integer noticeNo) {

        noticeService.deleteNotice(noticeNo);
        return new ResponseEntity<>("Delete Success", HttpStatus.OK);
    }


}
