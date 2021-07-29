<template>
  <el-container>
    <el-aside width="200px"><SideBarUser /></el-aside>
    <el-container></el-container>
  </el-container>
</template>

<script>
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
export default {
  components: {
    SideBarUser,
  },
  name: "InterviewRoom",
};
</script>

<style></style>
