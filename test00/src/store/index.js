import { createStore } from "vuex";
import $axios from 'axios'

export default createStore({
  state: {
    temp: [
      { SignupIndivID: "" },
      { SignupIndivPW: "" },
      { SignupIndivPWConfirm: "" },
      { UserName: "" },
      { UserBirth: "" },
      { open: false },
      { Gender: "" },
      { UserTel: "" },
      { UserEmail: "" },
    ],
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
  mutations: {
    Signupdata(state, data) {
      this.state.temp = data;
    },
  },
  actions: {
    requestLogin({ state }, payload) {
      console.log("requestLogin", state, payload);
      const url = "/ind/login";
      let body = payload;
      return $axios.post(url, body);
    },
  },

  modules: {},
});
