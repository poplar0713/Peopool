<template>
  <el-tabs :tab-position="tabPosition" style="height: 100%;">
    <el-tab-pane label="기본정보"><SideBarProfileUserInfo /></el-tab-pane>
    <!-- <el-tab-pane label="Level of Education"><SideBarProfileUserEducation /></el-tab-pane> -->
    <el-tab-pane label="소개"><SideBarProfileUserIntroduction /></el-tab-pane>
    <el-tab-pane label="소개영상"><PRVideo /></el-tab-pane>
    <el-tab-pane label="태그관리"><SideBarProfileUserTags /></el-tab-pane>
    <el-tab-pane label="서류관리"><SideBarProfileUserDoc /></el-tab-pane>
    <el-tab-pane label="회원탈퇴"><DeleteUserAccount /></el-tab-pane>
  </el-tabs>
</template>

<script>
import SideBarProfileUserInfo from "./SideBarProfileUserInfo.vue";
import SideBarProfileUserIntroduction from "./SideBarProfileUserIntroduction.vue";
import SideBarProfileUserTags from "./SideBarProfileUserTags.vue";
import PRVideo from "./PRVideo.vue";
import SideBarProfileUserDoc from "./SideBarProfileUserDoc.vue";
import DeleteUserAccount from "./DeleteUserAccount.vue";
export default {
  components: {
    SideBarProfileUserInfo,
    SideBarProfileUserIntroduction,
    SideBarProfileUserTags,
    PRVideo,
    SideBarProfileUserDoc,
    DeleteUserAccount,
  },
  data() {
    return {
      tabPosition: "left",
    };
  },
};
</script>

<style></style>
