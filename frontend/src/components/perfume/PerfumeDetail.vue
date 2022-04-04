<template>
  <div class="perfume">
    <v-card :loading="loading" max-width="600">
      <template slot="progress">
        <v-progress-linear color="deep-purple" height="10" indeterminate>
        </v-progress-linear>
      </template>
      <v-img height="300" src="perfume.img"> </v-img>
      <v-card-title>{{ perfume.name }}</v-card-title>
      <v-card-text>
        <v-row align="center" class="mx-0">
          <v-rating
            :value="perfume.rating"
            color="amber"
            dense
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
      </v-card-text>
      <v-divider class="mx-4"></v-divider>
    </v-card>
    <perfume-review></perfume-review>
  </div>
</template>

<script>
import { mapState } from "vuex";
import PerfumeReview from "./PerfumeReview.vue";

const perfumeStore = "perfumeStore";
export default {
  name: "PerfumeDetail",
  components: {
    PerfumeReview,
  },
  data() {
    return {
      perfume: {},
    };
  },
  computed: {
    ...mapState(perfumeStore, ["perfumeDetail"]),
  },
};
</script>

<style></style>
