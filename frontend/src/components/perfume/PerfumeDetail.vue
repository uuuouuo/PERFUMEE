<template>
  <div class="perfume" v-if="perfume">
    <h3>향수 상세보기</h3>
    <v-card :loading="loading" width="600">
      <template slot="progress">
        <v-progress-linear color="deep-purple" height="10" indeterminate>
        </v-progress-linear>
      </template>
      <v-img height="300" :src="perfume.image"> </v-img>
      <br />
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
          {{ perfume.brand }} / {{ perfume.gender }}
        </div>
        <br />
        <h3>📝 Notes</h3>
        <hr />
        <br />
        <div v-if="perfume">
          <h4>Top</h4>
          <li v-if="perfume.notes[0]">{{ perfume.notes[0].name }}</li>
          <li v-else>None</li>
          <li v-if="perfume.notes[1]">
            {{ perfume.notes[1].name }}
          </li>
          <li v-else>None</li>
          <h4>Middle</h4>
          <li v-if="perfume.notes[2]">{{ perfume.notes[2].name }}</li>
          <li v-else>None</li>
          <li v-if="perfume.notes[3]">
            {{ perfume.notes[3].name }}
          </li>
          <li v-else>None</li>
          <h4>Bottom</h4>
          <li v-if="perfume.notes[4]">{{ perfume.notes[4].name }}</li>
          <li v-else>None</li>
          <li v-if="perfume.notes[5]">
            {{ perfume.notes[5].name }}
          </li>
          <li v-else>None</li>
        </div>
      </v-card-text>
      <v-divider class="mx-4"></v-divider>
    </v-card>
    <perfume-review
      class="review"
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
import PerfumeReview from "./PerfumeReview.vue";

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
        this.perfume = data;
      });
    axios
      .get(`http://j6c101.p.ssafy.io:8081/review/${this.$route.params.no}`)
      .then(({ data }) => {
        this.reviews = data;
      });
  },
};
</script>

<style></style>
