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

app.use(router);
router.app = app;
app.use(store);
app.use(VueCooKie, {
  expireTime: "0",
  secure:true,
});
// .use(AxiosPlugin)
app.mount("#app");

//set default config
// app.$cookies.config("21600", "", "", true);
