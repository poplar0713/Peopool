<template>
  <el-table
    :data="
      myinterview.filter(
        (data) =>
          !search || data.name.toLowerCase().includes(search.toLowerCase())
      )
    "
    :default-sort="{ prop: 'int_start', order: 'ascending' }"
    height="500"
  >
    <el-table-column align="center" label="Date" prop="int_start" sortable width="100">
    </el-table-column>
    <el-table-column align="center" label="Company" prop="name">
    </el-table-column>
    <el-table-column align="center">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <CompanyInfo :item="scope.row.ent_index" />
        &nbsp;
        <el-button
          v-if="scope.row.int_end !== 'null'"
          size="mini"
          type="danger"
          @click="GoToInteriewRoom(scope.row.name, scope.row.int_roomnumber)"
          >Interview Room</el-button
        >

        <el-text v-else size="mini" disabled>인터뷰가 종료되었습니다</el-text>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import CompanyInfo from "./CompanyInfo.vue";

export default {
  components: { CompanyInfo },
  mounted() {
    const token = this.$cookies.get("PID_AUTH");
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
  },
  data() {
    return {
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
        this.$router.push({
          name: "InterviewRoom",
          params: { company: company, url: url },
        });
      }, 1000);
    },
  },
};
</script>

<style></style>
