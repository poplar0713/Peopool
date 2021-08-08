<template>
  <el-row :gutter="24">
    <el-col :span="6" v-for="(item, i) in ents.slice(0, 8)" :key="i">
      <CompanyCard :item="item" />
    </el-col>
  </el-row>
</template>

<script>
import CompanyCard from "@/components/search/CompanyCard.vue";
// import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  components: {
    CompanyCard,
  },
  data() {
    // 토큰가져오기
    // const token = localStorage.getItem("token");
    // const decoded = jwt_decode(token);
    // const index = decoded.index;
    // 기업 전체프로필 가져오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/poe", {
        headers: { Authorization: this.token },
      })
      .then((res) => {
        console.log(res);
        this.ents = res.data;
      })
      .catch((err) => {
        console.log("token error");
        console.log(err.response.data.status);
        if (err.response.data.status == 401) {
          this.$message.error('로그인세션이 만료되었습니다');
          localStorage.clear();
          this.$router.push("/");
        }
      });
    return {
      ents: [],
    };
  },
};
</script>

<style></style>
