<template>
  <div class="regist">
    <h1 class="underline">Notice 보기</h1>
    <div class="regist_form">
      <label for="isbn">글번호</label>
      <div class="view">{{ notice.no }}</div>
      <label for="title">제목</label>
      <div class="view">{{ notice.title }}</div>
      <label for="author">작성자</label>
      <div class="view">{{ notice.writer }}</div>
      <label for="price">작성일</label>
      <div class="view">{{ notice.regtime }}</div>
      <label for="content">내용</label>
      <div class="view" v-html="notice.content"></div>
      <div style="padding-top: 15px">
        <router-link :to="{ name: 'NoticeModify' }" class="btn"
          >수정</router-link
        >
        <router-link :to="{ name: 'NoticeDelete' }" class="btn"
          >삭제</router-link
        >
        <router-link to="/list" class="btn">목록</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data: function () {
    return {
      notice: {},
    };
  },
  created() {
    axios
      .get(`http://localhost:9999/vue/api/board/${this.$route.params.no}`)
      .then(({ data }) => {
        this.notice = data;
      });
  },
  methods: {},
};
</script>

<style scoped>
.underline {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgb(223, 45, 178) 30%
  );
}
.regist {
  padding: 10px;
}
.regist_form {
  border-radius: 5px;
  background-color: #f2f2f2;
  padding: 20px;
}
input,
textarea,
.writer {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
  color: #787878;
  font-size: medium;
}
label {
  display: inline-block;
  width: 80px;
}
button,
.btn {
  width: 6%;
  background-color: #58b958;
  color: rgb(255, 255, 255);
  padding: 7px 10px 7px;
  margin: 8px 8px;
  border: 1px solid #ffffff;
  border-radius: 4px;
  font-size: large;
  cursor: pointer;
}
</style>
