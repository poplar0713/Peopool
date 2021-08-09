<template>
  <!-- UserSchedule -->
  <el-container>
    <el-aside width="200px"><SideBarUser /></el-aside>
    <el-container>
      <el-header><headerSearchCompany /></el-header>
      <el-main>
        <el-row :gutter="20">
          <el-col :span="12"
            ><div class="grid-content bg-purple">
              <el-divider content-position="left">요청받은 인터뷰</el-divider
              ><UserSugInterview /></div
          ></el-col>
          <el-col :span="12"
            ><div class="grid-content bg-purple">
              <el-divider content-position="left">인터뷰 일정</el-divider
              ><UserSchedule /></div
          ></el-col>
        </el-row>
        <div>
          <el-divider content-position="left">관심기업 정보</el-divider>
          <FollowingEntsList />
        </div>
        <div v-for="tag in mytags" :key="tag">
          <el-divider content-position="left">#{{tag.taglist_name}} 관련 기업</el-divider>
          <TagCompanyList :tag="tag.taglist_name"/>
        </div>
      </el-main>
      <el-footer> </el-footer>
    </el-container>
  </el-container>
</template>
<script>
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
import UserSugInterview from "@/components/MainUser/UserSugInterview.vue";
import UserSchedule from "@/components/MainUser/UserSchedule.vue";
import TagCompanyList from "@/components/MainUser/TagCompanyList.vue";
import FollowingEntsList from "@/components/MainUser/FollowingEntsList.vue";
import headerSearchCompany from "@/components/SideBarComponents/headerSearchCompany.vue";

import jwt_decode from "jwt-decode";
import axios from "axios";
import server_url from "@/server.js";

export default {
  name: "MainUser",
  components: {
    SideBarUser,
    TagCompanyList,
    FollowingEntsList,
    UserSugInterview,
    UserSchedule,
    headerSearchCompany,
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
        console.log(err.response.data.status);
        if (err.response.data.status == 401) {
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
        if (err.response.data.status == 401) {
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
</style>
