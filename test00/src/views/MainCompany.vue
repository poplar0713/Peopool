<template>
  <el-container>
    <el-aside width="200px"><SideBarCompany /></el-aside>
    <el-container>
      <el-header><headerSearchUser /></el-header>

      <el-main>
        <el-row :gutter="20">
          <el-col :span="12"
            ><div class="grid-content bg-purple"><CompanySugInterview /></div
          ></el-col>
          <el-col :span="12"
            ><div class="grid-content bg-purple"><check-annc /></div
          ></el-col>
        </el-row>

        <FollowerAppc title="Today 우리회사 관심 피풀인" />
        <FollowingAppc title="Today 눈여겨보는 관심 피풀인" />
        <!-- <webviewer initialDoc="파이팅 프런트.docx" /> -->
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBarCompany from "@/components/SideBarComponents/SideBarCompany.vue";
import FollowerAppc from "@/components/MainCompany/FollowerAppc.vue";
import FollowingAppc from "@/components/MainCompany/FollowingAppc.vue";
import CheckAnnc from "@/components/MainCompany/CheckAnnc.vue";
import CompanySugInterview from "@/components/MainCompany/CompanySugInterview.vue";
// import webviewer from "@/components/MainCompany/webviewer.vue";
import headerSearchUser from "@/components/SideBarComponents/headerSearchUser.vue";

import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  name: "MainCompany",
  components: {
    SideBarCompany,
    CheckAnnc,
    FollowerAppc,
    FollowingAppc,
    // webviewer,
    headerSearchUser,
    CompanySugInterview,
  },
  mounted() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.company_index = index;
    //팔로잉정보 가져오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/fol/follower", {
        params: {
          index: index,
          type: 1,
        },
        headers: { Authorization: token },
      })
      // 팔로워데이터 넣어주기
      .then((res) => {
        console.log(res);
        this.follower = res.data;
      })
      .catch((err) => {
        console.log("token error");
        console.log(err.response.data.status);
        if (err.response.data.status == 401) {
          alert("로그인세션이이 만료 되었습니다.");
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
  data() {
    return {
      tableData: [
        {
          state: true,
          date: "2016-05-03",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 0,
        },
      ],
      follower: [],
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style></style>
