<<<<<<< HEAD
import { createRouter, createWebHistory } from 'vue-router'
import MainUser from '../views/MainUser.vue'
import About from '../views/About.vue'
import SearchDetail from '../views/SearchDetail.vue'
import Start from '../views/Start.vue'
import MainCompany from '../views/MainCompany.vue'
import InterviewRoom from '../views/InterviewRoom.vue'

const routes = [
  {
    path: '/',
    name: 'Start',
    component: Start
  },
  {
    path: '/user/:userid',
    name: 'MainUser',
    component: MainUser
  },
  {
    path:'/company/:companyid',
    name:'MainCompany',
    component: MainCompany

=======
import { createRouter, createWebHistory } from "vue-router";
import MainUser from "../views/MainUser.vue";
import About from "../views/About.vue";
import SearchDetail from "../views/SearchDetail.vue";
import Start from "../views/Start.vue";
import MainCompany from "../views/MainCompany.vue";
import Search from "../components/search/SearchList.vue";
const routes = [
  {
    path: "/user",
    name: "MainUser",
    component: MainUser,
  },
  {
    path: "/company",
    name: "MainCompany",
    component: MainCompany,
  },
  {
    path: "/about",
    name: "About",
    component: About,
>>>>>>> front
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
<<<<<<< HEAD
    path: '/interviewroom',
    name: 'InterviewRoom',
    component: InterviewRoom
=======
    path: "/search",
    name: "Search",
    component: Search,
>>>>>>> front
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
