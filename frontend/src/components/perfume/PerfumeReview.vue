<template>
  <div class="container overflow-auto">
    <v-card :loading="loading" max-width="600">
      <template slot="progress">
        <v-progress-linear color="deep-purple" height="10" indeterminate>
        </v-progress-linear>
      </template>
      <v-card-title>{{ no }}</v-card-title>
      <v-card-text>
        <v-row align="center" class="mx-0">
          <v-rating
            :value="rating"
            color="amber"
            dense
            half-increments
            readonly
            size="14"
          >
          </v-rating>
        </v-row>
        <div class="my-4 text-subtitle-1">
          {{ content }}
        </div>
        <div class="my-4 text-subtitle-1">
          {{ user.id }}
        </div>
      </v-card-text>
      <v-divider class="mx-4"></v-divider>
    </v-card>
    <v-dialog v-model="dialog2" width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          v-if="userInfo.id == user.id"
          dark
          v-bind="attrs"
          v-on="on"
          style="margin: 0px 10px 0px 0px"
        >
          리뷰 수정
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="text-h5 grey lighten-2"> 리뷰 작성 </v-card-title>
        <v-card-text>
          <v-text-field label="별점(1~5)" v-model="changeRating">
          </v-text-field>
          <v-text-field type="text" label="내용 입력" v-model="changeContent">
          </v-text-field>
        </v-card-text>
        <v-divider></v-divider>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="reviewUpdate"> 리뷰 수정 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-btn v-if="userInfo.id == user.id" @click="deleteConfirm"
      >리뷰 삭제</v-btn
    >
  </div>
</template>

<script>
import http from "@/config/http-common.js";
import { mapState } from "vuex";
import Swal from "sweetalert2";

const userStore = "userStore";
export default {
  data() {
    return {
      id: "",
      dialog2: "",
      changeRating: "",
      changeContent: "",
    };
  },
  props: {
    content: String,
    rating: Number,
    no: Number,
    user: Object,
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    deleteConfirm() {
      Swal.fire({
        icon: "question",
        text: "리뷰를 삭제하시겠습니까?",
        showCancelButton: true,
        confirmButtonText: "네",
      }).then((result) => {
        if (result.isConfirmed) {
          this.deleteReview();
        } else {
          Swal.fire({
            icon: "info",
            text: "리뷰 삭제가 취소되었습니다.",
          });
        }
      });
    },
    deleteReview() {
      http({
        method: "delete",
        url: `/review/${this.no}`,
      })
        .then(() => {
          this.$router.push("/detail");
          Swal.fire({
            icon: "info",
            text: "리뷰 삭제가 완료되었습니다.",
          });
        })
        .catch((err) => {
          console.dir(err);
        });
    },
    reviewUpdate() {
      http
        .put(`/review/${this.no}`, {
          rating: this.changeRating,
          content: this.changeContent,
        })
        .then(() => {
          this.$router.push("/detail");
          Swal.fire({
            icon: "success",
            text: "리뷰 수정이 완료되었습니다.",
          });
          this.modalCheck = false;
        })
        .catch((err) => {
          console.dir(err);
        });
    },
  },
};
</script>

<style></style>
