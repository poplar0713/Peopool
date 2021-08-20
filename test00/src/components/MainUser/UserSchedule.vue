<template>
  <el-table
    style="border-radius: 2em;"
    :data="
      myinterview.filter(
        (data) =>
          (!search || data.name.toLowerCase().includes(search.toLowerCase())) &&
          data.int_show == 'W'
      )
    "
    :default-sort="{ prop: 'int_start', order: 'ascending' }"
    height="500"
  >
    <el-table-column align="center" label="날짜" prop="int_start" sortable width="160%">
    </el-table-column>
    <el-table-column align="center" label="기업명" prop="ent_name"> </el-table-column>
    <el-table-column align="center" label="면접장" prop="int_duty">
      <template #default="scope">
        <el-button
          v-if="scope.row.int_show == 'W'"
          size="mini"
          type="danger"
          @click="GoToInteriewRoom(scope.row.ent_name, scope.row.int_roomnumber)"
          >Interview Room</el-button
        >
        <el-text v-else size="mini" disabled>인터뷰가 종료되었습니다</el-text>
      </template>
    </el-table-column>
    <el-table-column align="center">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="검색어를 입력해주세요" />
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
  name: "UserSchedule",
  components: { CompanyInfoDetail },
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
        
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
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
