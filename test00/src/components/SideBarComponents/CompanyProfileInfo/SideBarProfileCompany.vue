<template>
  <h2>회사 정보 관리</h2>
  <h5>이곳에서 작성된 회사 정보가 피풀인들에게 보여집니다</h5>
  <el-divider />
  <el-tabs :tab-position="tabPosition" style="height: auto">
    <el-tab-pane label="기본정보"><SideBarProfileCompanyBasicInfo /></el-tab-pane>
    <el-tab-pane label="회사정보"><SideBarProfileCompanyInfo /></el-tab-pane>
    <el-tab-pane label="태그관리"><SideBarProfileCompanyTags /></el-tab-pane>
    <el-tab-pane label="회원탈퇴"><DeleteCompanyAccount /></el-tab-pane>
  </el-tabs>
</template>

<script>
import SideBarProfileCompanyBasicInfo from "./SideBarProfileCompanyBasicInfo.vue";
import SideBarProfileCompanyInfo from "./SideBarProfileCompanyInfo.vue";
import SideBarProfileCompanyTags from "./SideBarProfileCompanyTags.vue";
import DeleteCompanyAccount from "./DeleteCompanyAccount.vue";
export default {
  name: "SideBarProfileCompany",
  components: {
    SideBarProfileCompanyBasicInfo,
    SideBarProfileCompanyInfo,
    DeleteCompanyAccount,
    SideBarProfileCompanyTags,
  },
  data() {
    return {
      tabPosition: "left",
    };
  },
};
</script>

<style></style>
