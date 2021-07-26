import { createRouter, createWebHistory } from "vue-router";
import MainUser from "../views/MainUser.vue";

import SearchDetail from "../views/SearchDetail.vue";
import Start from "../views/Start.vue";
import MainCompany from "../views/MainCompany.vue";
import InterviewRoom from "../views/InterviewRoom.vue";
import SearchCompany from "../views/SearchCompany.vue";
import SearchUser from "../views/SearchUser.vue";
import Recruiting from "../views/Recruiting.vue";

const routes = [
  {
    path: "/",
    name: "Start",
    component: Start,
  },
  {
    path: "/user/:userid",
    name: "MainUser",
    component: MainUser,
  },
  {
    path: "/company",
    name: "MainCompany",
    component: MainCompany,
  },
  {
    path: "/searchDetail",
    name: "SearchDetail",
    component: SearchDetail,
  },
  {
    path: "/searchcompany",
    name: "SearchCompany",
    component: SearchCompany,
  },
  {
    path: "/",
    name: "Start",
    component: Start,
  },
  {
    path: "/user/interviewroom/:company/:user",
    name: "InterviewRoom",
    component: InterviewRoom,
  },
  {
    path: "/searchuser/:keyword",
    name: "searchuser",
    component: SearchUser,
  },
  {
    path: "/searchcompany",
    name: "searchcompany",
    component: SearchCompany,
  },
  {
    path: "/recruiting",
    name: "recruiting",
    component: Recruiting,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
