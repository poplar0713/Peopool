<template>
  <el-table
    :data="
      pastinterview.filter(
        (data) =>
          (!search || data.name.toLowerCase().includes(search.toLowerCase())) &&
          data.int_done == 'P'
      )
    "
    height="600"
  >
    <el-table-column
      align="center"
      label="면접종료일"
      prop="int_end"
      width="160%"
    >
    </el-table-column>
    <el-table-column align="center" label="피풀인" prop="ind_name" width="100%">
    </el-table-column>
    <el-table-column align="center" label="결과" prop="int_done" width="100%">
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
  name: "PassUser",
  components: { UserInfoDetail },
  data() {
    // 토큰으로 유저index 가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 예전면접가져오기
    axios
      .get(`https://i5d206.p.ssafy.io:8443/int/ent/last/${index}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res);
        this.pastinterview = res.data;
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
      pastinterview: [],
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
