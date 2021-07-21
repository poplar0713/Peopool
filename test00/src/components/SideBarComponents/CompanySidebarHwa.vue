<template>
  <el-container>
    <el-aside class="sidebar" width="250px">
      <router-link to="/home">
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
        class="el-menu-vertical-demo"
        background-color="#F4F6F6"
        text-color="#fff"
        active-text-color="#FFC000"
      >
        <el-menu-item index="1">
          <i class="el-icon-menu"></i>
          <span>공고관리</span>
        </el-menu-item>

        <el-menu-item index="2">
          <i class="el-icon-menu"></i>
          <span>면접관리</span>
        </el-menu-item>

        <el-menu-item index="3">
          <i class="el-icon-document"></i>
          <span>관심 피플인</span>
        </el-menu-item>

        <el-menu-item index="4" disabled>
          <i class="el-icon-setting"></i>
          <span>settings</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <!-- <router-view></router-view> -->
  </el-container>
</template>

<script>
export default {};
</script>

<style scoped>
.sidebar {
  position: fixed;
  height: 100%;
  overflow: auto;
}
.el-menu {
  height: 90%;
}
span {
  color: black;
}
.title {
  font-size: 40px;
  text-decoration: none;
  /* padding: 10px; */
  display: flex;
  align-content: center;
  justify-content: center;
  height: 10%;
}

.f {
  color: #ffc000;
}
</style>
