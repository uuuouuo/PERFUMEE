<template>
  <div id="app">
    <!-- <v-container>
      <v-btn color="pink" dark @click.stop="drawer = !drawer"> 옵션 </v-btn
      ><br />
      <v-navigation-drawer v-model="drawer" absolute temporary>
        <v-list-item>
          <v-list-item-content>옵션</v-list-item-content>
        </v-list-item>
        <v-divider></v-divider>
        <template v-for="(item, i) in items">
          <v-divider v-if="!item" :key="`divider-${i}`"></v-divider>

          <v-list-item
            v-else
            :key="`item-${i}`"
            :value="item"
            active-class="deep-purple--text text--accent-4"
          >
            <template v-slot:default="{ active }">
              <v-list-item-content>
                <v-list-item-title v-text="item"></v-list-item-title>
              </v-list-item-content>

              <v-list-item-action>
                <v-checkbox
                  :input-value="active"
                  color="deep-purple accent-4"
                ></v-checkbox>
              </v-list-item-action>
            </template>
          </v-list-item>
        </template>
        <v-btn color="red">적용</v-btn>
      </v-navigation-drawer>
      <v-text-field
        v-model="search"
        placeholder="향수 검색"
        outlined
      ></v-text-field>
    </v-container> -->
    <v-container class="grey lighten-5">
      <v-row no-gutters>
        <item-list
          v-for="(perfume, index) in calData"
          :key="index"
          v-bind="perfume"
        ></item-list>
      </v-row>
    </v-container>
    <div class="text-center">
      <v-pagination v-model="page" :length="numOfPages" circle></v-pagination>
    </div>
  </div>
</template>

<script>
import ItemList from "./ItemList.vue";
import { mapActions } from "vuex";
import axios from "axios";
import Swal from "sweetalert2";

const perfumeStore = "perfumeStore";

export default {
  name: "ItemPage",
  components: { ItemList },
  data() {
    return {
      drawer: null,
      items: ["남성", "여성", "공용"],
      perfumes: [],
      search: "",
      pageSize: 10,
      page: 1,
    };
  },
  created() {
    axios.get("http://j6c101.p.ssafy.io:8081/perfume").then(({ data }) => {
      this.perfumes = data.content;
    });
  },
  methods: {
    ...mapActions(perfumeStore, ["searchByTitle"]),
    perfumeSearch() {
      console.log(this.search);
      const value = this.search;
      if (
        value == "" ||
        value == null ||
        value == undefined ||
        (value != null &&
          typeof value == "object" &&
          !Object.keys(value).length)
      ) {
        var swalAlert = Swal.fire({
          icon: "error",
          text: "검색어를 입력해주세요.",
        });
        return swalAlert;
      } else {
        return this.searchByTitle(value);
      }
    },
  },
  computed: {
    startOffset() {
      return (this.page - 1) * this.pageSize;
    },
    endOffset() {
      return this.startOffset + this.pageSize;
    },
    numOfPages() {
      return Math.ceil(this.perfumes.length / this.pageSize);
    },
    calData() {
      return this.perfumes.slice(this.startOffset, this.endOffset);
    },
  },
};
</script>

<style></style>
