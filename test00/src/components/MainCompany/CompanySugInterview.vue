<template>
  <el-table
    style="border-radius: 2em;"
    :data="
      InterviewSug.filter(
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
      width="60%"
    >
    </el-table-column>
    <el-table-column align="center" label="요청일" prop="sug_send" width="160%">
    </el-table-column>
    <el-table-column align="center" label="피풀인" prop="name" width="100%">
    </el-table-column>
    <el-table-column align="center" label="직무" prop="sug_duty" width="120%">
    </el-table-column>
    <el-table-column align="center" label="요청상태">
      <template #default="scope">
        <el-text v-if="scope.row.sug_state == 'W'" size="mini"
          >응답대기</el-text
        >
        <el-button
          v-if="scope.row.sug_state == 'W'"
          size="mini"
          type="danger"
          @click="CancelInt(scope.row.sug_index)"
          >요청취소</el-button
        >
        <el-text
          v-if="scope.row.sug_state == 'T'"
          disabled
          size="mini"
          style="margin-right:5px"
          >면접수락</el-text
        >
        <el-text v-if="scope.row.sug_state == 'C'" disabled size="mini"
          >취소된 요청</el-text
        >
        <el-text v-if="scope.row.sug_state == 'F'" disabled size="mini"
          >거절된 요청</el-text
        >
      </template>
    </el-table-column>
    <el-table-column
      align="center"
      label="응답일정"
      prop="sug_decision"
      width="100%"
    >
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
  name: "CompanySugInterview",
  components: { UserInfoDetailNoSug },
  data() {
    // 토큰으로 유저index 가져오기
    const token = this.$cookies.get("PID_AUTH");
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
        console.log("여기서 이미 못받아옴");
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
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
    // 면접취소
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
          if (err.response.data.status == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
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
