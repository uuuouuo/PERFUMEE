<template>
  <div class="regist">
    <h1 class="underline">공지사항 게시판</h1>
    <div class="regist_form">
      <label for="subject">제목</label>
      <input
        type="subject"
        id="subject"
        name="subject"
        v-model="subject"
        ref="subject"
      /><br />
      <label for="content">내용</label><br />
      <textarea
        id="content"
        name="content"
        v-model="content"
        ref="content"
        cols="35"
        rows="5"
      ></textarea
      ><br />
      <button @click="checkValue">등록</button>
      <button @click="moveList">뒤로 가기</button>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      subject: "",
      content: "",
    };
  },
  methods: {
    // 입력값 체크하기 - 체크가 성공하면 NoticeWrite 호출
    checkValue() {
      // 사용자 입력값 체크하기
      // writer,title, content 설명이 없을 경우 각 항목에 맞는 메세지를 출력
      let err = true;
      let msg = "";
      err &&
        !this.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      // 만약, 내용이 다 입력되어 있다면 NoticeWrite 호출
      else this.writeNotice();
    },

    writeNotice() {
      axios
        .post("http://j6c101.p.ssafy.io:8081/notice", {
          subject: this.subject,
          content: this.content,
        })
        .then(({ data }) => {
          // 서버에서 정상적인 값이 넘어 왔을경우 실행.
          let msg = "등록이 완료되었습니다.";
          console.log(data);
          alert(msg);
          this.moveList();
        });
    },
    moveList() {
      this.$router.push({ name: "NoticeList" });
    },
  },
};
</script>

<style scoped>
.underline {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgb(243, 203, 94) 30%
  );
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
button,
.btn {
  width: 8%;
  background-color: #41c241;
  color: rgb(255, 255, 255);
  padding: 14px 20px;
  margin: 50px;
  border: 1px solid #36ca63;
  border-radius: 4px;
  font-size: large;
  cursor: pointer;
}
</style>
