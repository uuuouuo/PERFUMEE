package com.ssafy.perfumee.model.dto.notice;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NoticeDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class NoticeReq {
        private String subject;
        private String content;

        public NoticeReq(String subject, String content){
            this.subject = subject;
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class NoticeRes {
        private String subject;
        private String content;

        public NoticeRes(String subject, String content){
            this.subject = subject;
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateReq {
        private String subject;
        private String content;

        public UpdateReq(String subject, String content){
            this.subject = subject;
            this.content = content;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class UpdateRes {
        private String subject;
        private String content;

        public UpdateRes(String subject, String content){
            this.subject = subject;
            this.content = content;
        }
    }
}
