<template>
  <el-dialog v-model="InterviewDialogVisible">
    <el-container>
      <el-main>
        <el-input type="textarea" :rows="10" placeholder="Please input" v-model="memo"> </el-input
      ></el-main>
      <el-footer>
        <el-button @click="clickSaveBtn" type="info">저장</el-button>
      </el-footer>
    </el-container>
  </el-dialog>
  <el-space class="examineCard">
    <el-container>
      <el-aside>
        <el-image :src="p_img" :fit="fill"></el-image>
      </el-aside>
      <el-container>
        <el-main
          ><h5>면접일시 : {{ interviewTime }}</h5>
          <h4>{{ p_name }} ({{ part }})</h4>
          <el-button-group>
            <el-button @click="openUserprofile">프로필</el-button>
            <el-button type="warning" @click="openInterviewMemo(p_ind)">면접 메모</el-button>
          </el-button-group>
        </el-main>
        <el-footer>
          <el-space wrap :size="large">
            <el-button type="success">입사 제안</el-button>
            <el-button type="danger">탈락</el-button>
          </el-space>
        </el-footer>
      </el-container>
    </el-container>
  </el-space>
</template>

<style>
.examineCard {
  padding: 1%;
  border-width: 3px;
  border-radius: 10px 10px 10px 10px;
  border-color: #ffc000;
  border-style: solid;
  margin: 1%;
  width: 39%;
  box-shadow: 5px 5px 5px 5px wheat;
}
</style>

<script>
import webviewer from "../MainCompany/webviewer.vue";
export default {
  component: {
    webviewer,
  },
  data() {
    return {
      InterviewDialogVisible: false,
      memo: "",
    };
  },
  props: ["p_ind", "p_name", "app_url", "part", "p_img", "interviewTime"],
  methods: {
    openUserprofile() {},
    openViewer(app_url) {
      console.log(app_url);
    },
    openInterviewMemo() {
      console.log(this.p_ind);
      this.InterviewDialogVisible = true;
    },
    closeInterviewMemo() {
      this.InterviewDialogVisible = false;
    },
    clickSaveBtn() {
      console.log(this.memo);
      alert("저장되었습니다");
    },
  },
};
</script>
