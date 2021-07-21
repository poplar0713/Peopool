<template>
  <el-container>
    <el-aside class="sidebar" width="250px">
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
          <span>관심피플인</span>
        </el-menu-item>
        <el-menu-item index="4">
          <i class="el-icon-setting"></i>
          <span>setting</span>
        </el-menu-item>
      </el-menu>
    </el-aside>
  </el-container>
</template>

<script>
export default {};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Work+Sans:ital,wght@1,700&display=swap");

.sidebar {
  position: fixed;
  height: 100%;
  overflow: auto;
  font-size: 30px;
}

span {
  color: black;
}
.title {
  margin: 10px;
  text-align: center;
}
.title span {
  font-family: "Work Sans", sans-serif;
  font-size: 50px;
}
.f {
  color: #ffc000;
}
</style>
