<template>
  <el-table
    :data="
      myinterview.filter(
        (data) =>
          (!search || data.name.toLowerCase().includes(search.toLowerCase())) &&
          data.int_show == null
      )
    "
    :default-sort="{ prop: 'int_start', order: 'ascending' }"
    height="500"
  >
    <el-table-column
      align="center"
      label="Date"
      prop="int_start"
      sortable
      width="160%"
    >
    </el-table-column>
    <el-table-column align="center" label="피풀인" prop="ind_name" width="100%">
    </el-table-column>
    <el-table-column align="center" label="직무" prop="int_duty" width="120%">
    </el-table-column>
    <el-table-column align="center">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="Type to search" />
      </template>
      <template #default="scope">
        <el-row>
          <el-col :span="12"
            ><div><UserInfo :userindex="scope.row.ind_index" /></div
          ></el-col>
          <el-col :span="12"
            ><div>
              <el-button
                v-if="scope.row.int_end == null"
                size="mini"
                type="danger"
                @click="
                  GoToInteriewRoom(scope.row.ent_name, scope.row.int_roomnumber)
                "
                >Interview Room</el-button
              >
              <el-button size="mini" @click="FinishInterview(scope.row.int_index)">면접종료</el-button>
            </div></el-col
          >
        </el-row>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import UserInfo from "./UserInfo.vue";

export default {
  name: "CompanySchedule",
  components: { UserInfo },
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
        console.log("token error");
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
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
    FinishInterview(interviewindex){
      axios.put("https://i5d206.p.ssafy.io:8443/int/finish", {
        headers: { Authorization: this.token },
        data:{
          int_index:interviewindex
        }

      })
    }
  },
};
</script>

<style></style>
