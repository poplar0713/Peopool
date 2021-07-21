<template>
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
  <!-- <router-view></router-view> -->
</template>

<script>
export default {};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Work+Sans:ital,wght@1,700&display=swap");

.sidebar {
  /* position: fixed;
  height: 100%;
  overflow: auto; */
}
.el-menu {
  /* height: 100%; */
}

span {
  color: black;
}
.title {
  font-family: "Work Sans", sans-serif;
  font-size: 40px;
  text-decoration: none;
  /* padding: 10px; */
  /* display: flex;
  align-content: center;
  justify-content: center;
  height: 10%; */
}

.f {
  color: #ffc000;
}
</style>
