<template>
  <!-- TabSchedule -->
  <el-container>
    <el-aside width="200px"><SideBarUser /></el-aside>
    <el-container>
      <el-header><headerSearchCompany /></el-header>
      <el-main>
        <el-row :gutter="20">
          <el-col :span="11"
            ><div class="grid-content bg-purple">
              <el-divider content-position="left">내정보</el-divider><MyInfo /></div
          ></el-col>
          <el-col :span="6"
            ><div class="grid-content bg-purple">
              <el-divider content-position="left">요청받은 인터뷰</el-divider
              ><TabRequiredInterview /></div
          ></el-col>
          <el-col :span="7"
            ><div class="grid-content bg-purple">
              <el-divider content-position="left">인터뷰 일정</el-divider><TabSchedule /></div
          ></el-col>
        </el-row>
      </el-main>
      <el-footer>
        <el-divider content-position="left">기업 정보</el-divider>
        <CompanyList />
        <el-divider content-position="left">관심기업 정보</el-divider>
        <CompanyList />
      </el-footer>
    </el-container>
  </el-container>
</template>
<script>
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
import TabRequiredInterview from "@/components/MainUser/TabRequiredInterview.vue";
import MyInfo from "@/components/MainUser/MyInfo.vue";
import TabSchedule from "@/components/MainUser/TabSchedule.vue";
import CompanyList from "@/components/MainUser/CompanyList.vue";
import headerSearchCompany from "@/components/SideBarComponents/headerSearchCompany.vue";

import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  components: {
    SideBarUser,
    CompanyList,
    TabRequiredInterview,
    TabSchedule,
    MyInfo,

    headerSearchCompany,
  },
  data() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 회원정보 가져오기
    axios
      .get(`https://localhost:8443/ind/${index}`)
      .then((res) => {
        console.log(res.data.ind_name);
        this.username = res.data.ind_name;
      })
      .catch((err) => {
        console.log(err);
      });
    return {
      mainsearch: "",
      search: "",
      username: "",
      hasVideo: true,
      hasDoc: true,
      videosrc: "@/assets/samplevideo.mp4",
      fileList: [],
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
  },
};
</script>

<style>
.el-main {
  margin: 10px 10px 10px 10px;
  background-color: white;
  border-radius: 20px;
}

/* .main {
  border-radius: 20px 20px 20px 20px;
  margin-top: 2%;
  margin-left: 2%;
  margin-right: 2%;
  background-color: #f4f6f6;
  position: relative;
}
.videoNotFound {
  border-radius: 20px 20px 20px 20px;
  width: 440px;
  height: 330px;
  border-color: gray;
  border-width: 4px;
  border-style: dotted;
  text-align: center;
  background-color: white;
}
video {
  margin: 1%;
  height: auto;
  max-width: 1280px;
}
.section {
  border-radius: 20px 20px 20px 20px;
  width: auto;
  height: auto;
  margin: 1%;
  padding: 1%;
  background-color: white;
}
.panel-tabs {
  height: 500px;
}
.panel {
  overflow: scroll;
} */
</style>
