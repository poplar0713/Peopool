import { createApp } from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
import installElementPlus from "./plugins/element";

// axios cors err
// import axios from 'axios' 
// import AxiosPlugin from 'vue-axios-cors';
// axios.defaults.headers.common['Content-Type'] = 'application/x-www-form-urlencoded' 
// axios.defaults.headers.common['Access-Control-Allow-Origin'] = '*';



// kakaotalk
window.Kakao.init("e5254ff19094b2f428ebe96dfb28c1a7");
// firebase
require("../node_modules/firebaseui/dist/firebaseui.js");
require("../node_modules/firebaseui/dist/firebaseui.css");

const app = createApp(App);
installElementPlus(app);
app
  .use(router)
  .use(store)
  // .use(AxiosPlugin)
  .mount("#app");
