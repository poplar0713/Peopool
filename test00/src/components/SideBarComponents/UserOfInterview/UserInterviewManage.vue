<template>
  <el-button type="text" @click="dialogVisible = true" style="color:black"
    >면접관리</el-button
  >

  <el-dialog
    title="면접관리"
    v-model="dialogVisible"
    width="50%"
    :before-close="handleClose"
  >
    <!--  -->
    <el-tabs :tab-position="tabPosition" style="height: 100%;">
      <el-tab-pane label="받은요청"><UserSugInterview /></el-tab-pane>
      <el-tab-pane label="수락한면접"><UserSchedule /></el-tab-pane>
      <el-tab-pane label="결과대기"><UserWaitingResult /></el-tab-pane>
      <el-tab-pane label="결과"><ResultInterview/></el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>

<script>
import UserSchedule from "@/components/MainUser/UserSchedule.vue";
import UserSugInterview from "@/components/MainUser/UserSugInterview.vue";
import UserWaitingResult from "@/components/MainUser/UserWaitingResult.vue";
import ResultInterview from "@/components/MainUser/ResultInterview.vue";
export default {
  name: "UserInterviewManage",
  components: {
    UserSugInterview,
    UserSchedule,
    UserWaitingResult,
    ResultInterview
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
