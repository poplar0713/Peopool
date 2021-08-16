<template>
  <!-- 면접메모 -->
  <el-dialog v-model="InterviewDialogVisible">
    <el-container>
      <el-main>
        <el-input
          type="textarea"
          :rows="10"
          placeholder="Please input"
          v-model="memo"
        >
        </el-input
      ></el-main>
      <el-footer>
        <el-button @click="clickSaveBtn" type="info">저장</el-button>
      </el-footer>
    </el-container>
  </el-dialog>

  <el-card class="box-card" style="margin-bottom:25px;" shadow="hover">
    <!-- v-if="item.int_done == 'W'" -->
    <el-row :gutter="20">
      <el-col :span="8"
        ><div>
          <el-avatar
            shape="square"
            :size="60"
            :src="squareUrl"
          ></el-avatar></div
      ></el-col>
      <el-col :span="16"
        ><div>
          <UserInfo :userindex="item.ind_index" /><br />
          <el-button
            type="success"
            plain
            round
            @click="sugpass(item.int_index), disabled"
            >입사제안</el-button
          >
          <el-button
            type="danger"
            plain
            round
            @click="sugfail(item.int_index), disabled"
            >탈락</el-button
          >
        </div></el-col
      >
    </el-row>
  </el-card>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import UserInfo from "./UserInfo.vue";

export default {
  name: "ExamineCard",
  components: {
    UserInfo,
  },
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    return {
      InterviewDialogVisible: false,
      memo: "",
      company_index: index,
    };
  },
  props: ["item"],
  methods: {
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
    sugpass(intindex) {
      console.log(intindex);
      axios.put(`https://i5d206.p.ssafy.io:8443/int/pass?index=${intindex}`, {
        headers: { Authorization: this.token },
      });
    },
    sugfail(intindex) {
      console.log(intindex);
      axios.put(`https://i5d206.p.ssafy.io:8443/int/fail?index=${intindex}`, {
        headers: { Authorization: this.token },
      });
    },
  },
};
</script>

<style>
.box-card {
  border-width: 3px;
  border-radius: 10px 10px 10px 10px;
  border-color: #fce08c;
  border-style: solid;
  /* box-shadow: 5px 5px 5px 5px wheat; */
}
</style>
