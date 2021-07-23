<template>
  <el-button type="text" @click="dialogVisible = true" style="color:black"
    >interview 관리</el-button
  >

  <el-dialog
    title="면접관리"
    v-model="dialogVisible"
    width="70%"
    :before-close="handleClose"
  >
    <!--  -->
    <el-tabs :tab-position="tabPosition" style="height: 100%;">
      <el-tab-pane label="받은요청"><RequestedInterviews /></el-tab-pane>
      <el-tab-pane label="수락한면접"><MyInterviews /></el-tab-pane>
      <el-tab-pane label="결과대기">Role</el-tab-pane>
      <el-tab-pane label="결과">Task</el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>

<script>
import RequestedInterviews from "./RequestedInterviews.vue";
import MyInterviews from "./MyInterviews.vue";
export default {
  components: {
    RequestedInterviews,
    MyInterviews,
  },
  data() {
    return {
      dialogVisible: false,
      tabPosition: "left",
    };
  },
};
</script>

<style lang=""></style>
