<template>
  <el-tabs :tab-position="tabPosition" style="height: 100%;">
    <el-tab-pane label="Basic Info"><SideBarProfileCompanyInfo /></el-tab-pane>
    <el-tab-pane label="Basic Info"><SideBarProfileCompanyInfo /></el-tab-pane>
  </el-tabs>
</template>

<script>
import SideBarProfileCompanyInfo from "./SideBarProfileCompanyInfo.vue";
export default {
  components: {
    SideBarProfileCompanyInfo,
  },
  data() {
    return {
      tabPosition: "left",
    };
  },
};
</script>

<style></style>
