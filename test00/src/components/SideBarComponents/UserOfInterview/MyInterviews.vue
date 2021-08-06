<template>
  <el-table
    :data="
      myinterview.filter(
        (data) =>
          !search || data.company.toLowerCase().includes(search.toLowerCase())
      )
    "
    :default-sort="{ prop: 'date', order: 'ascending' }"
    height="300"
  >
    <el-table-column label="Date" prop="int_start" sortable> </el-table-column>
    <el-table-column label="Company" prop="name"> </el-table-column>
    <el-table-column align="right">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-button
          size="mini"
          @click="Cancel(scope.$index, scope.row, scope.row.int_index)"
          >Cancel</el-button
        >
        <!-- {{scope.row.company}} -->
        <el-button
          size="mini"
          type="danger"
          @click="GoToInteriewRoom(scope.row.ent_index, scope.row.int_url)"
          >Interview Room</el-button
        >
        <!-- {{scope.row.url}} -->
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
      .get(`https://i5d206.p.ssafy.io:8443/int/${index}`)
      .then((res) => {
        console.log(res);
        this.myinterview = res.data;
      })
      .catch((err) => console.log(err));
    return {
      user: "김백수",
      myinterview: [],
      search: "",
    };
  },
  methods: {
    Cancel(index, row, company) {
      console.log(index, row, company);
    },
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
