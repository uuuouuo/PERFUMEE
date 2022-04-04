<template>
  <div>
    선호하는 향
    <v-select
      v-model="scent"
      :items="options"
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
      >회원가입</v-btn
    >
  </div>
</template>

<script>
export default {
  data() {
    return {
      options: [
        { value: "0", text: "알데하이드" },
        { value: "1", text: "용연향" },
        { value: "2", text: "사과" },
        { value: "3", text: "바질" },
        { value: "4", text: "벤조인" },
        { value: "5", text: "버가못" },
        { value: "6", text: "후추" },
        { value: "7", text: "블랙커런트" },
        { value: "8", text: "불가리안 로즈" },
        { value: "9", text: "카라멜" },
        { value: "10", text: "카다멈" },
        { value: "11", text: "카네이션" },
        { value: "12", text: "시더" },
        { value: "13", text: "시나몬" },
        { value: "14", text: "시트러스" },
        { value: "15", text: "시벳" },
        { value: "16", text: "클라리 세이지" },
        { value: "17", text: "클로브" },
        { value: "18", text: "코코넛" },
        { value: "19", text: "고수" },
        { value: "20", text: "시클라멘" },
        { value: "21", text: "솔잎" },
        { value: "22", text: "엘레미" },
        { value: "23", text: "플로럴" },
        { value: "24", text: "프랑킨센스" },
        { value: "25", text: "프리지아" },
        { value: "26", text: "제라늄" },
        { value: "27", text: "진저" },
        { value: "28", text: "자몽" },
        { value: "29", text: "바닐라 초콜릿" },
        { value: "30", text: "꿀" },
        { value: "31", text: "히야신스" },
        { value: "32", text: "아이리스" },
        { value: "33", text: "자스민" },
        { value: "34", text: "자스민 삼박" },
        { value: "35", text: "쥬니퍼베리" },
        { value: "36", text: "랍다넘" },
        { value: "37", text: "라벤더" },
        { value: "38", text: "가죽" },
        { value: "39", text: "레몬" },
        { value: "40", text: "백합" },
        { value: "41", text: "은방울꽃" },
        { value: "42", text: "라임" },
        { value: "43", text: "매그놀리아" },
        { value: "44", text: "만다린" },
        { value: "45", text: "미모사" },
        { value: "46", text: "민트" },
        { value: "47", text: "모스" },
        { value: "48", text: "머스크" },
        { value: "49", text: "몰약" },
        { value: "50", text: "네롤리" },
        { value: "51", text: "넛맥" },
        { value: "52", text: "오크모스" },
        { value: "53", text: "오렌지" },
        { value: "54", text: "난초" },
        { value: "55", text: "북꽃" },
        { value: "56", text: "오스만투스" },
        { value: "57", text: "침향" },
        { value: "58", text: "파출리" },
        { value: "59", text: "복숭아" },
        { value: "60", text: "배" },
        { value: "61", text: "모란" },
        { value: "62", text: "감귤" },
        { value: "63", text: "파인애플" },
        { value: "64", text: "핑크페퍼" },
        { value: "65", text: "자두" },
        { value: "66", text: "라즈베리" },
        { value: "67", text: "장미" },
        { value: "68", text: "로즈마리" },
        { value: "69", text: "샤프론" },
        { value: "70", text: "허브" },
        { value: "71", text: "샌달우드" },
        { value: "72", text: "매운향" },
        { value: "73", text: "귤" },
        { value: "74", text: "담배" },
        { value: "75", text: "통카" },
        { value: "76", text: "튜베로즈" },
        { value: "77", text: "바닐라" },
        { value: "78", text: "베티버" },
        { value: "79", text: "바이올렛" },
        { value: "80", text: "바이올렛 리프" },
        { value: "81", text: "화이트 머스크" },
        { value: "82", text: "우디" },
        { value: "83", text: "일랑" },
      ],
    };
  },
  methods: {
    checkValue() {},
  },
};
</script>

<style></style>
