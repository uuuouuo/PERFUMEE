<template>
  <tr>
    <td>{{ no }}</td>
    <td>
      <router-link :to="{ name: 'NoticeView', params: { no: no } }">{{
        title
      }}</router-link>
    </td>
    <td>{{ writer }}</td>
    <!-- <td>{{ regtime }}</td> -->
    <td>{{ changeDataFormat }}</td>
  </tr>
</template>

<script>
import moment from "moment"; // npm install moment 설치

export default {
  name: "NoticeListRow",
  props: {
    no: Number,
    title: String,
    writer: String,
    regtime: String,
  },
  computed: {
    changeDataFormat() {
      console.log("test");
      return moment(new Date(this.regtime)).format("YYYY.MM.DD hh:mm:ss");
    },
  },
};
</script>

<style></style>
