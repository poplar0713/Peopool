<template>
  <el-row :gutter="24">
    <el-col :span="6" v-for="(item, i) in followingents.slice(0, 8)" :key="i">
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
    const token = localStorage.getItem("token");
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
        console.log(err.response.data.status);
        if (err.response.data.status == 401) {
          alert("로그인세션이이 만료 되었습니다.");
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
