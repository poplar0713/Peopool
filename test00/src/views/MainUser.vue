<template>
  <!-- UserSchedule -->
  <el-container>
    <el-aside width="200px"><SideBarUser /></el-aside>
    <el-container>
      <el-header><headerSearchCompany /></el-header>
      <el-main>
        <h1>인기있는 기업 랭킹</h1>
        <PopularCompanyList />
        <br>
        <el-row :gutter="20">
          <el-col
            :span="12"
            style="background-color:#FAFAFA; border-radius: 2em;"
            ><div class="grid-content bg-purple">
              <h4 style="text-align:center">요청받은 인터뷰</h4>
              <UserSugInterview /></div
          ></el-col>
          <el-col
            :span="12"
            style="background-color:#FAFAFA; border-radius: 2em;"
            ><div class="grid-content bg-purple">
              <h4 style="text-align:center">인터뷰 일정</h4>
              <UserSchedule /></div
          ></el-col>
        </el-row>
      </el-main>
    </el-container>
  </el-container>
  <router-view></router-view>
</template>
<script>
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
import headerSearchCompany from "@/components/SideBarComponents/headerSearchCompany.vue";
import UserSugInterview from "@/components/MainUser/UserSugInterview.vue";
import UserSchedule from "@/components/MainUser/UserSchedule.vue";
import PopularCompanyList from "@/components/MainUser/PopularCompanyList.vue";

import jwt_decode from "jwt-decode";
import axios from "axios";
import server_url from "@/server.js";
// import wsocket from "@/components/utils/websocket.js";

let ws = null;
export default {
  name: "MainUser",
  components: {
    SideBarUser,
    PopularCompanyList,
    UserSugInterview,
    UserSchedule,
    headerSearchCompany,
  },
  created() {
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    ws = new WebSocket(`wss://i5d206.p.ssafy.io:8443/ws/${index}`);
  },
  mounted: function() {
    console.log("mounted start - ", ws);
    ws.onopen = () => {
      console.log("loginpage - Websocket is connected!");
      this.sendMessage({
        id: "sessioncheck",
      });
    };
    ws.onmessage = (message) => {
      console.log("ws onmessage- ", message);
    };
    // ws.onclose = function() {
    //   setTimeout(
    //     (this.ws = new WebSocket("wss://i5d206.p.ssafy.io:8443/groupcall")),
    //     300
    //   ); // 웹소켓을 재연결하는 코드 삽입
    // };

    console.log(server_url);
  },
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    console.log("타입확인");
    console.log(decoded.type);
    // 회원정보 가져오기
    axios
      .get(`https://i5d206.p.ssafy.io:8443/ind/${index}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res.data.ind_name);
        this.username = res.data.ind_name;
        localStorage.setItem("username", res.data.ind_name);
      })
      .catch((err) => {
        console.log("token error");
        console.log(err.response);
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    // 유저본인 태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/has/tag", {
        headers: { Authorization: token },
        params: {
          index: index,
          type: 0,
        },
      })
      .then((res) => {
        console.log(res);
        this.mytags = res.data;
      })
      .catch((err) => {
        if (err.response == 401) {
          console.log("token error");
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    return {
      username: "",
      mytags: [],
    };
  },
  methods: {
    sendMessage(message) {
      var jsonMessage = JSON.stringify(message);
      console.log("Sending message: " + jsonMessage);
      ws.send(jsonMessage);
    },
    uploadFile() {},
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file.size);
    },
    handleExceed() {
      this.$message.warning(`최대 1개의 파일만 업로드 가능합니다`);
    },
    beforeRemove(file) {
      return this.$confirm(`Cancel the transfert of ${file.name} ?`);
    },
    // 해당 태그의 기업들 검색으로
    GetTagCompany(keyword) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
        this.$router.push({
          name: "SearchCompany",
          query: { keyword: keyword },
        });
      }, 2000);
      setTimeout(() => {
        location.reload();
      }, 2001);
    },
  },
};
</script>

<style>
.el-main {
  margin: 10px 10px 10px 10px;
  background-color: white;
  border-radius: 20px;
}
</style>
