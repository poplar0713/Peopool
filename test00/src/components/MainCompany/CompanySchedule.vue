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
    <el-table-column align="center" label="Date" prop="int_start" sortable>
    </el-table-column>
    <el-table-column align="center" label="피풀인" prop="ind_name">
    </el-table-column>
    <el-table-column align="center" label="면접장" prop="int_duty">
      <template #default="scope">
        <el-button
          v-if="scope.row.int_show == 'W'"
          size="mini"
          type="danger"
          @click="
            GoToInteriewRoom(scope.row.ent_name, scope.row.int_roomnumber)
          "
          >Interview Room</el-button
        >
      </template>
    </el-table-column>
    <el-table-column #default="scope" align="center" label="종료" prop="int_duty">
      <el-popover placement="left" :width="200" trigger="click">
        <template #reference>
          <el-button type="success" plain size="mini">면접종료</el-button>
        </template>
        <div style="text-align:center">
          <div style="margin-bottom:10px">
            <el-button
              size="mini"
              type="success"
              plain
              round
              @click="FinishInterview(scope.row, scope.row.int_index)"
              >면접종료</el-button
            >
          </div>
          <div>
            <el-button
              type="danger"
              plain
              round
              size="mini"
              @click="Noshow(scope.row, scope.row.int_index)"
              >노쇼로 인한 면접종료</el-button
            >
          </div>
        </div>
      </el-popover>
    </el-table-column>
    <el-table-column align="center">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <UserInfoDetailNoSug :userindex="scope.row.ind_index" />
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import UserInfoDetailNoSug from "@/components/UserInfo/UserInfoDetailNoSug.vue";

export default {
  name: "CompanySchedule",
  components: { UserInfoDetailNoSug },
  data() {
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 면접일정조회
    axios
      .get(`https://i5d206.p.ssafy.io:8443/int/ent/${index}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res);
        this.myinterview = res.data;
      })
      .catch((err) => {
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
          localStorage.clear();
          this.$router.push("/");
        }
      });
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
    FinishInterview(row, interviewindex) {
      axios
        .put("https://i5d206.p.ssafy.io:8443/int/finish", {
          headers: { Authorization: this.token },
          int_index: interviewindex,
        })
        .then(() => {
          this.$message({
            message: "면접이 정상적으로 종료되었습니다",
            type: "success",
          });
          this.myinterview.splice(this.myinterview.indexOf(row), 1);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    Noshow(row, interviewindex) {
      axios
        .put("https://i5d206.p.ssafy.io:8443/int/show", {
          headers: { Authorization: this.token },
          int_index: interviewindex,
        })
        .then(() => {
          this.$message({
            message: "지원자의 노쇼행위로 인해 면접이 종료되었습니다",
            type: "success",
          });
          this.myinterview.splice(this.myinterview.indexOf(row), 1);
        });
    },
  },
};
</script>

<style></style>
