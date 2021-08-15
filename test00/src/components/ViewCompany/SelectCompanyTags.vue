<template>
  <el-collapse v-model="activeNames" @change="handleChange">
    <el-collapse-item title="태그선택" name="1" style="text-align:center">
      <el-button style="margin:5px" @click="search()">검색</el-button>
      <div v-if="this.selected_tags.length == 0" style="text-align:center">
        <h3>태그를 선택해주세요</h3>
      </div>
      <div style="text-align:center">
        <h3><span v-for="tag in selected_tags" :key="tag" style="margin:3px"
          >{{ tag.list_name }}</span
        ></h3>
      </div>
      <div>
        <el-row :gutter="20">
          <el-col :span="2"
            ><div class="grid-content bg-purple">
              <el-divider content-position="top">규모</el-divider>
              <el-checkbox-group v-model="selected_tags" :min="0" :max="4">
                <el-checkbox v-for="tag in sizes" :label="tag" :key="tag">{{
                  tag.list_name
                }}</el-checkbox>
              </el-checkbox-group>
            </div></el-col
          >
          <el-col :span="22"
            ><div class="grid-content bg-purple">
              <el-divider content-position="left">태그목록</el-divider>
              <el-tabs :tab-position="tabPosition" style="height: 200px;">
                <el-tab-pane label="tag1"
                  ><el-checkbox-group v-model="selected_tags" :min="0" :max="4">
                    <el-checkbox
                      v-for="tag in tag1"
                      :label="tag"
                      :value="tag"
                      :key="tag"
                      >{{ tag.list_name }}</el-checkbox
                    >
                  </el-checkbox-group></el-tab-pane
                >
                <el-tab-pane label="tag2"
                  ><el-checkbox-group v-model="selected_tags" :min="0" :max="4">
                    <el-checkbox v-for="tag in tag2" :label="tag" :key="tag">{{
                      tag.list_name
                    }}</el-checkbox>
                  </el-checkbox-group></el-tab-pane
                >
                <el-tab-pane label="tag3"
                  ><el-checkbox-group v-model="selected_tags" :min="0" :max="4">
                    <el-checkbox v-for="tag in tag3" :label="tag" :key="tag">{{
                      tag.list_name
                    }}</el-checkbox>
                  </el-checkbox-group></el-tab-pane
                >
                <el-tab-pane label="tag4"
                  ><el-checkbox-group v-model="selected_tags" :min="0" :max="4">
                    <el-checkbox v-for="tag in tag4" :label="tag" :key="tag">{{
                      tag.list_name
                    }}</el-checkbox>
                  </el-checkbox-group></el-tab-pane
                >
                <el-tab-pane label="tag5"
                  ><el-checkbox-group v-model="selected_tags" :min="0" :max="4">
                    <el-checkbox v-for="tag in tag5" :label="tag" :key="tag">{{
                      tag.list_name
                    }}</el-checkbox>
                  </el-checkbox-group></el-tab-pane
                >
              </el-tabs>
            </div></el-col
          >
        </el-row>
      </div>
    </el-collapse-item>
  </el-collapse>
  <br />
  <div v-show="this.searchresult == true">
    <el-row :gutter="24">
      <el-col :span="4" v-for="(item, i) in selected_list" :key="i">
        <CompanyCardInfo :item="item.ent_index" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import axios from "axios";
import jwt_decode from "jwt-decode";
import CompanyCardInfo from "./CompanyCardInfo.vue";
export default {
  name: "SelectCompanyTags",
  components: { CompanyCardInfo },
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
        console.log(res.data);
        this.sizes = res.data.slice(0, 4);
        this.tag1 = res.data.slice(4, 7);
        this.tag2 = res.data.slice(7, 10);
        this.tag3 = res.data.slice(10, 13);
        this.tag4 = res.data.slice(13, 16);
        this.tag5 = res.data.slice(16);
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
    // 교집합정보 가져오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/cla/case", {
        headers: { Authorization: token },
        params: {
          list: this.selected_tags,
        },
      })
      .then((res) => {
        this.matchinglist = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    return {
      searchresult: false,
      tabPosition: "left",
      user_index: "",
      sizes: [],
      checksize: [],
      tag1: [],
      tag2: [],
      tag3: [],
      tag4: [],
      tag5: [],
      selected_tags: [],
      selected: [],
      selected_list: [],
      // activeNames: ["1"],
    };
  },
  methods: {
    handleChange(val) {
      console.log(val);
    },
    gototagcompany(tag) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      this.$router.push({
        name: "SearchCompany",
        query: { keyword: tag },
      });
      setTimeout(() => {
        loading.close();
      }, 1000);
    },
    search() {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      var tagindexlist = [];
      for (var tagindex of this.selected_tags) {
        tagindexlist.push(tagindex.list_index);
      }
      this.selected = tagindexlist;
      // 교집합불러오기

      const indexlist = [];
      for (var ind of this.selected) {
        indexlist.push(ind);
      }
      console.log(indexlist);
      const qs = require("qs");
      axios
        .get("https://i5d206.p.ssafy.io:8443/cla/case", {
          headers: { Authorization: this.token },
          params: {
            list: indexlist,
          },
          paramsSerializer: (params) => {
            return qs.stringify(params, { arrayFormat: "repeat" });
          },
        })
        .then((res) => {
          console.log(res.data);
          this.selected_list = res.data;
        })
        .catch((err) => {
          console.log(err);
        });
      setTimeout(() => {
        loading.close();
        this.searchresult = true;
      }, 1000);
    },
  },
  reset() {
    this.selected_tags = [];
  },
};
</script>
