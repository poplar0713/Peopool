<template>
  <!-- UserSchedule -->
  <el-container>
    <el-aside width="200px"><SideBarUser :usertoken="index"/></el-aside>
    <el-container>
      <el-header><headerSearchCompany /></el-header>
      <el-main style="">
        <h1>태그별 기업 찾아보기</h1>
        <SelectCompanyTags />
      </el-main>
      
    </el-container>
  </el-container>
  <router-view></router-view>
</template>
<script>
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
import headerSearchCompany from "@/components/SideBarComponents/headerSearchCompany.vue";
import SelectCompanyTags from "@/components/ViewCompany/SelectCompanyTags.vue";
import jwt_decode from "jwt-decode";
import axios from "axios";


export default {
  name: "ViewCompany",
  components: {
    SideBarUser,
    headerSearchCompany,
    SelectCompanyTags,
  },
  mounted() {},
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
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
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
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
          this.$cookies.remove("PID_AUTH");
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
          query: { keyword: `${keyword}` },
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
