<template>
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
          <el-row :gutte="10">
            <el-col :span="24">
              <el-checkbox-group v-model="selected">
                <el-checkbox
                  v-for="item in this.taglist"
                  :key="item.taglist_index"
                  :label="item.taglist_name"
                  :value="item.taglist_index"
                ></el-checkbox>
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
              >
              </el-switch
            ></el-col>
            <el-col :span="2"
              ><el-button type="success" icon="el-icon-search" @click="this.getResultList"
                >검색</el-button
              >
            </el-col>
          </el-row>
          <el-divider></el-divider>
        </el-main>
        <el-table :data="this.resultList">
          <el-table-column prop="ind_name" label="성명" />
          <el-table-column prop="ind_phone" label="전화번호" />
        </el-table>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBarCompany from "@/components/SideBarComponents/SideBarCompany.vue";
import headerSearchUser from "@/components/SideBarComponents/headerSearchUser.vue";
import axios from "axios";
//import jwt_decode from "jwt-decode";

var token;
//var decoded;
//var index;

export default {
  mounted() {
    token = this.$cookies.get("PID_AUTH");
    //    decoded = jwt_decode(token);
    //    index = decoded.index;

    axios
      .get("https://localhost:8443/taglist/cat", {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.categorylist = res.data;
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

    axios
      .get("https://localhost:8443/career", {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res.data);
        this.careerlist = res.data;
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
  },
  data() {
    return {
      categorylist: [],
      careerlist: [],
      taglist: [],
      selected: [],
      //resultList: this.getResultList(),
      isUnion: false,
      isAbove: false,
      originlist: [],
      resultList: [],
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
  },
  watch: {
    cat_ind() {
      console.log(this.cat_ind);
      axios
        .get(`https://localhost:8443/taglist/${this.cat_ind}`, {
          headers: { Authorization: token },
        })
        .then((res) => {
          console.log(res.data);
          this.taglist = res.data;
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
    getResultList() {
      var jsonArray = new Object();
      jsonArray.list = this.selected;
      jsonArray = JSON.stringify(jsonArray);
      console.log(jsonArray);

      if (this.isUnion) {
        axios
          .get("https://localhost:8443/has/union", {
            headers: { Authorization: token },
            params: {
              list: jsonArray,
            },
          })
          .then((res) => {
            this.resultList = res.data;
          })
          .catch((err) => {
            if (err.response == 401) {
              console.log("token error");
              this.$message.error("로그인세션이 만료되었습니다");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      } else {
        axios
          .get("https://localhost:8443:8443/has/inter", {
            headers: { Authorization: token },
            params: {
              list: jsonArray,
            },
          })
          .then((res) => {
            this.resultList = res.data;
          })
          .catch((err) => {
            if (err.response == 401) {
              console.log("token error");
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
