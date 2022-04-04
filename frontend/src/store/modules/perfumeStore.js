import http from "@/config/http-common.js";

const perfumeStore = {
  namespaced: true,
  state: {
    perfumeItems: [],
    perfumeDetail: [],
  },
  mutations: {
    LOAD_PERFUME_ITEMS: function (state, results) {
      if (results.length == 0) {
        state.perfumeItems = false;
      } else {
        state.perfumeItems = results.sort((a, b) => {
          return b.id - a.id;
        });
      }
    },
    LOAD_PERFUME_DETAIL: function (state, results) {
      state.perfumeDetail = results;
    },
  },
  actions: {
    LoadPerfumeItems: function ({ commit }) {
      http({
        method: "get",
        url: `/perfume`,
      })
        .then((response) => {
          commit("LOAD_PERFUME_ITEMS", response.data);
        })
        .catch((err) => {
          console.dir(err);
        });
    },
    searchByTitle: function ({ commit }, search_value) {
      http({
        method: "get",
        url: `/perfume/searchByTitle?word=${search_value}`,
      })
        .then((response) => {
          // console.log(response);
          commit("LOAD_CONFERENCE_ITEMS", response.data);
        })
        .catch((err) => {
          console.dir(err);
        });
    },
  },
};

export default perfumeStore;
