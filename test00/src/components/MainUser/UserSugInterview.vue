<template>
  <el-table
    :data="
      InterviewReq.filter(
        (data) =>
          !search ||
          data.name.toLowerCase().includes(search.toLowerCase()) ||
          data.sug_duty.toLowerCase().includes(search.toLowerCase())
      )
    "
    height="200"
    ref="filterTable"
  >
    <el-table-column
      label="상태"
      prop="sug_state"
      :filters="[
        { text: '대기', value: 'W' },
        { text: '확정', value: 'T' },
        { text: '거절', value: 'F' },
        { text: '취소', value: 'C' },
      ]"
      :filter-method="filterHandler"
      width="60%"
    >
    </el-table-column>
    <el-table-column align="center" label="Company" prop="name">
    </el-table-column>
    <el-table-column align="center" label="직무" prop="sug_duty">
    </el-table-column>
    <el-table-column align="center">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button
          v-if="scope.row.sug_state == 'W'"
          @click="dialogVisible = true"
          size="mini"
          >응답하기</el-button
        >
        <el-button v-if="scope.row.sug_state !== 'W'" disabled size="mini"
          >응답완료</el-button
        >

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
          <!-- <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible = false">Cancel</el-button>
              <el-button type="primary" @click="dialogVisible = false"
                >Confirm</el-button
              >
            </span>
          </template> -->
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
      .get(`https://i5d206.p.ssafy.io:8443/sug/${index}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res);
        this.InterviewReq = res.data;
      })
      .catch((err) => {
        console.log("token error");
        console.log(err.response.data.status);
        if (err.response.data.status == 401) {
          this.$message.error('로그인세션이 만료되었습니다');
          localStorage.clear();
          this.$router.push("/");
        }
      });
    return {
      InterviewReq: [],
      dialogVisible: false,
      search: "",
    };
  },
  methods: {
    select(index, row, decision, sugindex) {
      console.log(index);
      console.log(decision);
      console.log(sugindex);
      axios
        .put("https://i5d206.p.ssafy.io:8443/sug/accept", {
          headers: { Authorization: this.token },
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
          console.log("token error");
          console.log(err.response.data.status);
          if (err.response.data.status == 401) {
            this.$message.error('로그인세션이 만료되었습니다');
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
    reject() {},
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
  },
};
</script>

<style></style>
