package com.ssafy.perfumee.model.dto.perfume;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class NoteDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class NoteRes {
        private String type;
        private String name;

        public NoteRes(String type, String name){
            this.type = type;
            this.name = name;
        }
    }
}
