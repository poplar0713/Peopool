<template>
  <el-container class="mainLayout">
    <el-aside width="250px"><SideBarUser /></el-aside>

    <el-container>
      <el-header><Header /></el-header>
      <el-main>
        <!-- 메인 컴포넌트 부분 -->
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
import Header from "@/components/SideBarComponents/header.vue";

export default {
  components: {
    SideBarUser,
    Header,
  },
};
</script>

<style></style>