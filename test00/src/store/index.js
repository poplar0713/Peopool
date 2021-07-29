import { createStore } from "vuex";
import $axios from 'axios'

export default createStore({
  state: {
    LoginDialog: false,
    SignupDialogIndiv: false,
    SignupDialogCompany: false,
    fileList: [
      {
        name: "자소서",
        url:
          "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100",
      },
      {
        name: "이력서",
        url:
          "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100",
      },
      {
        name: "인턴보고서",
        url:
          "https://fuss10.elemecdn.com/3/63/4e7f3a15429bfda99bce42a18cdd1jpeg.jpeg?imageMogr2/thumbnail/360x360/format/webp/quality/100",
      },
    ],
  },
  mutations: {},
  actions: {
    requestLogin({ state }, payload) {
      console.log("requestLogin", state, payload);
<<<<<<< HEAD
      const url = "/ind/login";
=======
      const url = "/auth/login";
>>>>>>> 6d5f31f6ba1d47e64438d2220e3772795da394fa
      let body = payload;
      return $axios.post(url, body);
    },
  },
  modules: {},
});
