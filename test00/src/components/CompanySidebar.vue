<template>
  <el-container>
    <el-aside width="200px">
      <router-link to="/home">
        <span class="title f">P</span>
        <span class="title s">eo</span>
        <span class="title s">P</span>
        <span class="title f">ool</span>
      </router-link>

      <el-menu
        default-active="3"
        class="el-menu-vertical-demo"
        background-color="#FFC000"
        text-color="#fff"
        active-text-color="#ffd04b"
      >
        <!--  -->
        <el-menu-item index="1">
          <i class="el-icon-menu"></i>
          <span>공고관리</span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="2">
          <i class="el-icon-menu"></i>
          <span>면접관리</span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="3">
          <i class="el-icon-document"></i>
          <span>관심 피플인</span>
        </el-menu-item>
        <!--  -->
        <el-menu-item index="4" disabled>
          <i class="el-icon-setting"></i>
          <span>settings</span>
        </el-menu-item>
        <!--  -->
      </el-menu>
    </el-aside>
    <router-view></router-view>
  </el-container>
</template>

<script>
export default {};
</script>

<style scoped>
i {
  color: black !important;
}

span {
  color: black;
}
.title {
  font-size: 40px;
  text-decoration: none;
}
.f {
  color: #ffc000;
}
</style>
