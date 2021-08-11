import { createRouter, createWebHistory } from "vue-router";
import Start from "../views/Start.vue";
import MainUser from "../views/MainUser.vue";
import ProfileUser from "../views/ProfileUser.vue";
import MainCompany from "../views/MainCompany.vue";
import InterviewRoom from "../views/InterviewRoom.vue";
import SearchCompany from "../views/SearchCompany.vue";
import SearchUser from "../views/SearchUser.vue";
import Recruiting from "../views/Recruiting.vue";
import Inyh from "@/views/Interviewyh.vue";
const routes = [
  {
    path: "/meetinter",
    component: Inyh,
  },
  {
    path: "/",
    name: "Start",
    component: Start,
  },
  {
    path: "/user",
    name: "MainUser",
    component: MainUser,
    beforeEnter: function(to, from, next) {
      if (localStorage.getItem("token") == null) {
        alert("로그인이 필요한 서비스입니다");
        return next("/");
      }
      next();
    },
  },
  {
    path: "/profileuser",
    name: "ProfileUser",
    component: ProfileUser,
    beforeEnter: function(to, from, next) {
      if (localStorage.getItem("token") == null) {
        alert("로그인이 필요한 서비스입니다");
        return next("/");
      }
      next();
    },
  },
  {
    path: "/company",
    name: "MainCompany",
    component: MainCompany,
    beforeEnter: function(to, from, next) {
      if (localStorage.getItem("token") == null) {
        alert("로그인이 필요한 서비스입니다");
        return next("/");
      }
      next();
    },
  },
  {
    path: "/searchcompany/:keyword",
    name: "SearchCompany",
    component: SearchCompany,
    beforeEnter: function(to, from, next) {
      if (localStorage.getItem("token") == null) {
        alert("로그인이 필요한 서비스입니다");
        return next("/");
      }
      next();
    },
  },
  {
    path: "/searchuser/:keyword",
    name: "searchuser",
    component: SearchUser,
    beforeEnter: function(to, from, next) {
      if (localStorage.getItem("token") == null) {
        alert("로그인이 필요한 서비스입니다");
        return next("/");
      }
      next();
    },
  },
  {
    path: "/user/interviewroom/:company/:url",
    name: "InterviewRoom",
    component: InterviewRoom,
    // 링크공유로 로그인안해도 들어올수있도록
  },
  {
    path: "/searchcompany",
    name: "searchcompany",
    component: SearchCompany,
    beforeEnter: function(to, from, next) {
      if (localStorage.getItem("token") == null) {
        alert("로그인이 필요한 서비스입니다");
        return next("/");
      }
      next();
    },
  },
  {
    path: "/recruiting",
    name: "recruiting",
    component: Recruiting,
    beforeEnter: function(to, from, next) {
      if (localStorage.getItem("token") == null) {
        alert("로그인이 필요한 서비스입니다");
        return next("/");
      }
      next();
    },
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
