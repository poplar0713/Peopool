<template>
  <el-container>
    <el-aside width="200px"><SideBarCompany /></el-aside>
    <el-container>
      <el-header><headerSearchUser /></el-header>
      <el-main style="width:60%;  text-align:center; margin:0 auto; padding: 70px 0;">
        <SideBarProfileCompany />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBarCompany from "@/components/SideBarComponents/SideBarCompany.vue";
import headerSearchUser from "@/components/SideBarComponents/headerSearchUser.vue";
import SideBarProfileCompany from "@/components/SideBarComponents/CompanyProfileInfo/SideBarProfileCompany.vue";

import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  name: "ProfileCompany",
  components: {
    SideBarCompany,
    headerSearchUser,
    SideBarProfileCompany,
  },
  mounted() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
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
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
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
