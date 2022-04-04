import http from "@/config/http-common.js";

const reviewStore = {
  namespaced: true,
  state: {
    reviewItems: [],
  },
  mutations: {
    LOAD_REVIEW_ITEMS: function (state, results) {
      if (results.length == 0) {
        state.reviewItems = false;
      } else {
        state.reviewItems = results.sort((a, b) => {
          return b.id - a.id;
        });
      }
    },
    actions: {
      LoadReviewItems: function ({ commit }, perfumeNo) {
        http({
          method: "get",
          url: `/review/${perfumeNo}`,
        })
          .then((response) => {
            commit("LOAD_REVIEW_ITEMS", response.data);
          })
          .catch((err) => {
            console.dir(err);
          });
      },
    },
  },
};

export default reviewStore;
