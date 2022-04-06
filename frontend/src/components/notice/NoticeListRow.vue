<template>
  <tr>
    <td>{{ noticeNo }}</td>
    <td>
      <router-link
        :to="{ name: 'NoticeView', params: { noticeNo: noticeNo } }"
        >{{ subject }}</router-link
      >
    </td>
    <td>관리자</td>
  </tr>
</template>

<script>
export default {
  name: "NoticeListRow",
  props: {
    noticeNo: Number,
    subject: String,
  },
};
</script>

<style></style>
