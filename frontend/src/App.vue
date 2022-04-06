<template>
  <v-app>
    <v-app-bar app color="green" dark>
      <div v-if="isLogin" class="d-flex align-center">
        <router-link to="/">
          <v-img
            alt="Perfumee Logo"
            class="shrink mr-2"
            contain
            src="@/assets/logo.png"
            transition="scale-transition"
            width="100"
          />
        </router-link>
        <v-btn to="/perfume" style="margin: 0px 10px 0px 0px">향수</v-btn>
        <v-btn :to="{ name: 'NoticeList' }" style="margin: 0px 10px 0px 0px"
          >공지사항</v-btn
        >
        <v-btn to="/profile" style="margin: 0px 10px 0px 0px">{{
          userInfo.id
        }}</v-btn>
        <router-link to="/">
          <v-btn @click="onClickLogout()">로그아웃</v-btn>
        </router-link>
      </div>
      <div v-else class="d-flex align-center">
        <router-link to="/">
          <v-img
            alt="Perfumee Logo"
            class="shrink mr-2"
            contain
            src="@/assets/logo.png"
            transition="scale-transition"
            width="100"
          />
        </router-link>
        <v-dialog v-model="dialog" width="500">
          <template v-slot:activator="{ on, attrs }">
            <v-btn
              color="red lighten-2"
              dark
              v-bind="attrs"
              v-on="on"
              style="margin: 0px 10px 0px 0px"
            >
              로그인
            </v-btn>
          </template>
          <v-card>
            <v-card-title class="text-h5 grey lighten-2"> 로그인 </v-card-title>
            <!-- <v-alert type="error" v-if="isLoginError">
              아이디 또는 비밀번호를 확인하세요.
            </v-alert> -->
            <v-card-text>
              <v-text-field label="아이디" v-model="user.id"> </v-text-field>
              <v-text-field
                type="password"
                label="비밀번호"
                v-model="user.password"
                @keyup.enter="checkValue"
              >
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
        <v-btn to="/signUp" style="margin: 0px 10px 0px 0px">회원가입</v-btn>
        <v-btn :to="{ name: 'ItemPage' }" style="margin: 0px 10px 0px 0px"
          >향수</v-btn
        >
        <v-btn :to="{ name: 'NoticeList' }" style="margin: 0px 10px 0px 0px"
          >공지사항</v-btn
        >
      </div>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
import Swal from "sweetalert2";

const userStore = "userStore";

export default {
  name: "App",
  data() {
    return {
      dialog: false,
      id: "",
      user: {
        id: "",
        password: "",
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo", "isLoginError"]),
  },
  destroyed() {
    this.onclickLogout();
  },
  methods: {
    ...mapMutations(userStore, ["SET_IS_LOGIN", "SER_USER_INFO"]),
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      if (this.isLogin) {
        sessionStorage.setItem("id", this.user.id);
        this.user.id = "";
        this.user.password = "";
        await this.getUserInfo(token);
        this.$router.push("/list");
      }
    },
    checkValue: function () {
      if (this.user.password === "" || this.user.id == "") {
        Swal.fire({
          icon: "error",
          title: "Stop!",
          text: "아이디, 비밀번호는 필수 입력사항입니다.",
        });
      } else {
        this.confirm();
      }
    },
    onClickLogout() {
      sessionStorage.removeItem("access-token");
      sessionStorage.removeItem("id");
      this.SET_IS_LOGIN(false);
      sessionStorage.removeItem("vuex");
      this.dialog = false;
    },
  },
};
</script>

<style>
a {
  text-decoration: none;
  color: #787878;
}
</style>
