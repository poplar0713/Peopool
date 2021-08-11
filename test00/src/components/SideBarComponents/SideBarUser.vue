<template>
  <el-container>
    <el-aside class="sidebar" width="200px">
      <router-link to="/user" style="text-decoration: none;">
        <div class="title">
          <div>
            <span class="f">P</span>
            <span class="s">eo</span>
            <span class="s">P</span>
            <span class="f">ool</span>
          </div>
        </div>
      </router-link>
      <el-menu
        default-active="2"
        @open="handleOpen"
        @close="handleClose"
        background-color="#f1c40f"
      >
        <!--  -->
        <el-menu-item index="1">
          <i class="el-icon-user"></i>
          <span><ProfileUser /></span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="5">
          <i class="el-icon-video-camera"></i>
          <span><UserInterviewManage /></span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="5">
          <i class="el-icon-right"></i>
          <i class="el-icon-office-building"></i>
          <span><UserFollowings /></span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="5">
          <i class="el-icon-user-solid"></i>
          <i class="el-icon-back"></i>
          <span><UserFollowers /></span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="6" disabled>
          <i class="el-icon-document"></i>
          <span>혹시나</span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="7">
          <i class="el-icon-setting"></i>
          <span>settings</span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="7">
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
import ProfileUser from "@/components/SideBarComponents/ProfileUser.vue";
import UserInterviewManage from "@/components/SideBarComponents/UserOfInterview/UserInterviewManage.vue";
import UserFollowings from "@/components/SideBarComponents/FollowOfUser/UserFollowings.vue";
import UserFollowers from "@/components/SideBarComponents/FollowOfUser/UserFollowers.vue";
export default {
  components: {
    ProfileUser,
    UserInterviewManage,
    UserFollowings,
    UserFollowers,
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    // 로그아웃
    Logout() {
      // 깔끔하게 비우기
      localStorage.clear();
      // 로딩페이지
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
        this.$router.push("/");
        this.$message({
          message: "로그아웃",
          type: "success",
        });
      }, 2000);
    },
  },
  props:{usertoken:Number},
  data() {
    return {
      value: [],
    };
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Work+Sans:ital,wght@1,700&display=swap");
img {
  display: block;
  margin: 0px auto;
}
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
.title span {
  font-family: "Work Sans", sans-serif;
  font-size: 40px;
}
.f {
  color: #ffc000;
}
span {
  color: black;
}
.title {
  margin: 10px;
  text-align: center;
}
i {
  color: black !important;
}
</style>
