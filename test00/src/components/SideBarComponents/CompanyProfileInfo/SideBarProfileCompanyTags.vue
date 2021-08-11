<template>
  <div class="select-section">
    <el-divider content-position="left">나의 태그</el-divider>
    <div style="align-text:center">
      <!-- select -->
      <el-select
        v-model="value"
        filterable
        placeholder="Choose tags"
        style="align-text:center"
      >
        <el-option
          v-for="item in options_company"
          :key="item.list_index"
          :label="item.list_name"
          :value="item.list_index"
        >
        </el-option>
      </el-select>
      <!--  -->
      <el-button
        icon="el-icon-plus"
        circle
        @click="plustag"
        style="margin: 1em;"
      ></el-button>
    </div>
    <div
      v-if="this.mytags.length > 0"
      style="width:100%; word-break:break-all;word-wrap:break-word;"
    >
      <el-tag
        v-for="item in mytags"
        style="margin:5px"
        :key="item.list_index"
        :type="warning"
        effect="plain"
        closable
        :disable-transitions="true"
        @close="handleClose(tag, item.tag_index)"
        @click="GetTagCompany(item.taglist_name)"
      >
        {{ item.taglist_name }}
      </el-tag>
    </div>
    <div v-else style="align-text:center">
      선택된 태그가 없습니다
    </div>
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
    this.company_index = index;

    // 회사전용태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/classlist/", {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.options_company = res.data;
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
    // 회사 본인 태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/cla/list", {
        headers: { Authorization: token },
        params: {
          index: index,
        },
      })
      .then((res) => {
        console.log(res);
        this.mytags = res.data;
      })
      .catch((err) => {
        if (err.response == 401) {
          console.log("token error");
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    // 본인 태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/has/tag", {
        headers: { Authorization: token },
        params: {
          index: index,
          type: 1,
        },
      })
      .then((res) => {
        console.log(res);
        // this.mytags = res.data;
      })
      .catch((err) => {
        if (err.response == 401) {
          console.log("token error");
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    // 기업전용태그목록 불러오기
  },
  data() {
    return {
      company_index: "",
      // 불러온 태그들
      options_company: [],
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
      console.log(this.company_index);
      const mytaglist = [];
      for (var i in this.mytags) {
        mytaglist.push(this.mytags[i].taglist_index);
      }
      console.log(mytaglist);
      if (mytaglist.includes(this.value)) {
        this.$message.error("이미 추가된 태그입니다.");
      } else {
        axios
          .post("https://i5d206.p.ssafy.io:8443/cla", {
            headers: { Authorization: this.token },
            ent_index: this.company_index,
            list_index: this.value,
          })
          .then((res) => {
            console.log(res);
            this.mytags.push(res.data);
          })
          .catch((err) => {
            console.log(err);
            if (err.response == 401) {
              console.log("token error");
              this.$message.error("로그인세션이 만료되었습니다");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }
    },

    GetTagCompany(keyword) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
        this.$router.push({
          name: "SearchCompany",
          params: { keyword: `${keyword}` },
        });
      }, 2000);
      setTimeout(() => {
        location.reload();
      }, 2001);
    },
    handleClose(tag, tag_index) {
      console.log(tag);
      console.log(tag_index);
      axios
        .delete(`https://i5d206.p.ssafy.io:8443/has/${tag_index}`, {
          headers: { Authorization: this.token },
        })
        .then(() => {
          console.log();
          this.mytags.splice(this.mytags.indexOf(tag), 1);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style>
.el-divider span {
  font-size: 1rem;
}
.select-section {
  padding: 1%;
  margin: 1%;
}
</style>
