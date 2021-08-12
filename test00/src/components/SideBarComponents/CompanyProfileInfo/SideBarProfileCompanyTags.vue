<template>
  <div style="align-text:center">
    <!-- select -->
    <!-- 테스트 -->
    <el-select v-model="value" filterable placeholder="Choose tags" style="align-text:center">
      <el-option
        v-for="item in options"
        :key="item.list_index"
        :label="item.list_name"
        :value="item.list_index"
      >
      </el-option>
    </el-select>
    <!--  -->
    <el-button icon="el-icon-plus" circle @click="plustag" style="margin: 1em;"></el-button>
  </div>
  <div v-if="this.mytags.length > 0" style="width:100%; word-break:break-all;word-wrap:break-word;">
    <el-tag
      v-for="item in mytags"
      style="margin:5px"
      :key="item.taglist_index"
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
</template>

<script>
import axios from "axios";
import jwt_decode from "jwt-decode";

var token;
var decoded;
var index;

export default {
  mounted() {
    // 토큰가져오기
    token = this.$cookies.get("PID_AUTH");
    decoded = jwt_decode(token);
    index = decoded.index;
    this.company_index = index;

    // 태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/classlist/", {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res.data);
        this.options = res.data;
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
    // 기업본인 태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/cla/list", {
        headers: { Authorization: token },
        params: {
          index: index,
          type: 1,
        },
      })
      .then((res) => {
        console.log(res);
        this.mytags = res.data;
        this.getNewArray = !this.getNewArray;
      })
      .catch((err) => {
        if (err.response == 401) {
          console.log("token error");
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
  data() {
    return {
      company_index: "",
      // 불러온 태그들
      options: [],
      // 선택한 태그들
      value: "",
      //나의 태그들
      mytags: [],
      getNewArray: false,
    };
  },
  watch: {
    getNewArray() {
      axios
        .get("https://i5d206.p.ssafy.io:8443/cla/list", {
          headers: { Authorization: token },
          params: {
            index: index,
            type: 1,
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
    },
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
            tag_target: this.company_index,
            tag_type: 0,
            taglist_index: this.value,
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
          name: "searchuser",
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
        .delete(`https://i5d206.p.ssafy.io:8443/cla/${tag_index}`, {
          headers: { Authorization: this.token },
        })
        .then(() => {
          this.mytags.splice(this.mytags.indexOf(tag), 1);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
