import jwt_decode from "jwt-decode";
import { findById, login } from "../../api/user";
import Swal from "sweetalert2";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: null,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.isLogin = true;
      state.userInfo = userInfo;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        (response) => {
          let token = response.data;
          commit("SET_IS_LOGIN", true);
          commit("SET_IS_LOGIN_ERROR", false);
          sessionStorage.setItem("access-token", token);
        },
        (error) => {
          console.log(error);
          console.log(error.response);
          if (error.response.data.message) {
            Swal.fire({
              icon: "error",
              text: error.response.data.message,
            });
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          } else if (error.response.data["login failed"]) {
            // 비밀번호 틀릴때 // 키값이 message가 아니라 login failed로 설정되어있음 // 키값에 공백있을때는 이렇게 접근
            Swal.fire({
              icon: "error",
              text: error.response.data["login failed"],
            });
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
          }
        }
      );
    },
    getUserInfo({ commit }, token) {
      let decode_token = jwt_decode(token);
      findById(
        decode_token["id"],
        (response) => {
          console.log(response.data);
          commit("SET_USER_INFO", response.data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default userStore;
