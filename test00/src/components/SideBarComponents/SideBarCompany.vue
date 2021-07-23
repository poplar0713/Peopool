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
        <!--  -->
        <el-menu-item index="1" @click="dialogVisible = true">
          <i
            class="el-icon-office-building
"
          ></i>
          <span>Profile</span>
        </el-menu-item>
        <el-menu-item index="2">
          <i class="el-icon-camera"></i>
          <span>Interview List</span>
        </el-menu-item>
        <el-menu-item index="3">
          <i class="el-icon-star-on"></i>
          <span>Following Applicants</span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-setting"></i>
          <span>setting</span>
        </el-menu-item>
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
export default {
  components: {},
  methods: {
    ch(data) {
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
