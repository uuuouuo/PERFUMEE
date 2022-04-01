package com.ssafy.perfumee.model.dto.perfume;

import com.ssafy.perfumee.model.entity.perfume.Perfume;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

public class PerfumeDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PerfumeRes {
        private Integer no;
        private String name;
        private String brand;
        private String gender;
        private String image;
        private BigDecimal rating;

        public PerfumeRes(Integer no, String name, String brand, String gender, String image, BigDecimal rating){
            this.no = no;
            this.name = name;
            this.brand = brand;
            this.gender = gender;
            this.image = image;
            this.rating = rating;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class PerfumePagingRes {
        private Integer no;
        private String name;
        private String brand;
        private String gender;
        private String image;
        private BigDecimal rating;

        public PerfumePagingRes(Integer no, String name, String brand, String gender, String image, BigDecimal rating){
            this.no = no;
            this.name = name;
            this.brand = brand;
            this.gender = gender;
            this.image = image;
            this.rating = rating;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SearchDto {
        private List<String> brand;
        private List<String> note;
        private List<String> gender;

        public void create(List<String> brand, List<String> note, List<String> gender) {
            this.brand = brand;
            this.note = note;
            this.gender = gender;
        }
    }
}
