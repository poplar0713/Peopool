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
    <!-- <el-table-column label="choice_2" prop="date"> </el-table-column>
    <el-table-column label="choice_2" prop="date"> </el-table-column> -->

    <el-table-column>
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button
          size="mini"
          type="primary"
          @click="
            select(
              scope.$index,
              scope.row,
              scope.row.sug_timeone,
              scope.row.sug_index
            )
          "
          >{{ scope.row.sug_timeone }}</el-button
        >
        <el-button
          size="mini"
          type="primary"
          @click="
            select(
              scope.$index,
              scope.row,
              scope.row.sug_timetwo,
              scope.row.sug_index
            )
          "
          >{{ scope.row.sug_timetwo }}</el-button
        >
        <el-button
          size="mini"
          type="primary"
          @click="
            select(
              scope.$index,
              scope.row,
              scope.row.sug_timethree,
              scope.row.sug_index
            )
          "
          >{{ scope.row.sug_timethree }}</el-button
        >
        <el-button
          size="mini"
          type="danger"
          @click="reject(scope.$index, scope.row)"
          >거절하기</el-button
        >
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
