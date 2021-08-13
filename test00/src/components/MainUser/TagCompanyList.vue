<template>
  <div v-if="this.ents !== null">
    <el-row :gutter="24">
      <el-col :span="4" v-for="(item, i) in ents.slice(0, 6)" :key="i">
        <TagCompanyCard :item="item.ent_index" />
      </el-col>
    </el-row>
  </div>
  <div v-if="ents.length == 0" style="text-align:center">
    태그에 해당하는 기업정보가 없습니다
  </div>
</template>

<script>
import TagCompanyCard from "./TagCompanyCard.vue";
// import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  name:"TagCompanyList",
  components: {
    TagCompanyCard,
  },
  props: {
    tag: Object,
  },
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    axios
      .get("https://i5d206.p.ssafy.io:8443/cla/ent", {
        headers: { Authorization: token },
        params: {
          name: this.tag,
        },
      })
      .then((res) => {
        console.log(res);
        this.ents = res.data;
      })
      .catch((err) => {
        console.log(err.response);
        if (err.response == 401) {
          console.log("token error");
          this.$message.error("로그인세션이 만료되었습니다");
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
