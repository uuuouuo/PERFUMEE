<template>
  <div class="container text-center">
    <h2 class="fw-bold my-5 text-start">프로필</h2>
    <vue-profile
      :nickname="`${userInfo.id}`"
      :profileImg="Img"
      coverColor="#5498e9"
    />
    <v-dialog v-model="dialog" width="500">
      <template v-slot:activator="{ on, attrs }">
        <v-btn
          color="red lighten-2"
          dark
          v-bind="attrs"
          v-on="on"
          style="margin: 0px 10px 0px 0px"
        >
          회원정보 수정
        </v-btn>
      </template>
      <v-card>
        <v-card-title class="text-h5 grey lighten-2">
          회원정보수정
        </v-card-title>
        <v-card-text>
          <v-text-field label="변경할 닉네임" v-model="user.nickame">
          </v-text-field>
          <v-text-field
            type="password"
            label="변경할 비밀번호"
            v-model="user.pass"
          >
          </v-text-field>
          <v-text-field
            type="password"
            label="변경할 비밀번호 재입력"
            v-model="user.passcheck"
          >
          </v-text-field>
          <v-text-field label="변경할 이메일 입력" v-model="user.email">
          </v-text-field>
        </v-card-text>

        <v-divider></v-divider>

        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn color="primary" text @click="checkValue"> 확인 </v-btn>
          <v-btn color="primary" text @click="dialog = false"> 취소 </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import http from "@/config/http-common.js";
import Swal from "sweetalert2";

const userStore = "userStore";
export default {
  data() {
    return {
      user: {
        profileImage: "",
        nickame: "",
        pass: "",
        passcheck: "",
        email: "",
      },
      Img: require("@/assets/logo.png"),
      modalCheck: false,
    };
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    ...mapActions(userStore, ["getUserInfo"]),
    deleteConfirm() {
      Swal.fire({
        icon: "question",
        text: "탈퇴하시겠습니까?",
        showCancelButton: true,
        confirmButtonText: "네",
      }).then((result) => {
        if (result.isConfirmed) {
          this.deleteUser();
        } else {
          Swal.fire({
            icon: "info",
            text: "탈퇴가 취소되었습니다.",
          });
        }
      });
    },
    deleteUser() {
      http({
        method: "delete",
        url: `/user/${this.userInfo.id}`,
      })
        .then((response) => {
          console.log(response);
          this.SET_IS_LOGIN(false);
          this.SET_USER_INFO(null);
          this.$router.push({
            path: "/",
          });
          Swal.fire({
            icon: "info",
            text: "탈퇴가 완료되었습니다.",
          });
        })
        .catch((err) => {
          console.dir(err);
        });
    },
    getUserData() {
      this.modalCheck = true;
      this.user.nickame = this.userInfo.nickname;
      this.user.email = this.userInfo.email;
      this.user.gender = this.userInfo.gender;
    },
    checkValue: function () {
      if (this.user.nickame === "") {
        Swal.fire({
          icon: "error",
          title: "Stop!",
          text: "닉네임은 필수 입력사항입니다.",
        });
      } else if (this.user.pass !== this.user.passcheck) {
        Swal.fire({
          icon: "error",
          title: "PasswordNotMatch",
          text: "비밀번호가 일치하지 않습니다",
        });
      } else if (this.user.nickname.length > 30) {
        Swal.fire({
          icon: "error",
          title: "UsernameError",
          text: "닉네임은 30자를 넘을 수 없습니다.",
        });
      } else if (this.user.nickname === this.userInfo.nickname) {
        Swal.fire({
          icon: "question",
          text: "닉네임을 그대로 유지하시겠습니까?",
          showCancelButton: true,
          confirmButtonText: "네",
        }).then((result) => {
          if (result.isConfirmed) {
            this.userInfoUpdate();
          }
        });
      } else {
        this.userInfoUpdate();
      }
    },
    userInfoUpdate() {
      console.log(this.user.profileImage);
      let obj = {};
      if (this.user.user_pw) {
        obj = {
          password: this.user.pass,
          id: this.userInfo.id,
          nickname: this.user.nickname,
          email: this.user.email,
          gender: this.user.gender,
        };
      } else {
        obj = {
          id: this.userInfo.id,
          nickame: this.user.nickame,
          email: this.user.email,
          gender: this.user.gender,
        };
      }
      http({
        method: "put",
        url: `/user/${this.userInfo.id}`,
        data: obj,
      })
        .then((response) => {
          console.log(response);
          Swal.fire({
            icon: "success",
            text: "회원정보 수정이 완료되었습니다.",
          });
          let token = sessionStorage.getItem("access-token");
          this.getUserInfo(token);
          this.user.pass = "";
          this.user.passcheck = "";
          this.modalCheck = false;
        })
        .catch((err) => {
          console.dir(err);
        });
    },
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
};
</script>

<style></style>
