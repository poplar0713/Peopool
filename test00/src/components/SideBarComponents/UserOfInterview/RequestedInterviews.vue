<template>
  <el-table
    :data="
      InterviewReq.filter(
        (data) =>
          !search ||
          data.company_name.toLowerCase().includes(search.toLowerCase())
      )
    "
    style="width: 100%"
    height="250"
  >
    <el-table-column label="Company" prop="name"> </el-table-column>
    <el-table-column label="직무" prop="sug_duty"> </el-table-column>

    <el-table-column>
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button v-if="scope.row.sug_state=='null'" @click="dialogVisible = true" >응답하기</el-button>
        <el-button v-if="scope.row.sug_state" disabled >응답완료</el-button>

        <el-dialog
          title="시간설정"
          v-model="dialogVisible"
          width="30%"
          :before-close="handleClose"
        >
          <div style="text-align:center">{{ scope.row.sug_message }}</div>
          <br />
          <div style="text-align:center">
            <el-button
              size="mini"
              type="primary"
              style="margin:5px"
              @click="
                select(
                  scope.$index,
                  scope.row,
                  scope.row.sug_timeone,
                  scope.row.sug_index
                )
              "
              >{{ scope.row.sug_timeone }}</el-button
            ><br />
            <el-button
              size="mini"
              type="primary"
              style="margin:5px"
              @click="
                select(
                  scope.$index,
                  scope.row,
                  scope.row.sug_timetwo,
                  scope.row.sug_index
                )
              "
              >{{ scope.row.sug_timetwo }}</el-button
            ><br />
            <el-button
              size="mini"
              type="primary"
              style="margin:5px"
              @click="
                select(
                  scope.$index,
                  scope.row,
                  scope.row.sug_timethree,
                  scope.row.sug_index
                )
              "
              >{{ scope.row.sug_timethree }}</el-button
            ><br />
            <el-button
              size="mini"
              type="danger"
              style="margin:5px"
              @click="reject(scope.$index, scope.row)"
              >거절하기</el-button
            >
          </div>
        </el-dialog>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  data() {
    // 토큰으로 유저index 가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 요청받은 면접일정 가져오기
    axios
      .get(`https://i5d206.p.ssafy.io:8443/sug/${index}`)
      .then((res) => {
        console.log(res);
        this.InterviewReq = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    return {
      InterviewReq: [],
      dialogVisible: false,
    };
  },
  methods: {
    select(index, row, decision, sugindex) {
      console.log(index);
      console.log(decision);
      console.log(sugindex);
      axios
        .put("https://i5d206.p.ssafy.io:8443/sug/accept", {
          sug_decision: decision,
          sug_index: sugindex,
        })
        .then((res) => {
          console.log(res);
          this.$message({
            message: "수락되었습니다",
            type: "success",
          });
          this.InterviewReq.splice(this.InterviewReq.indexOf(row), 1);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    reject() {},
  },
};
</script>

<style></style>
