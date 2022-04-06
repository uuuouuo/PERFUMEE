<template>
  <div>
    <h1 class="underline">글 목록</h1>
    <div style="text-align: right">
      <button @click="movePage">글 등록</button>
    </div>
    <div v-if="notices.length">
      <table id="notice-list">
        <colgroup>
          <col style="width: 15%" />
          <col style="width: 45%" />
          <col style="width: 25%" />
          <col style="width: 25%" />
        </colgroup>
        <thead>
          <tr>
            <th>글번호</th>
            <th>제목</th>
            <th>글 작성자</th>
          </tr>
        </thead>
        <tbody>
          <notice-list-row
            v-for="(notice, index) in notices.reverse()"
            :key="index"
            v-bind="notice"
          ></notice-list-row>
        </tbody>
      </table>
    </div>
    <div v-else class="text-center">게시글이 없습니다.</div>
  </div>
</template>

<script>
import axios from "axios";
import NoticeListRow from "@/components/notice/NoticeListRow.vue";

export default {
  name: "NoticeList",
  components: {
    NoticeListRow,
  },
  data() {
    return {
      notices: [],
    };
  },
  created() {
    axios.get("http://j6c101.p.ssafy.io:8081/notice").then(({ data }) => {
      this.notices = data.content;
    });
  },
  methods: {
    movePage() {
      this.$router.push({ name: "NoticeWrite" });
    },
  },
};
</script>

<style scoped>
button,
.btn {
  width: 6%;
  background-color: #329232;
  color: rgb(255, 255, 255);
  padding: 7px 10px 7px;
  margin: 8px 0;
  border: 1px solid #ffffff;
  border-radius: 4px;
  font-size: large;
  cursor: pointer;
}
.underline {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgb(0, 255, 34) 30%
  );
}
#notice-list {
  border-collapse: collapse;
  width: 100%;
}

#notice-list thead {
  background-color: #ccc;
  font-weight: bold;
}

#notice-list td,
#notice-list th {
  text-align: center;
  border-bottom: 1px solid #ddd;
  height: 50px;
}

tr:nth-child(even) {
  background-color: #59af60;
}
</style>
