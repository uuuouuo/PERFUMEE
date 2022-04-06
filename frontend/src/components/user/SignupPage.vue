<template>
  <v-container class="con">
    <div class="pl-3 pr-3">
      <h2>회원가입</h2>
    </div>
    <v-text-field
      class="pl-3 pr-3"
      label="아이디"
      type="text"
      v-model="id"
      prepend-icon="mdi-account"
    >
    </v-text-field>
    <v-text-field
      class="pl-3 pr-3"
      label="비밀번호"
      type="password"
      v-model="pass"
      prepend-icon="mdi-lock"
    >
    </v-text-field>
    <v-text-field
      class="pl-3 pr-3"
      label="비밀번호 확인"
      type="password"
      v-model="passcheck"
      prepend-icon="mdi-lock"
    >
    </v-text-field>
    <v-text-field
      class="pl-3 pr-3"
      label="닉네임"
      type="text"
      v-model="nickname"
      prepend-icon="mdi-account"
    >
    </v-text-field>
    <v-text-field
      class="pl-3 pr-3"
      label="이메일"
      type="email"
      v-model="email"
      prepend-icon="mdi-email"
    >
    </v-text-field>
    <v-text-field
      class="pl-3 pr-3"
      label="성별"
      type="text"
      v-model="gender"
      prepend-icon="mdi-account"
    >
    </v-text-field>
    <v-btn
      class="mt-3"
      color=""
      outlined
      style="margin: 0px 10px 0px 0px"
      @click="checkValue"
      >회원가입</v-btn
    >
    <v-btn class="mt-3" color="" outlined @click="resetValue">취소</v-btn>
  </v-container>
</template>

<script>
import http from "@/config/http-common.js";
import Swal from "sweetalert2";
export default {
  data() {
    return {
      nickname: "",
      pass: "",
      passcheck: "",
      email: "",
      id: "",
      gender: "",
    };
  },
  methods: {
    checkValue: function () {
      if (
        this.nickname === "" ||
        this.pass === "" ||
        this.passcheck === "" ||
        this.id === "" ||
        this.gender === "" ||
        this.email === ""
      ) {
        Swal.fire({
          icon: "error",
          title: "Stop!",
          text: "입력하지 않은 정보가 있는지 확인하세요",
        });
      } else if (this.pass !== this.passcheck) {
        Swal.fire({
          icon: "error",
          title: "PasswordNoMatch",
          text: "비밀번호가 일치하지 않습니다.",
        });
      } else {
        Swal.fire({
          icon: "question",
          text: "이대로 가입하시겠습니까?",
          showCancelButton: true,
          confirmButtonText: "네",
        }).then((result) => {
          if (result.isConfirmed) {
            this.registUser();
          } else {
            Swal.fire({
              icon: "warning",
              text: "취소되었습니다.",
            });
          }
        });
      }
    },
    registUser() {
      http
        .post(`/user/signup`, {
          id: this.id,
          password: this.pass,
          email: this.email,
          nickname: this.nickname,
          gender: this.gender,
        })
        .then(() => {
          sessionStorage.setItem("id", this.id);
          let msg = "등록에 문제가 생겼습니다.";
          // 서버에서 정상적인 값이 넘어 왔을경우 실행.
          msg = "회원가입을 축하합니다. 선호하는 향을 선택해주세요.";
          Swal.fire({
            icon: "success",
            text: msg,
          });
          this.$router.push("/scent");
        })
        .catch((err) => {
          Swal.fire({
            icon: "error",
            text: err.response.data,
          });
        });
    },
    resetValue() {
      this.$router.push("/");
    },
  },
};
</script>

<style>
.con {
  position: relative;
  top: 50px;
  outline: 3px solid black;
}
</style>
