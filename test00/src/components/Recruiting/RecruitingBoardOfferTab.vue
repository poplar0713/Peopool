<template>
  <el-table
    :data="
      InterviewSug.filter(
        (data) =>
          (!search ||
            data.name.toLowerCase().includes(search.toLowerCase()) ||
            data.sug_duty.toLowerCase().includes(search.toLowerCase())) &&
          data.sug_state == 'W'
      )
    "
    height="600"
  >
    <el-table-column align="center" label="요청일" prop="sug_send">
    </el-table-column>
    <el-table-column align="center" label="피풀인" prop="name">
    </el-table-column>
    <el-table-column align="center" label="직무" prop="sug_duty">
    </el-table-column>
    <el-table-column
      align="center"
      label="제안1"
      prop="sug_timeone"
      width="170%"
    >
    </el-table-column>
    <el-table-column
      align="center"
      label="제안2"
      prop="sug_timetwo"
      width="170%"
    >
    </el-table-column>
    <el-table-column
      align="center"
      label="제안3"
      prop="sug_timethree"
      width="170%"
    >
    </el-table-column>
    <el-table-column align="center">
      <template #header>
        <el-input v-model="search" size="mini" placeholder="검색어를 입력해주세요" />
      </template>
      <template #default="scope">
        <UserInfoDetail :userindex="scope.row.ind_index" />
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import UserInfoDetail from "@/components/UserInfo/UserInfoDetail.vue";

export default {
  name: "RecruitingBoardOfferTab",
  components: { UserInfoDetail },
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
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
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
          { headers: { Authorization: this.$store.state.usertoken } },
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
          if (err.response.data.status == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            this.$cookies.remove("PID_AUTH");
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
