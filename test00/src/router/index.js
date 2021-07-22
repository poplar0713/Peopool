<<<<<<< HEAD
import { createRouter, createWebHistory } from 'vue-router'
import MainUser from '../views/MainUser.vue'

import SearchDetail from '../views/SearchDetail.vue'
import Start from '../views/Start.vue'
import MainCompany from '../views/MainCompany.vue'
import InterviewRoom from '../views/InterviewRoom.vue'
import SearchCompany from "../views/SearchCompany.vue";
=======
import { createRouter, createWebHistory } from "vue-router";
import MainUser from "../views/MainUser.vue";
>>>>>>> 06c599964b2529e2381e7b27d26e4717310c44ce

import SearchDetail from "../views/SearchDetail.vue";
import Start from "../views/Start.vue";
import MainCompany from "../views/MainCompany.vue";
import InterviewRoom from "../views/InterviewRoom.vue";
import SearchUser from "../views/SearchUser.vue";
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
    path: "/interview",
    name: "InterviewRoom",
    component: InterviewRoom,
  },
  {
    path: "/searchuser",
    name: "searchuser",
    component: SearchUser,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
