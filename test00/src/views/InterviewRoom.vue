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
  created() {
    var ws = new WebSocket("wss://localhost:8443" + "/groupcall");
    console.log(ws);
  },
  name: "InterviewRoom",
};
</script>

<style></style>
