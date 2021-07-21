import { createRouter, createWebHistory } from 'vue-router'
import UserStart from '../views/UserStart.vue'
import About from '../views/About.vue'
import SearchDetail from '../views/SearchDetail.vue'
import Start from '../views/Start.vue'
import CompanyStart from '../views/CompanyStart.vue'
import UserMain from '../views/UserMain.vue'
import TestPage from '../views/TestPage.vue'

const routes = [
  {
    path: '/userstart',
    name: 'UserStart',
    component: UserStart
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
    path:'/company',
    name:'CompanyStart',
    component: CompanyStart

  },
  {
    path: '/usermain',
    name: 'UserMain',
    component: UserMain
  },
  {
    path:'/testpage',
    name:'TestPage',
    component: TestPage
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
