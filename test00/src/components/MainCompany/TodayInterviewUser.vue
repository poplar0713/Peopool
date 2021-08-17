<template>
  <div v-if="this.todayinterviewuser !== null">
    <el-row :gutter="24">
      <el-col :span="4" v-for="(item, i) in todayinterviewuser" :key="i">
        <UserInfoCardNoSug :userindex="item.ind_index" />
      </el-col>
    </el-row>
  </div>
  <div v-if="todayinterviewuser.length == 0" style="text-align:center">
    오늘은 면접자가 없습니다
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import UserInfoCardNoSug from "@/components/UserInfo/UserInfoCardNoSug.vue";

export default {
  name: "TodayInterviewUser",
  components: { UserInfoCardNoSug },
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.company_index = index;
    const today = new Date();
    var year = today.getFullYear();
    var month = 1 + today.getMonth();
    month = month >= 10 ? month : "0" + month;
    var day = today.getDate();
    day = day >= 10 ? day : "0" + day;
    const todaydate = String(year) + "-" + String(month) + "-" + String(day);

    //
    axios
      .get(
        `https://i5d206.p.ssafy.io:8443/int/ent/iday/${this.company_index}`,
        {
          headers: { Authroization: token },
        }
      )
      .then((res) => {
        console.log(todaydate);
        // 받아온 데이터 날짜랑 비교해주기
        console.log(res.data);
        for (var i in res.data) {
          if (todaydate == res.data[i].date) {
            console.log(res.data[i].interviewers);
            this.todayinterviewuser = res.data[i].interviewers;
          }
        }
      })
      .catch((err) => {
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    return {
      todayinterviewuser: [],
      company_index: 0,
    };
  },
};
</script>

<style></style>
