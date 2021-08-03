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
    type: 0,
    othertype: 0,
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
