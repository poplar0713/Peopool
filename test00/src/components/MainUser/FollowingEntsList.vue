<template>
  <el-row :gutter="24">
    <el-col :span="4" v-for="(item, i) in followingents.slice(0, 6)" :key="i">
      <FollowingEntCard :item="item" />
    </el-col>
  </el-row>
</template>

<script>
import FollowingEntCard from "./FollowingEntCard.vue";
import jwt_decode from "jwt-decode";
import axios from "axios";
export default {
  components: {
    FollowingEntCard,
  },
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 팔로잉기업 가져오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/fol/following", {
        params: {
          index: index,
          type: 0,
        },
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res);
        this.followingents = res.data;
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
      followingents: [],
    };
  },
};
</script>

<style></style>
