<template>
  <!-- UserSchedule -->
  <el-container>
    <el-aside width="200px"><SideBarUser /></el-aside>
    <el-container>
      <el-header><headerSearchCompany /></el-header>
      <el-header>프로필</el-header>
      <el-main>
        <el-tabs :tab-position="tabPosition" style="height: 100%;">
          <el-tab-pane label="기본정보"><SideBarProfileUserInfo /></el-tab-pane>
          <!-- <el-tab-pane label="Level of Education"><SideBarProfileUserEducation /></el-tab-pane> -->
          <el-tab-pane label="프로필사진 및 소개"
            ><SideBarProfileUserIntroduction
          /></el-tab-pane>
          <el-tab-pane label="소개영상"><PRVideo /></el-tab-pane>
          <el-tab-pane label="태그관리"><SideBarProfileUserTags /></el-tab-pane>
          <el-tab-pane label="서류관리"><SideBarProfileUserDoc /></el-tab-pane>
          <el-tab-pane label="회원탈퇴"><DeleteUserAccount /></el-tab-pane>
        </el-tabs>
      </el-main>
      <el-footer> </el-footer>
    </el-container>
  </el-container>
</template>
<script>
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
import headerSearchCompany from "@/components/SideBarComponents/headerSearchCompany.vue";
import SideBarProfileUserInfo from "@/components/SideBarComponents/UserProfileInfo/SideBarProfileUserInfo.vue";
import SideBarProfileUserIntroduction from "@/components/SideBarComponents/UserProfileInfo/SideBarProfileUserIntroduction.vue";
import SideBarProfileUserTags from "@/components/SideBarComponents/UserProfileInfo/SideBarProfileUserTags.vue";
import PRVideo from "@/components/SideBarComponents/UserProfileInfo/PRVideo.vue";
import SideBarProfileUserDoc from "@/components/SideBarComponents/UserProfileInfo/SideBarProfileUserDoc.vue";
import DeleteUserAccount from "@/components/SideBarComponents/UserProfileInfo/DeleteUserAccount.vue";

import jwt_decode from "jwt-decode";
import axios from "axios";
import server_url from "@/server.js";

export default {
  name: "MainUser",
  components: {
    SideBarUser,
    headerSearchCompany,
    SideBarProfileUserInfo,
    SideBarProfileUserIntroduction,
    SideBarProfileUserTags,
    PRVideo,
    SideBarProfileUserDoc,
    DeleteUserAccount,
  },
  mounted() {
    console.log(server_url);
  },
  data() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
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
      tabPosition: "left",
    };
  },
  methods: {
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
          params: { keyword: `${keyword}` },
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
