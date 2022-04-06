<template>
  <div>
    <v-carousel
      cycle
      height="600"
      hide-delimiter-background
      show-arrows-on-hover
    >
      <v-carousel-item v-for="(item, i) in items" :src="item.src" :key="i">
        <v-row class="fill-height" align="center" justify="center">
          <div class="text-h3 yellow--text">{{ item.text }}</div>
        </v-row>
      </v-carousel-item>
    </v-carousel>
    <div v-if="isLogin">
      <h2>맞춤 향수 추천</h2>
      <v-container class="grey lighten-5">
        <v-row no-gutters>
          <male-perfume
            style="margin: 0px 60px 30px 0px"
            v-for="(recommPerfume, index) in recommcalData"
            :key="index"
            v-bind="recommPerfume"
          ></male-perfume>
        </v-row>
      </v-container>
    </div>
    <h2>남자 향수 추천</h2>
    <v-container class="grey lighten-5">
      <v-row no-gutters>
        <male-perfume
          style="margin: 0px 60px 30px 0px"
          v-for="(malePerfume, index) in malecalData"
          :key="index"
          v-bind="malePerfume"
        ></male-perfume>
      </v-row>
    </v-container>
    <h2>여자 향수 추천</h2>
    <v-container class="grey lighten-5">
      <v-row no-gutters>
        <female-perfume
          style="margin: 0px 60px 30px 0px"
          v-for="(femalePerfume, index) in femalecalData"
          :key="index"
          v-bind="femalePerfume"
        ></female-perfume>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import http from "@/config/http-common.js";
import MalePerfume from "../components/perfume/MalePerfume.vue";
import FemalePerfume from "../components/perfume/FemalePerfume.vue";
import { mapState } from "vuex";

const userStore = "userStore";
export default {
  components: {
    MalePerfume,
    FemalePerfume,
  },
  data() {
    return {
      recommPerfumes: [],
      malePerfumes: [],
      femalePerfumes: [],
      page: 1,
      pageSize: 10,
      model: 0,
      items: [
        {
          src: "https://cdn.pixabay.com/photo/2019/04/06/19/22/glass-4108085_960_720.jpg",
          text: "혹시 냄새가 날까 걱정하고 계신가요?",
        },
        {
          src: "https://cdn.pixabay.com/photo/2016/06/15/22/22/still-life-1460067_960_720.jpg",
          text: "누군가를 만나서 잘 보여야 하는 상황인가요?",
        },
        {
          src: "https://cdn.pixabay.com/photo/2018/01/30/14/22/lavender-3118882_960_720.jpg",
          text: "그럴땐 향수 추천사이트 Perfumee",
        },
        {
          src: "https://cdn.pixabay.com/photo/2018/08/20/19/38/fragrance-3619762_960_720.jpg",
          text: "선호하는 향에 따라 향수를 추천해주는 그런 사이트!",
        },
        {
          src: "https://cdn.pixabay.com/photo/2019/11/29/10/03/perfume-4660970_960_720.jpg",
          text: "지금 당장 Perfumee를 이용해보세요!",
        },
      ],
    };
  },
  created() {
    http
      .post(`/user/recommper`, {
        userId: sessionStorage.getItem("id"),
      })
      .then(({ data }) => {
        this.recommPerfumes = data;
        console.log(this.recommPerfumes);
      });

    http.get(`/perfume/male`).then(({ data }) => {
      this.malePerfumes = data.content;
      console.log(this.malePerfumes);
    });
    http.get(`/perfume/female`).then(({ data }) => {
      this.femalePerfumes = data.content;
      console.log(this.femalePerfumes);
    });
  },
  computed: {
    ...mapState(userStore, ["isLogin"]),
    startOffset() {
      return (this.page - 1) * this.pageSize;
    },
    endOffset() {
      return this.startOffset + this.pageSize;
    },
    recommcalData() {
      return this.recommPerfumes.slice(this.startOffset, this.endOffset);
    },
    malecalData() {
      return this.malePerfumes.slice(this.startOffset, this.endOffset);
    },
    femalecalData() {
      return this.femalePerfumes.slice(this.startOffset, this.endOffset);
    },
  },
};
</script>
