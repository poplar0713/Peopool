import { createApp } from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
import installElementPlus from "./plugins/element";

// import vueMoment from "vue-moment";
// Vue.use(vueMoment);

// // kakaotalk
// window.Kakao.init("e5254ff19094b2f428ebe96dfb28c1a7");
// // firebase
// require("../node_modules/firebaseui/dist/firebaseui.js");
// require("../node_modules/firebaseui/dist/firebaseui.css");

const app = createApp(App);
installElementPlus(app);
app
  .use(router)
  .use(store)
  // .use(AxiosPlugin)
  .mount("#app");
