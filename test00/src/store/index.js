import { createStore } from "vuex";
import $axios from "axios";

export default createStore({
  state: {
    url: "/",
    LoginDialog: false,
    SignupDialogIndiv: false,
    SignupDialogCompany: false,
    findUserId: false,
    findUserPw: false,
    findCompanyId: false,
    findCompanyPw: false,
    type: -1,
    othertype: -1,
  },
  mutations: {},
  actions: {
    requestLoginind({ state }, payload) {
      console.log("requestLoginind", state, payload);
      const url = "https://i5d206.p.ssafy.io:8443/auth/login";
      let body = payload;
      return $axios.post(url, body);
    },
    requestLoginent({ state }, payload) {
      console.log("requestLoginent", state, payload);
      const url = "https://i5d206.p.ssafy.io:8443/auth/loginent";
      let body = payload;
      return $axios.post(url, body);
    },
  },
  modules: {},
});
