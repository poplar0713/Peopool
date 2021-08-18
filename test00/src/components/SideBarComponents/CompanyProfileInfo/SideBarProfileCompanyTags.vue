<template>
  <div>
    <h4>1. 회사 분류 설정</h4>
    <div v-if="this.entsize_value == ''"><h5>&nbsp;&nbsp;&nbsp;선택된 분류 없음</h5></div>
    <div v-else>
      <h5>&nbsp;&nbsp;&nbsp;현재 분류 : {{ this.entsize_value }}</h5>
    </div>
    <el-select v-model="this.entsize_index">
      <el-option
        v-for="item in this.size_company"
        :key="item.list_index"
        :label="item.list_name"
        :value="item.list_index"
        >{{ item.list_name }}
      </el-option>
    </el-select>
    <el-button @click="modifyEntsize">수정</el-button>
    <el-divider />
    <h4>2. 기업 분야 태그 설정</h4>
    <el-select v-model="value" filterable placeholder="Choose tags" style="align-text:center">
      <el-option
        v-for="item in this.options_company"
        :key="item.list_index"
        :label="item.list_name"
        :value="item.list_index"
      >
      </el-option>
    </el-select>
    <!--  -->
    <el-button icon="el-icon-plus" circle @click="plustag" style="margin: 1em;"></el-button>
  </div>
  <div
    v-if="this.resulttags.length > 0"
    style="width:100%; word-break:break-all;word-wrap:break-word;"
  >
    <el-tag
      v-for="item in this.resulttags"
      style="margin:5px"
      :key="item.list_index"
      :type="warning"
      effect="plain"
      closable
      :disable-transitions="true"
      @close="handleClose(item.cla_index)"
    >
      {{ item.list_name }}
    </el-tag>
  </div>
  <div v-else style="align-text:center">
    선택된 태그가 없습니다
  </div>
</template>

<script>
import axios from "axios";
import jwt_decode from "jwt-decode";

var token = "";
var decoded = "";
var index = "";

export default {
  name: "SideBarProfileCompanyTags",
  mounted() {
    // 토큰가져오기
    token = this.$cookies.get("PID_AUTH");
    decoded = jwt_decode(token);
    index = decoded.index;
    this.company_index = index;

    // 회사전용태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/classlist/", {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.originList = res.data;
      })
      .catch((err) => {
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    // 회사 본인 태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/cla/list", {
        headers: { Authorization: token },
        params: {
          ent_index: index,
        },
      })
      .then((res) => {
        console.log(res);
        this.mytags = res.data;
      })
      .catch((err) => {
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    // 기업전용태그목록 불러오기

    this.resulttags = [];
    for (var m = 0; m < this.mytags.length; m++) {
      if (this.mytags[m].list_index < 4) {
        this.entsize_index = this.mytags[m].list_index;
        this.entsize_value = this.mytags[m].list_name;
        this.temp_index = this.mytags[m].cla_index;
      } else {
        this.resulttags.push(this.mytags[m]);
      }
    }
    console.log(this.resulttags);
  },
  data() {
    return {
      company_index: "",
      temp_index: "",
      // 불러온 태그들
      originList: [],
      options_company: [],
      size_company: [],
      // 선택한 태그들
      entsize_index: "",
      value: "",
      //나의 태그들
      mytags: [],
      resulttags: [],
      getNewArray: false,
      entsize_value: "",
    };
  },
  watch: {
    originList() {
      this.size_company = [];
      this.options_company = [];
      for (var i = 0; i < 4; i++) {
        this.size_company.push(this.originList[i]);
      }
      for (var j = 4; j < this.originList.length; j++) {
        this.options_company.push(this.originList[j]);
      }
    },

    mytags() {
      this.resulttags = [];
      for (var m = 0; m < this.mytags.length; m++) {
        if (this.mytags[m].list_index < 4) {
          this.entsize_index = this.mytags[m].list_index;
          this.entsize_value = this.mytags[m].list_name;
          this.temp_index = this.mytags[m].cla_index;
        } else {
          this.resulttags.push(this.mytags[m]);
        }
      }
      console.log(this.resulttags);
    },

    getNewArray() {
      axios
        .get("https://i5d206.p.ssafy.io:8443/cla/list", {
          headers: { Authorization: token },
          params: {
            ent_index: index,
          },
        })
        .then((res) => {
          this.mytags = res.data;
        })
        .catch((err) => {
          if (err.response == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            this.$cookies.remove("PID_AUTH");
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
  },
  methods: {
    modifyEntsize() {
      if (this.temp_index != "") {
        axios
          .delete(`https://i5d206.p.ssafy.io:8443/cla/${this.temp_index}`, {
            headers: { Authorization: this.token },
          })
          .then(() => {})
          .catch((err) => {
            console.log(err);
          });
      }
      axios
        .post("https://i5d206.p.ssafy.io:8443/cla", {
          headers: { Authorization: this.token },
          ent_index: this.company_index,
          //cla_index: this.value,
          list_index: this.entsize_index,
        })
        .then(() => {
          this.$message.info("기업 정보가 변경되었습니다.");
          this.getNewArray = !this.getNewArray;
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
    },
    plustag() {
      // 기업 태그추가
      const mytaglist = [];
      for (var i in this.mytags) {
        mytaglist.push(this.mytags[i].list_index);
      }
      if (mytaglist.includes(this.value)) {
        this.$message.error("이미 추가된 태그입니다.");
      } else {
        axios
          .post("https://i5d206.p.ssafy.io:8443/cla", {
            headers: { Authorization: this.token },
            ent_index: this.company_index,
            //cla_index: this.value,
            list_index: this.value,
          })
          .then(() => {
            this.$message.info("기업 정보가 변경되었습니다.");
            this.getNewArray = !this.getNewArray;
          })
          .catch((err) => {
            console.log(err);
            if (err.response == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
              this.$cookies.remove("PID_AUTH");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }
    },
    handleClose(tag_index) {
      axios
        .delete(`https://i5d206.p.ssafy.io:8443/cla/${tag_index}`, {
          headers: { Authorization: this.token },
        })
        .then(() => {
          this.$message.info("태그가 삭제되었습니다");
          this.getNewArray = !this.getNewArray;
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
