import { createStore } from "vuex";

export default createStore({
  state: {
    LoginDialog: false,
    SignupDialogIndiv: false,
    SignupDialogCompany: false,
    keyword: "",
  },
  mutations: {
    serachkeyword(state, str) {
      state.keyword = str;
    },
  },
  actions: {},
  modules: {},
});
