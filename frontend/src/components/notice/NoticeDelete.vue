<template>
  <div>
    <h1>공지사항 글 삭세</h1>
    <div>삭제중...</div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  created() {
    axios
      .delete(
        `http://j6c101.p.ssafy.io:8081/notice/${this.$route.params.noticeNo}`
      )
      .then(({ data }) => {
        let msg = "삭제가 완료되었습니다.";
        alert(msg);
        console.log(data);
        // 현재 route를 list로 변경.
        this.$router.push({ name: "NoticeList" });
      });
  },
};
</script>

<style></style>
