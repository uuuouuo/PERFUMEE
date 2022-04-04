import Vue from "vue";
import VueRouter from "vue-router";
import MainPage from "../views/MainPage.vue";
import SignupPage from "../components/user/SignupPage.vue";
import NoticePage from "../views/NoticePage.vue";
import NoticeList from "../components/notice/NoticeList.vue";
import NoticeView from "../components/notice/NoticeView.vue";
import NoticeModify from "../components/notice/NoticeModify.vue";
import NoticeWrite from "../components/notice/NoticeWrite.vue";
import NoticeDelete from "../components/notice/NoticeDelete.vue";
import PerfumePage from "../components/perfume/PerfumePage.vue";
import ProfilePage from "../components/user/ProfilePage.vue";
import ScentPage from "../components/user/ScentPage.vue";
import PerfumeDetail from "../components/perfume/PerfumeDetail.vue";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: MainPage,
  },
  {
    path: "/signUp",
    name: "signUp",
    component: SignupPage,
  },
  {
    path: "/notice",
    name: "NoticePage",
    component: NoticePage,
    redirect: "/notice/list",
    children: [
      {
        path: "/list",
        name: "NoticeList",
        component: NoticeList,
      },
      {
        path: "/view/:no",
        name: "NoticeView",
        component: NoticeView,
      },
      {
        path: "/modify/:no",
        name: "NoticeModify",
        component: NoticeModify,
      },
      {
        path: "/write",
        name: "NoticeWrite",
        component: NoticeWrite,
      },
      {
        path: "/delete/:no",
        name: "NoticeDelete",
        component: NoticeDelete,
      },
    ],
  },
  {
    path: "/perfume",
    name: "perfume",
    component: PerfumePage,
  },
  {
    path: "/perfume/detail/:perfumeId",
    name: "perfumeDetail",
    component: PerfumeDetail,
  },
  {
    path: "/profile",
    name: "profile",
    component: ProfilePage,
  },
  {
    path: "/scent",
    name: "scent",
    component: ScentPage,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
