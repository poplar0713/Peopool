<template>
  <div v-if="this.mytags.length > 0">
    <el-tag
      v-for="item in mytags"
      :key="item.taglist_name"
      :type="warning"
      effect="plain"
    >
      {{ item }}
    </el-tag>
  </div>
  <div v-else>
    선택된 태그가 없습니다
  </div>
  <div>
    <el-select v-model="value" clearable placeholder="Select">
      <el-option
        v-for="item in options"
        :key="item.taglist_index"
        :label="item.taglist_name"
        :value="item.taglist_name"
      >
      </el-option>
    </el-select>
    <i
      class="far fa-plus-square fa-2x"
      @click="plustag"
      style="cursor:pointer; margin: 10px"
    ></i>
  </div>
</template>

<script>
import axios from "axios";
// import jwt_decode from "jwt-decode";
// 토큰가져오기
const token = localStorage.getItem("token");
// const decoded = jwt_decode(token);
// const index = decoded.index;
export default {
  data() {
    // 태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/taglist/", {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.options = res.data;
      })
      .catch((err) => {
        console.log(err.response.data.status);
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          console.log("token error");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    // 유저본인 태그목록 불러오기
    // axios
    //   .get("https://i5d206.p.ssafy.io:8443/has/tag", {
    //     headers: { Authorization: token },
    //     params: {
    //       index: index,
    //     },
    //   })
    //   .then((res) => {
    //     console.log(res);
    //     this.mytags = res.data;
    //   })
    //   .catch((err) => {
    //     console.log(err.response.data.status);
    //     if (err.response.data.status == 401) {
    //       console.log("token error");
    //       this.$message.error("로그인세션이 만료되었습니다");
    //       localStorage.clear();
    //       this.$router.push("/");
    //     }
    //   });
    return {
      // 불러온 태그들
      options: [],
      // 선택한 태그들
      value: "",
      //나의 태그들
      mytags: [],
    };
  },
  methods:{
    plustag(){
      
    }
  }
};
</script>

<style></style>
