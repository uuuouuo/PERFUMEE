<template>
  <div class="perfume">
    <h3>향수 상세보기</h3>
    <v-card :loading="loading" max-width="600">
      <template slot="progress">
        <v-progress-linear color="deep-purple" height="10" indeterminate>
        </v-progress-linear>
      </template>
      <v-img height="300" :src="perfume.image"> </v-img>
      <v-card-title>{{ perfume.name }}</v-card-title>
      <v-card-text>
        <v-row align="center" class="mx-0">
          <v-rating
            :value="perfume.rating"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          >
          </v-rating>
          <div class="grey--text ms-4">
            {{ perfume.rating }}
          </div>
        </v-row>
        <div class="my-4 text-subtitle-1">
          {{ perfume.brand }}
        </div>
        <div>
          <h4>Top</h4>
          {{ perfume.notes[0].name }} /
          {{ perfume.notes[1].name }}
          <h4>Middle</h4>
          {{ perfume.notes[2].name }} /
          {{ perfume.notes[3].name }}
          <h4>Bottom</h4>
          {{ perfume.notes[4].name }} /
          {{ perfume.notes[5].name }}
        </div>
      </v-card-text>
      <v-divider class="mx-4"></v-divider>
    </v-card>
    <perfume-review
      id="review"
      v-for="(review, index) in reviews"
      :key="index"
      v-bind="review"
    ></perfume-review>
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn dark v-bind="attrs" v-on="on" style="margin: 0px 10px 0px 0px">
          리뷰 작성
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="text-h5 grey lighten-2"> 리뷰 작성 </v-card-title>
        <v-card-text>
          <v-text-field label="별점(1~5)" v-model="rating"> </v-text-field>
          <v-text-field type="text" label="내용 입력" v-model="content">
          </v-text-field>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="createReview"> 작성 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
import PerfumeReview from "./PerfumeReview.vue";
import Swal from "sweetalert2";

const perfumeStore = "perfumeStore";
const userStore = "userStore";
export default {
  name: "PerfumeDetail",
  components: {
    PerfumeReview,
  },
  data() {
    return {
      dialog: "",
      rating: "",
      content: "",
      user: {
        id: "",
      },
      perfume: {},
      reviews: {},
    };
  },
  created() {
    axios
      .get(
        `http://j6c101.p.ssafy.io:8081/perfume/detail/${this.$route.params.no}`
      )
      .then(({ data }) => {
        console.log(data.notes);
        this.perfume = data;
      });
    axios
      .get(`http://j6c101.p.ssafy.io:8081/review/${this.$route.params.no}`)
      .then(({ data }) => {
        console.log(data);
        this.reviews = data;
      });
  },
  methods: {
    createReview() {
      axios
        .post(`http://localhost:8081/review`, {
          userId: this.userInfo.id,
          perfumeNo: this.$route.params.no,
          rating: this.rating,
          content: this.content,
        })
        .then(() => {
          let msg = "등록에 문제가 생겼습니다.";
          // 서버에서 정상적인 값이 넘어 왔을경우 실행.
          msg = "리뷰를 작성하였습니다!";
          this.$router.push("/detail");
          Swal.fire({
            icon: "success",
            text: msg,
          });
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            text: err.response.data,
          });
        });
    },
  },
  computed: {
    ...mapState(perfumeStore, ["perfumeDetail"]),
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>

<style></style>
