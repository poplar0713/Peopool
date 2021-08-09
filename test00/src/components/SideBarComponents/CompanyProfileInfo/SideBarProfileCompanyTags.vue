<template>
  <div v-if="this.value.length > 0">
    <el-tag
      v-for="item in value"
      :key="item.taglist_index"
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
    <el-select
      v-model="value"
      multiple
      filterable
      remote
      reserve-keyword
      placeholder="Please enter a keyword"
      :remote-method="remoteMethod"
      :loading="loading"
      style="width:100%"
    >
      <el-option
        v-for="item in options"
        :key="item.taglist_index"
        :label="item.taglist_name"
        :value="item.taglist_name"
      >
      </el-option>
    </el-select>
  </div>
</template>

<script>
import axios from "axios";
// 토큰가져오기
const token = localStorage.getItem("token");
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
        console.log("token error");
        console.log(err.response.data.status);
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    return {
      // 불러온 태그들
      options: [],
      // 선택한 태그들
      value: [],
    };
  },
};
</script>

<style></style>
