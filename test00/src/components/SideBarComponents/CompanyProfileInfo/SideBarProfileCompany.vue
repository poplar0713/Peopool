<template>
  <el-tabs :tab-position="tabPosition" style="height: 100%;">
    <el-tab-pane label="기본정보"><SideBarProfileCompanyBasicInfo /></el-tab-pane>
    <el-tab-pane label="회사정보"><SideBarProfileCompanyInfo /></el-tab-pane>
    <el-tab-pane label="회원탈퇴"><DeleteCompanyAccount /></el-tab-pane>
  </el-tabs>
</template>

<script>
import SideBarProfileCompanyBasicInfo from "./SideBarProfileCompanyBasicInfo.vue";
import SideBarProfileCompanyInfo from "./SideBarProfileCompanyInfo.vue";
import DeleteCompanyAccount from "./DeleteCompanyAccount.vue";
export default {
  components: {
    SideBarProfileCompanyBasicInfo,
    SideBarProfileCompanyInfo,
    DeleteCompanyAccount,
  },
  data() {
    return {
      tabPosition: "left",
    };
  },
};
</script>

<style></style>
