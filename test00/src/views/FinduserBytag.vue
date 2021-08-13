<template>
  <el-container>
    <el-aside width="200px"><SideBarCompany :usertoken="index"/></el-aside>
    <el-container>
      <el-header><headerSearchUser /></el-header>
      <el-main>
        <h2>인재 찾기</h2>
        <h4>우리 회사에 적합한 인재를 찾아보세요</h4>
        <div class="selectArea">
          <div>
            <el-checkbox-group v-model="selected">
              <el-checkbox
                v-for="item in this.taglist"
                :key="item.taglist_index"
                :label="item.taglist_name"
                :value="item.taglist_index"
              ></el-checkbox>
            </el-checkbox-group>
          </div>
          <el-row>
            <el-col :span="10" style="align-">
              <el-switch
                style="display: block"
                v-model="this.isUnion"
                active-color="#5F2D9A"
                inactive-color="#FFC000"
                active-text="그리고"
                inactive-text="또는"
              >
              </el-switch
            ></el-col>
            <el-col :span="2">
              <el-button type="info" @click="this.getResultList">검색</el-button></el-col
            >
          </el-row>
        </div>
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
          console.log("token error");
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
  data() {
    return {
      taglist: [],
      selected: [],
      //resultList: this.getResultList(),
      isUnion: false,
      resultList: [],
    };
  },
  components: {
    SideBarCompany,
    headerSearchUser,
  },
  methods: {
    getResultList() {
      if (this.isUnion) {
        axios
          .get("https://localhost:8443/has/union", {
            params: {
              list: this.selected,
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
            params: {
              list: this.selected,
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
  padding: 2%;
  background-color: whitesmoke;
}
</style>
