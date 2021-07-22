<template>
  <el-tabs :tab-position="tabPosition" style="height: 100%;">
    <el-tab-pane label="Basic Info"><TabProfileBasicInfo /></el-tab-pane>
    <el-tab-pane label="Level of Education"
      ><TabProfileEducation
    /></el-tab-pane>
    <el-tab-pane label="Introduction"><TabProfileIntroduction /></el-tab-pane>
    <el-tab-pane label="Tags"><TabProfileTags/></el-tab-pane>
  </el-tabs>
</template>

<script>
import TabProfileBasicInfo from "./TabProfileBasicInfo.vue";
import TabProfileEducation from "./TabProfileEducation.vue";
import TabProfileIntroduction from "./TabProfileIntroduction.vue";
import TabProfileTags from "./TabProfileTags.vue";
export default {
  components: {
    TabProfileBasicInfo,
    TabProfileEducation,
    TabProfileIntroduction,
    TabProfileTags,
  },
  data() {
    return {
      tabPosition: "left",
    };
  },
};
</script>

<style></style>
