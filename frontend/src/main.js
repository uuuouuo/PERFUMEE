import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import Vuetify from "vuetify/lib";
import VueProfile from "vue-profile";

Vue.config.productionTip = false;
Vue.use(Vuetify);
Vue.use(VueProfile, "vue-profile");
new Vue({
  router,
  store,
  vuetify,
  render: (h) => h(App),
}).$mount("#app");
