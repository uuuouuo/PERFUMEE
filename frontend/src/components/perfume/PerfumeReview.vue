<template>
  <div class="container overflow-auto">
    <div
      class="row row-cols-1 row-cols-lg-2 row-cols-xl-3 g-4"
      v-if="conferenceItems"
    >
      <perfume-review-item
        id="review"
        v-for="item in reviewItems"
        :key="item.id"
        :item="item"
      >
      </perfume-review-item>
    </div>
    <div v-else class="text-center">
      <i
        class="far fa-calendar-times"
        style="font-size: 10rem; margin-top: 100px"
      ></i>
      <h3 class="my-5 text-danger">작성된 리뷰가 없습니다.</h3>
    </div>
    <v-btn>작성</v-btn>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import PerfumeReviewItem from "./PerfumeReviewItem.vue";

const reviewStore = "reviewStore";

export default {
  components: {
    PerfumeReviewItem,
  },
  created: function () {
    this.LoadReviewItems;
  },
  computed: {
    ...mapActions(reviewStore, ["LOAD_REVIEW_ITEMS"]),
    ...mapState(reviewStore, ["ReviewItems"]),
  },
};
</script>

<style></style>
