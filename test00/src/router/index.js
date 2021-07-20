import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import About from '../views/About.vue'
import SearchDetail from '../views/SearchDetail.vue'
import Start from '../views/Start.vue'
import CompanyStart from '../views/CompanyStart.vue'
import UserMain from '../views/UserMain.vue'

const routes = [
  {
    path: '/home',
    name: 'Home',
    component: Home
  },
  {
    path: '/about',
    name: 'About',
    component: About
  },
  {
    path: '/searchDetail',
    name: 'SearchDetail',
    component: SearchDetail
  },
  {
    path: '/',
    name: 'Start',
    component: Start
  },
  {
    path:'/',
    name: 'Start',
    component: Start
  },
  {
    path:'/company',
    name:'CompanyStart',
    component: CompanyStart

  },
  {
    path: '/usermain',
    name: 'UserMain',
    component: UserMain
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
