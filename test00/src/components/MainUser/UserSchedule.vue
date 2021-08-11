<template>
  <el-table
    :data="
      myinterview.filter(
        (data) =>
          !search || data.name.toLowerCase().includes(search.toLowerCase())
      )
    "
    :default-sort="{ prop: 'int_start', order: 'ascending' }"
    height="200"
  >
    <el-table-column align="center" label="Date" prop="int_start" sortable>
    </el-table-column>
    <el-table-column align="center" label="Company" prop="name">
    </el-table-column>
    <el-table-column align="center">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button
          v-if="scope.row.int_end !== 'null'"
          size="mini"
          type="danger"
          @click="GoToInteriewRoom(scope.row.name, scope.row.int_roomnumber)"
          >Interview Room</el-button
        >

        <el-button v-else size="mini" disabled
          >인터뷰가 종료되었습니다</el-button
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
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 면접일정조회
    axios
      .get(`https://i5d206.p.ssafy.io:8443/int/${index}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res);
        this.myinterview = res.data;
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
    return {
      user: "김백수",
      myinterview: [],
      search: "",
    };
  },
  methods: {
    GoToInteriewRoom(company, url) {
      console.log(company, url);
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
        // this.$router.push(`interviewroom/${company}/${user}`);
        this.$router.push({
          name: "InterviewRoom",
          params: { company: company, url: url },
        });
        // user/interviewroom으로 넘어감
      }, 1000);
    },
  },
};
</script>

<style></style>
