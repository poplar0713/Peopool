<template>
  <el-tabs :tab-position="tabPosition" style="height: 100%;">
    <el-tab-pane label="Basic Info"><SideBarProfileUserInfo /></el-tab-pane>
    <el-tab-pane label="Level of Education"
      ><SideBarProfileUserEducation
    /></el-tab-pane>
    <el-tab-pane label="Introduction"
      ><TSideBarProfileUserIntroduction
    /></el-tab-pane>
    <el-tab-pane label="PR Video"
      ><PRVideo
    /></el-tab-pane>
    <el-tab-pane label="Tags"><SideBarProfileUserTags /></el-tab-pane>
  </el-tabs>
</template>

<script>
import SideBarProfileUserInfo from "./SideBarProfileUserInfo.vue";
import SideBarProfileUserEducation from "./SideBarProfileUserEducation.vue";
import TSideBarProfileUserIntroduction from "./TSideBarProfileUserIntroduction.vue";
import SideBarProfileUserTags from "./SideBarProfileUserTags.vue";
import PRVideo from "./PRVideo.vue";
export default {
  components: {
    SideBarProfileUserInfo,
    SideBarProfileUserEducation,
    TSideBarProfileUserIntroduction,
    SideBarProfileUserTags,
    PRVideo,
  },
  data() {
    return {
      tabPosition: "left",
    };
  },
};
</script>

<style></style>
