<template>
  <el-card class="box-card" style="margin-bottom:25px;" shadow="hover">
    <el-row :gutter="20">
      <el-col :span="8"
        ><div>
          <el-avatar
            shape="square"
            :size="100"
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
            :disabled="disablecard == true"
            @click="intpass(item.int_index), disabled"
            >입사제안</el-button
          >
          <el-button
            type="danger"
            plain
            round
            :disabled="disablecard == true"
            @click="intfail(item.int_index), disabled"
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
      company_index: index,
      disablecard: false,
    };
  },
  props: ["item"],
  methods: {
    intpass(intindex) {
      console.log(intindex);
      axios
        .put(`https://i5d206.p.ssafy.io:8443/int/pass?index=${intindex}`, {
          headers: { Authorization: this.token },
        })
        .then(() => {
          this.disablecard = true;
        });
    },
    intfail(intindex) {
      console.log(intindex);
      axios
        .put(`https://i5d206.p.ssafy.io:8443/int/fail?index=${intindex}`, {
          headers: { Authorization: this.token },
        })
        .then(() => {
          this.disablecard = true;
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
