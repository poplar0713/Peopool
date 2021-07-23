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
        <el-menu-item index="3">
          <i class="el-icon-setting"></i>
          <span><DragSetting /></span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="5">
          <i class="el-icon-video-camera"></i>
          <span>Interview</span>
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
          <span>Logout</span>
        </el-menu-item>
        <!--  -->
      </el-menu>
    </el-aside>
    <router-view></router-view>
  </el-container>
</template>

<script>
import DragSetting from "@/components/Recruitment/DragSetting.vue";
import ProfileUser from "@/components/SideBarComponents/ProfileUser.vue";
export default {
  components: {
    DragSetting,
    ProfileUser,
  },
  methods: {
    ch(data) {
      console.log(data);
      this.$router.push("searchDetail");
    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    search(data) {
      console.log(data);
      this.$router.push("searchDetail");
    },
  },
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
