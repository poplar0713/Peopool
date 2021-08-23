import { createApp } from "vue";
import App from "./App.vue";
import store from "./store";
import router from "./router";
import installElementPlus from "./plugins/element";
import VueCooKie from "vue3-cookies";
import "./element-variables.scss";

// import vueMoment from "vue-moment";
// Vue.use(vueMoment);

const app = createApp(App);
installElementPlus(app);
window.Kakao.init("72049fede699eb7616f97679415da8af");
app.use(router);
router.app = app;
app.use(store);
app.use(VueCooKie, {
  expireTime: "0",
  secure: true,
});
// .use(AxiosPlugin)
app.mount("#app");

//set default config
// app.$cookies.config("21600", "", "", true);
