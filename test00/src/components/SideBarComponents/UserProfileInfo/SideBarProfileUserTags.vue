<template>
  <div class="select-section" style="width:70%">
    <div>
      <el-divider content-position="left">직무</el-divider>
      <div style="margin-bottom:10px">나의 직무 ({{ this.mypart }})</div>
      <el-select
        v-model="mypart_ind"
        filterable
        placeholder=""
        style="align-text:center"
      >
        <el-option
          v-for="item in this.partlist"
          :key="item.cat_index"
          :label="item.cat_name"
          :value="item.cat_index"
        >
        </el-option>
      </el-select>
      <el-button style="margin-left:10px" @click="modifypart" round
        >수정</el-button
      >
    </div>
    <div>
      <el-divider content-position="left">경력</el-divider>
      <div style="margin-bottom:10px">나의 경력 ({{ this.mycareer }})</div>
      <el-select
        v-model="mycareer_ind"
        filterable
        placeholder=""
        style="align-text:center"
      >
        <el-option
          v-for="item in this.careerlist"
          :key="item.car_index"
          :label="item.car_value"
          :value="item.car_index"
        >
        </el-option>
      </el-select>
      <el-button style="margin-left:10px" @click="modifycareer" round
        >수정</el-button
      >
    </div>
    <div></div>
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
          v-for="item in options_user"
          :key="item.taglist_index"
          :label="item.taglist_name"
          :value="item.taglist_index"
        >
        </el-option>
      </el-select>

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
        style="margin: 0.2rem"
        :key="item.tag_index"
        :type="warning"
        effect="plain"
        closable
        :disable-transitions="true"
        @close="handleClose(tag, item.tag_index)"
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

var token = "";
var decoded = "";
var index = "";

export default {
  name: "SideBarProfileUserTags",
  mounted() {
    // 토큰가져오기
    token = this.$cookies.get("PID_AUTH");
    decoded = jwt_decode(token);
    index = decoded.index;
    this.user_index = index;

    //자신의 직무, 경력을 불러온다
    axios
      .get("https://i5d206.p.ssafy.io:8443/poi/cap", {
        headers: { Authorization: token },
        params: {
          index: index,
        },
      })
      .then((res) => {
        console.log(res.data);
        this.mypart_ind = res.data.cat_index;
        this.mycareer_ind = res.data.car_index;
        this.mypart = res.data.cat_name;
        this.mycareer = res.data.car_value;
      })
      .catch((err) => {
        console.log(err.response);
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
          localStorage.clear();
          this.$router.push("/");
        }
      });

    //커리어 목록 받아오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/career/", {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.careerlist = res.data;
      })
      .catch((err) => {
        console.log(err.response);
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
          localStorage.clear();
          this.$router.push("/");
        }
      });

    //직무 목록 받아오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/taglist/cat", {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.partlist = res.data;
      })
      .catch((err) => {
        console.log(err.response);
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
          localStorage.clear();
          this.$router.push("/");
        }
      });

    // 유저전용태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/taglist/", {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.options_user = res.data;
      })
      .catch((err) => {
        console.log(err.response);
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
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
        this.mytags = res.data;
      })
      .catch((err) => {
        if (err.response == 401) {
          this.$cookies.remove("PID_AUTH");
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
      options_user: [],
      // 선택한 태그들
      value: "",
      //나의 태그들
      mytags: [],
      getNewArray: false,
      getNewInfo: false,
      mypart: "",
      mycareer: "",
      mypart_ind: "",
      mycareer_ind: "",
      careerlist: [],
      partlist: [],
    };
  },
  watch: {
    getNewArray() {
      axios
        .get("https://i5d206.p.ssafy.io:8443/has/tag", {
          headers: { Authorization: token },
          params: {
            index: index,
            type: 0,
          },
        })
        .then((res) => {
          this.mytags = res.data;
        })
        .catch((err) => {
          if (err.response == 401) {
            this.$cookies.remove("PID_AUTH");
            this.$message.error("로그인세션이 만료되었습니다");
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
    getNewInfo() {
      axios
        .get("https://i5d206.p.ssafy.io:8443/poi/cap", {
          headers: { Authorization: token },
          params: {
            index: index,
          },
        })
        .then((res) => {
          this.mypart_ind = res.data.cat_index;
          this.mycareer_ind = res.data.car_index;
          this.mypart = res.data.cat_name;
          this.mycareer = res.data.car_value;
        })
        .catch((err) => {
          console.log(err.response);
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
    modifypart() {
      axios
        .put("https://i5d206.p.ssafy.io:8443/poi/mpart", {
          headers: { Authorization: token },
          ind_index: index,
          cat_index: this.mypart_ind,
        })
        .then(() => {
          this.$message.info("직무가 변경 되었습니다");
          this.getNewInfo = !this.getNewInfo;
        })
        .catch((err) => {
          console.log(err);
          if (err.response == 401) {
            this.$cookies.remove("PID_AUTH");
            this.$message.error("로그인세션이 만료되었습니다");
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
    modifycareer() {
      axios
        .put("https://i5d206.p.ssafy.io:8443/poi/mcar", {
          headers: { Authorization: token },
          ind_index: index,
          cat_index: this.mycareer_ind,
        })
        .then(() => {
          this.$message.info("경력사항이 변경 되었습니다");
          this.getNewInfo = !this.getNewInfo;
        })
        .catch((err) => {
          console.log(err);
          if (err.response == 401) {
            this.$cookies.remove("PID_AUTH");
            this.$message.error("로그인세션이 만료되었습니다");
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },

    plustag() {
      // 유저본인 태그추가
      console.log(this.value);
      console.log(this.user_index);
      const mytaglist = [];
      for (var i in this.mytags) {
        mytaglist.push(this.mytags[i].taglist_index);
      }
      console.log(mytaglist);
      if (mytaglist.includes(this.value)) {
        this.$message.error("이미 추가된 태그입니다.");
      } else {
        axios
          .post("https://i5d206.p.ssafy.io:8443/has", {
            headers: { Authorization: this.$store.state.usertoken },
            tag_target: this.user_index,
            tag_type: 0,
            taglist_index: this.value,
          })
          .then(() => {
            this.getNewArray = !this.getNewArray;
          })
          .catch((err) => {
            console.log(err);
            if (err.response == 401) {
              this.$cookies.remove("PID_AUTH");
              this.$message.error("로그인세션이 만료되었습니다");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }
    },
    handleClose(tag, tag_index) {
      console.log(tag);
      console.log(tag_index);
      axios
        .delete(`https://i5d206.p.ssafy.io:8443/has/${tag_index}`, {
          headers: { Authorization: this.$store.state.usertoken },
        })
        .then(() => {
          console.log();
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
el-divider {
  font-size: 1rem;
}
.select-section {
  padding: 1%;
  margin: 1%;
}
</style>
