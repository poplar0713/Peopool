<template>
  <div v-if="this.mytags.length > 0">
    <el-tag
      v-for="item in mytags"
      :key="item.taglist_index"
      :type="warning"
      effect="plain"
    >
      {{ item.taglist_name }}
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
        :value="item.taglist_index"
      >
        <!--  -->
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
import jwt_decode from "jwt-decode";

export default {
  mounted() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.user_index = index;

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
    axios
      .get("https://i5d206.p.ssafy.io:8443/has/tag", {
        headers: { Authorization: token },
        params: {
          index: index,
          type: 0,
        },
      })
      .then((res) => {
        console.log(res);
        this.mytags = res.data;
      })
      .catch((err) => {
        if (err.response.data.status == 401) {
          console.log("token error");
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
  data() {
    return {
      user_index: "",
      // 불러온 태그들
      options: [],
      // 선택한 태그들
      value: "",
      //나의 태그들
      mytags: [],
    };
  },
  methods: {
    plustag() {
      // 유저본인 태그추가
      console.log(this.value);
      console.log(this.user_index);
      axios
        .post("https://i5d206.p.ssafy.io:8443/has", {
          headers: { Authorization: this.token },

          tag_target: this.user_index,
          tag_type: 0,
          taglist_index: this.value,
        })
        .then((res) => {
          console.log(res);
        })
        .catch((err) => {
          console.log(err.response.data.status);
          if (err.response.data.status == 401) {
            console.log("token error");
            this.$message.error("로그인세션이 만료되었습니다");
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
  },
};
</script>

<style></style>
