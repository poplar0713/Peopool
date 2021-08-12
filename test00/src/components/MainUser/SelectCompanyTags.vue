<template>
  <el-collapse v-model="activeNames" @change="handleChange">
    <el-collapse-item title="Select Tags" name="1" style="text-align:center">
      <div>
        <el-row :gutter="20">
          <el-col :span="2"
            ><div class="grid-content bg-purple">
              <el-divider content-position="left">기업규모</el-divider>
              <el-checkbox-group v-model="checksize" :min="0" :max="1">
                <el-checkbox v-for="city in cities" :label="city" :key="city">{{
                  city
                }}</el-checkbox>
              </el-checkbox-group>
            </div></el-col
          >
          <el-col :span="22"
            ><div class="grid-content bg-purple">
              <el-divider content-position="left">태그목록</el-divider>
              <el-checkbox-group v-model="selected_tags" :min="0" :max="4">
                <el-checkbox
                  v-for="tag in company_tags"
                  :label="tag.list_name"
                  :key="tag"
                  >{{ tag.list_name }}</el-checkbox
                >
              </el-checkbox-group>
            </div></el-col
          >
        </el-row>
      </div>
    </el-collapse-item>
  </el-collapse>

  <el-divider>내가고른 태그목록</el-divider>
  <div style="text-align:center">
    <h3>{{ this.checksize[0] }}</h3>
    <span v-for="tag in selected_tags" :key="tag" style="margin:3px">{{
      tag
    }}</span>
  </div>
  <div v-for="tag in selected_tags" :key="tag">
    <el-divider content-position="left">{{ tag }}</el-divider>
    <TagCompanyList :tag="tag" />
  </div>
</template>

<script>
import axios from "axios";
import jwt_decode from "jwt-decode";
import TagCompanyList from "./TagCompanyList.vue";
export default {
  components: { TagCompanyList },
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.user_index = index;
    // 회사전용태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/classlist/", {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.company_tags = res.data;
      })
      .catch((err) => {
        console.log(err.response);
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          console.log("token error");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    const sizes = ["대기업", "중견기업", "중소기업", "강소기업", "스타트업"];
    return {
      user_index: "",
      checksize: [],
      cities: sizes,
      company_tags: [],
      selected_tags: [],
      // activeNames: ["1"],
    };
  },
  methods: {
    handleChange(val) {
      console.log(val);
    },
  },
};
</script>
