import { createRouter, createWebHistory } from 'vue-router'
import MainUser from '../views/MainUser.vue'

import SearchDetail from '../views/SearchDetail.vue'
import Start from '../views/Start.vue'
import MainCompany from '../views/MainCompany.vue'
import InterviewRoom from '../views/InterviewRoom.vue'

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
    path: "/",
    name: "Start",
    component: Start,
  },
  {
    path: "/interview",
    name: "InterviewRoom",
    component: InterviewRoom,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
