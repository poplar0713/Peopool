import { createStore } from "vuex";
import $axios from 'axios'

export default createStore({
  state: {
    url: "/",
    LoginDialog: false,
    SignupDialogIndiv: false,
    SignupDialogCompany: false,
    type: 0,
    othertype: 0,
    secretkey: "",
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
    requestLoginind({ state }, payload) {
      console.log("requestLoginind", state, payload);
      const url = "/auth/login";
      let body = payload;
      return $axios.post(url, body);
    },
    requestLoginent({ state }, payload) {
      console.log("requestLoginent", state, payload);
      const url = "/auth/loginent";
      let body = payload;
      return $axios.post(url, body);
    },
  },
  modules: {},
});
