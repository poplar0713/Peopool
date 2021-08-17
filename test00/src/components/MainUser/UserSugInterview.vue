<template>
  <el-table
    style="border-radius: 2em;"
    :data="
      InterviewReq.filter(
        (data) =>
          !search ||
          data.name.toLowerCase().includes(search.toLowerCase()) ||
          data.sug_duty.toLowerCase().includes(search.toLowerCase())
      )
    "
    height="500"
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
      width="58"
    >
    </el-table-column>
    <el-table-column align="center" label="Company" prop="name" width="100%">
    </el-table-column>
    <el-table-column align="center" label="직무" prop="sug_duty" width="100%">
    </el-table-column>
    <el-table-column align="center" label="한마디" prop="sug_message">
    </el-table-column
    ><el-table-column align="center" label="응답상태" prop="sug_message">
      <template #default="scope">
        <el-popover
          placement="top"
          :width="100"
          trigger="click"
          v-if="scope.row.sug_state == 'W'"
        >
          <template #reference>
            <el-button size="mini">응답하기</el-button>
          </template>
          <div style="text-align:center">
            <el-button
              size="mini"
              plain
              round
              style="margin:5px;"
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
              plain
              round
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
              plain
              round
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
              @click="reject(scope.$index, scope.row, scope.row.sug_index)"
              >거절하기</el-button
            >
          </div>
        </el-popover>
        <!--  -->
        <el-text v-if="scope.row.sug_state == 'T'" disabled size="mini"
          >응답완료</el-text
        >
        <el-text v-if="scope.row.sug_state == 'F'" disabled size="mini"
          >응답거절</el-text
        >
        <el-text v-if="scope.row.sug_state == 'C'" disabled size="mini"
          >취소된 요청입니다</el-text
        >
      </template>
    </el-table-column>
    <el-table-column align="center">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <CompanyInfoDetail :companyindex="scope.row.ent_index" />
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import CompanyInfoDetail from "@/components/CompanyInfo/CompanyInfoDetail.vue";

export default {
  name: "UserSugInterview",
  components: { CompanyInfoDetail },
  mounted() {
    // 토큰으로 유저index 가져오기
    const token = this.$cookies.get("PID_AUTH");
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
        console.log(err.response);
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
  data() {
    return {
      InterviewReq: [],
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
          // this.InterviewReq.splice(this.InterviewReq.indexOf(row), 1);
          location.reload();
        })
        .catch((err) => {
          console.log("token error");
          console.log(err.response);
          if (err.response == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
    reject(index, row, sugindex) {
      console.log(index, row);
      axios
        .put(`https://i5d206.p.ssafy.io:8443/sug/reject?index=${sugindex}`, {
          headers: { Authorization: this.token },
        })
        .then(() => {
          this.dialogVisible = false;
          this.$message({
            showClose: true,
            message: "면접요청이 거절되었습니다",
            type: "danger",
          });
          location.reload();
        });
    },
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
  },
};
</script>

<style></style>
