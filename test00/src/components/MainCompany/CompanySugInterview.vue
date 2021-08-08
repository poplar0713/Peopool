<template>
  <el-table
    :data="
      InterviewSug.filter(
        (data) =>
          !search ||
          data.name.toLowerCase().includes(search.toLowerCase()) ||
          data.sug_duty.toLowerCase().includes(search.toLowerCase())
      )
    "
    style="width: 100%"
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
    <el-table-column align="center" label="피풀인" prop="name" width="100%">
    </el-table-column>
    <el-table-column align="center" label="직무" prop="sug_duty" width="100%">
    </el-table-column>
    <el-table-column align="center">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button v-if="scope.row.sug_state == 'W'" size="mini"
          >응답대기</el-button
        >
        <el-button v-if="scope.row.sug_state == 'T'" disabled size="mini"
          >면접수락</el-button
        >
        <el-button
          v-if="scope.row.sug_state !== 'C'"
          size="mini"
          type="danger"
          @click="CancelInt(scope.row.sug_index)"
          >요청취소</el-button
        >
        <el-button v-if="scope.row.sug_state == 'C'" disabled size="mini"
          >취소된 요청입니다</el-button
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
      .get(`https://i5d206.p.ssafy.io:8443/sug/ent/${index}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res);
        this.InterviewSug = res.data;
      })
      .catch((err) => {
        console.log("token error");
        console.log(err.response.data.status);
        if (err.response.data.status == 401) {
          alert("로그인세션이이 만료 되었습니다.");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    return {
      InterviewSug: [],
      search: "",
    };
  },
  methods: {
    CancelInt(sugindex) {
      console.log(sugindex);
      axios
        .put(
          "https://i5d206.p.ssafy.io:8443/sug/cancel",
          { headers: { Authorization: this.token } },
          {
            params: {
              index: sugindex,
            },
          }
        )
        .then((res) => {
          console.log(res);
          this.$message({
            message: "면접요청이 취소되었습니다",
            type: "danger",
          });
        })
        .catch((err) => {
          console.log("token error");
          console.log(err.response.data.status);
          if (err.response.data.status == 401) {
            alert("로그인세션이이 만료 되었습니다.");
            localStorage.clear();
            this.$router.push("/");
          }
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
