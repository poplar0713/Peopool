<template>
  <el-container>
    <el-aside class="sidebar" width="200px">
      <router-link to="/company" style="text-decoration: none;">
        <div class="title">
          <div>
            <span class="f">P</span>
            <span class="s">eo</span>
            <span class="s">P</span>
            <span class="f">ool</span>
          </div>
        </div>
      </router-link>
      <el-menu background-color="#f1c40f">
        <el-menu-item
          index="1"
          onclick="location.href = '/company'"
          style="text-align:center"
        >
          <i class="fas fa-home"></i>&nbsp;&nbsp;Home
        </el-menu-item>
        <!--  -->
        <el-menu-item index="2" style="text-align:center">
          <i class="el-icon-office-building"></i>
          <router-link
            :to="{
              name: 'ProfileCompany',
              params: { companyindex: companyindex },
            }"
            style="text-decoration: none; color:black"
            >Company</router-link
          >
        </el-menu-item>
        <!--  -->
        <el-menu-item index="3" style="text-align:center">
          <i class="el-icon-right"></i>
          <i class="el-icon-user-solid"></i>
          <span><CompanyFollowings /></span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="4" style="text-align:center">
          <i class="el-icon-office-building"></i>
          <i class="el-icon-back"></i>
          <span><CompanyFollowers /></span>
        </el-menu-item>
        <el-menu-item index="5" style="text-align:center">
          <i class="el-icon-star-on"></i>
          <span @click="GoToRecruit">Recruitment</span>
        </el-menu-item>
        <el-menu-item index="6" style="text-align:center">
          <i class="el-icon-setting"></i>
          <span>setting</span>
        </el-menu-item>
        <el-menu-item index="7" style="text-align:center">
          <i class="el-icon-turn-off"></i>
          <span @click="Logout">Logout</span>
        </el-menu-item>
        <!--  -->
      </el-menu>
    </el-aside>
    <router-view></router-view>
  </el-container>
</template>

<script>
// import ProfileCompany from "@/components/SideBarComponents/ProfileCompany.vue";
import CompanyFollowings from "@/components/SideBarComponents/FollowOfCompany/CompanyFollowings.vue";
import CompanyFollowers from "@/components/SideBarComponents/FollowOfCompany/CompanyFollowers.vue";
import jwt_decode from "jwt-decode";
export default {
  name: "SideBarCompany",
  components: {
    CompanyFollowers,
    CompanyFollowings,
    // ProfileCompany,
  },
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    return {
      value: [],
      companyindex: index,
    };
  },
  methods: {
    Logout() {
      // 로딩페이지
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
        this.$cookies.remove("PID_AUTH");
        this.$router.push("/");
        this.$message({
          message: "로그아웃",
          type: "success",
        });
      }, 2000);
    },
    GoToRecruit() {
      this.$router.push("recruiting");
    },
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Work+Sans:ital,wght@1,700&display=swap");
.sidebar {
  position: fixed;
  height: 100%;
  overflow: auto;
  font-size: 30px;
  z-index: 1000;
}
.el-menu {
  height: 90%;
}
.title {
  margin: 2%;
  text-align: center;
  height: 6%;
}
.title span {
  font-family: "Work Sans", sans-serif;
  font-size: 40px;
}
.title {
  margin: 10px;
  text-align: center;
}
.f {
  color: #ffc000;
}
i {
  color: black !important;
}
span {
  color: black;
}
</style>
