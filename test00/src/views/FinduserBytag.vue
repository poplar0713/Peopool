<template>
  <!--/-->
  <el-container>
    <el-aside width="200px"><SideBarCompany :usertoken="index"/></el-aside>
    <el-container>
      <el-header><headerSearchUser /></el-header>
      <el-main>
        <h2>인재 찾기</h2>
        <h4>우리 회사에 적합한 인재를 찾아보세요</h4>
        <el-main class="selectArea">
          <el-divider></el-divider>
          <el-row>
            <el-col :span="5">
              <el-select v-model="cat_ind" filterable placeholder="직군 선택">
                <el-option
                  v-for="item in this.categorylist"
                  :key="item.cat_index"
                  :label="item.cat_name"
                  :value="item.cat_index"
                >
                </el-option>
              </el-select>
            </el-col>
            <el-col :span="18">
              <el-slider
                v-model="range"
                range
                show-stops
                :show-tooltip="false"
                :min="1"
                :max="9"
                :marks="marks"
              >
              </el-slider
            ></el-col>
          </el-row>
          <el-divider></el-divider>
          <el-row style="margin: 0.3rem">
            <el-col :span="24">
              <el-checkbox-group
                v-model="this.selected"
                @change="handleCheckedChange"
              >
                <el-checkbox
                  style="margin:5px"
                  v-for="item in this.taglist"
                  :label="item.taglist_index"
                  :key="item.taglist_index"
                  >{{ item.taglist_name }}</el-checkbox
                >
              </el-checkbox-group>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="19"></el-col>
            <el-col :span="3"
              ><el-switch
                style="display: block"
                v-model="this.isUnion"
                active-color="#5F2D9A"
                inactive-color="#FFC000"
                active-text="그리고"
                inactive-text="또는"
                active-value="inter"
                inactive-value="union"
              >
              </el-switch
            ></el-col>
            <el-col :span="2"
              ><el-button
                type="success"
                icon="el-icon-search"
                @click="this.getOriginList"
                >검색</el-button
              >
            </el-col>
          </el-row>
          <el-divider></el-divider>
        </el-main>

        <h3>검색 결과 (상세 정보를 보려면 클릭하세요)</h3>
        <el-row :gutter="24">
          <el-col :span="4" v-for="item in resultList" :key="item">
            <UserInfoCard :userindex="item.ind_index" />
          </el-col>
        </el-row>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBarCompany from "@/components/SideBarComponents/SideBarCompany.vue";
import headerSearchUser from "@/components/SideBarComponents/headerSearchUser.vue";
import UserInfoCard from "@/components/UserInfo/UserInfoCard.vue";
import jwt_decode from "jwt-decode";
import axios from "axios";
//import jwt_decode from "jwt-decode";

const qs = require("qs");
var token;
export default {
  mounted() {
    token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.ent_index = index;
    //    decoded = jwt_decode(token);
    //    index = decoded.index;

    axios
      .get("https://i5d206.p.ssafy.io:8443/taglist/cat", {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.categorylist = res.data;
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

    axios
      .get("https://i5d206.p.ssafy.io:8443/taglist/", {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.taglist = res.data;
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
  data() {
    return {
      ent_index: null,
      checkAll: false,
      isIndeterminate: true,
      categorylist: [],
      careerlist: [],
      taglist: [],
      selected: [],
      //resultList: this.getResultList(),
      isUnion: "inter",
      isAbove: false,
      originlist: [],
      resultList: this.getResultList,
      cat_ind: "",
      range: [1, 9],
      marks: {
        1: "신입",
        2: "1년",
        3: "2년",
        4: "3년",
        5: "4년",
        6: "5년 이상",
        7: "10년 이상",
        8: "15년 이상",
      },
    };
  },
  components: {
    SideBarCompany,
    headerSearchUser,
    UserInfoCard,
  },
  computed: {
    hasResult() {
      return this.resultList.length > 0;
    },
  },
  watch: {
    cat_ind() {
      this.selected = [];
      if (this.cat_ind == 1) {
        axios
          .get("https://i5d206.p.ssafy.io:8443/taglist/", {
            headers: { Authorization: token },
          })
          .then((res) => {
            this.taglist = res.data;
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
      } else {
        axios
          .get(`https://i5d206.p.ssafy.io:8443/taglist/${this.cat_ind}`, {
            headers: { Authorization: token },
          })
          .then((res) => {
            this.taglist = res.data;
          })
          .catch((err) => {
            if (err.response == 401) {
              this.$cookies.remove("PID_AUTH");
              this.$message.error("로그인세션이 만료되었습니다");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }
    },
    originlist() {
      this.resultList = [];
      for (var i = 0; i < this.originlist.length; i++) {
        if (
          this.originlist[i].car_index >= this.range[0] &&
          this.originlist[i].car_index <= this.range[1]
        ) {
          this.resultList.push(this.originlist[i]);
        }
      }
    },
    range() {
      this.resultList = [];
      for (var i = 0; i < this.originlist.length; i++) {
        if (
          this.originlist[i].car_index >= this.range[0] &&
          this.originlist[i].car_index <= this.range[1]
        ) {
          this.resultList.push(this.originlist[i]);
        }
      }
    },
  },

  methods: {
    getOriginList() {
      this.originlist = [];
      console.log(this.isUnion);
      if (this.isUnion == "union") {
        axios
          .get("https://i5d206.p.ssafy.io:8443/has/union", {
            headers: { Authorization: token },
            params: {
              list: this.selected,
            },
            paramsSerializer: (params) => {
              return qs.stringify(params, { arrayFormat: "repeat" });
            },
          })
          .then((res) => {
            this.originlist = res.data;
            console.log(this.originlist);
          })
          .catch((err) => {
            if (err.response == 401) {
              this.$cookies.remove("PID_AUTH");
              this.$message.error("로그인세션이 만료되었습니다");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      } else if (this.isUnion == "inter") {
        axios
          .get("https://i5d206.p.ssafy.io:8443/has/inter", {
            headers: { Authorization: token },
            params: {
              list: this.selected,
            },
            paramsSerializer: (params) => {
              return qs.stringify(params, { arrayFormat: "repeat" });
            },
          })
          .then((res) => {
            this.originlist = res.data;
            console.log(this.originlist);
          })
          .catch((err) => {
            if (err.response == 401) {
              this.$cookies.remove("PID_AUTH");
              this.$message.error("로그인세션이 만료되었습니다");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }
    },
  },
};
</script>

<style>
.selectArea {
  background-color: whitesmoke;
}
</style>
