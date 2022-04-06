<template>
  <div>
    선호하는 향
    <v-select
      v-model="scent"
      v-bind:items="options"
      item-value="note_id"
      item-text="name_kor"
      multiple
      label="선호하는 향"
      dense
      outlined
    >
    </v-select>
    <v-btn
      class="mt-3"
      color=""
      outlined
      style="margin: 0px 10px 0px 0px"
      @click="checkValue"
      >선호하는 향 설정</v-btn
    >
  </div>
</template>

<script>
import http from "@/config/http-common.js";
import Swal from "sweetalert2";

export default {
  data() {
    return {
      scent: "",
      userId: sessionStorage.getItem("id"),
      options: [
        { note_id: "1", name_kor: "알데하이드" },
        { note_id: "2", name_kor: "용연향" },
        { note_id: "3", name_kor: "사과" },
        { note_id: "4", name_kor: "바질" },
        { note_id: "5", name_kor: "벤조인" },
        { note_id: "6", name_kor: "버가못" },
        { note_id: "7", name_kor: "후추" },
        { note_id: "8", name_kor: "블랙커런트" },
        { note_id: "9", name_kor: "불가리안 로즈" },
        { note_id: "10", name_kor: "카라멜" },
        { note_id: "11", name_kor: "카다멈" },
        { note_id: "12", name_kor: "카네이션" },
        { note_id: "13", name_kor: "시더" },
        { note_id: "14", name_kor: "시나몬" },
        { note_id: "15", name_kor: "시트러스" },
        { note_id: "16", name_kor: "시벳" },
        { note_id: "17", name_kor: "클라리 세이지" },
        { note_id: "18", name_kor: "클로브" },
        { note_id: "19", name_kor: "코코넛" },
        { note_id: "20", name_kor: "고수" },
        { note_id: "21", name_kor: "시클라멘" },
        { note_id: "22", name_kor: "솔잎" },
        { note_id: "23", name_kor: "엘레미" },
        { note_id: "24", name_kor: "플로럴" },
        { note_id: "25", name_kor: "프랑킨센스" },
        { note_id: "26", name_kor: "프리지아" },
        { note_id: "27", name_kor: "제라늄" },
        { note_id: "28", name_kor: "진저" },
        { note_id: "29", name_kor: "자몽" },
        { note_id: "30", name_kor: "바닐라 초콜릿" },
        { note_id: "31", name_kor: "꿀" },
        { note_id: "32", name_kor: "히야신스" },
        { note_id: "33", name_kor: "아이리스" },
        { note_id: "34", name_kor: "자스민" },
        { note_id: "35", name_kor: "자스민 삼박" },
        { note_id: "36", name_kor: "쥬니퍼베리" },
        { note_id: "37", name_kor: "랍다넘" },
        { note_id: "38", name_kor: "라벤더" },
        { note_id: "39", name_kor: "가죽" },
        { note_id: "40", name_kor: "레몬" },
        { note_id: "41", name_kor: "백합" },
        { note_id: "42", name_kor: "은방울꽃" },
        { note_id: "43", name_kor: "라임" },
        { note_id: "44", name_kor: "매그놀리아" },
        { note_id: "45", name_kor: "만다린" },
        { note_id: "46", name_kor: "미모사" },
        { note_id: "47", name_kor: "민트" },
        { note_id: "48", name_kor: "모스" },
        { note_id: "49", name_kor: "머스크" },
        { note_id: "50", name_kor: "몰약" },
        { note_id: "51", name_kor: "네롤리" },
        { note_id: "52", name_kor: "넛맥" },
        { note_id: "53", name_kor: "오크모스" },
        { note_id: "54", name_kor: "오렌지" },
        { note_id: "55", name_kor: "난초" },
        { note_id: "56", name_kor: "북꽃" },
        { note_id: "57", name_kor: "오스만투스" },
        { note_id: "58", name_kor: "침향" },
        { note_id: "59", name_kor: "파출리" },
        { note_id: "60", name_kor: "복숭아" },
        { note_id: "61", name_kor: "배" },
        { note_id: "62", name_kor: "모란" },
        { note_id: "63", name_kor: "감귤" },
        { note_id: "64", name_kor: "파인애플" },
        { note_id: "65", name_kor: "핑크페퍼" },
        { note_id: "66", name_kor: "자두" },
        { note_id: "67", name_kor: "라즈베리" },
        { note_id: "68", name_kor: "장미" },
        { note_id: "69", name_kor: "로즈마리" },
        { note_id: "70", name_kor: "샤프론" },
        { note_id: "71", name_kor: "허브" },
        { note_id: "72", name_kor: "샌달우드" },
        { note_id: "73", name_kor: "매운향" },
        { note_id: "74", name_kor: "귤" },
        { note_id: "75", name_kor: "담배" },
        { note_id: "76", name_kor: "통카" },
        { note_id: "77", name_kor: "튜베로즈" },
        { note_id: "78", name_kor: "바닐라" },
        { note_id: "79", name_kor: "베티버" },
        { note_id: "80", name_kor: "바이올렛" },
        { note_id: "81", name_kor: "바이올렛 리프" },
        { note_id: "82", name_kor: "화이트 머스크" },
        { note_id: "83", name_kor: "우디" },
        { note_id: "84", name_kor: "일랑" },
      ],
    };
  },
  methods: {
    checkValue() {
      http
        .post(`/user/recomm`, {
          userId: this.userId,
          noteIds: this.scent,
        })
        .then(() => {
          let msg = "등록에 문제가 생겼습니다.";
          // 서버에서 정상적인 값이 넘어 왔을경우 실행.
          msg = "선호하는 향 설정을 완료하였습니다. 로그인해주세요!";
          Swal.fire({
            icon: "success",
            text: msg,
          });
          sessionStorage.removeItem("id");
          this.$router.push("/");
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            text: err.response.data,
          });
        });
    },
  },
};
</script>

<style></style>
