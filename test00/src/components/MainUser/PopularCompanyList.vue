<template>
<div style="width:70%; margin:0 auto">
  <el-carousel :interval="2000" type="card" height="200px">
    <el-carousel-item v-for="(item, i) in popularlist.slice(0, 10)" :key="i">
      Rank #{{ i + 1 }}
      <el-card
        shadow="hover"
        style="text-align:center; background-color:#F4F4EF; height:110%;"
        @click="(dialogVisible = true), getcompanydata(item.ent_index)"
      >
        <el-row>
          <el-col :span="8">
            {{ item.ent_image }}
          </el-col>
          <el-col :span="16">
            <h1>{{ item.ent_name }}</h1>
          </el-col>
        </el-row>
      </el-card>
    </el-carousel-item>
  </el-carousel>
  </div>
  <!-- 모달창 -->
  <el-dialog v-model="dialogVisible" class="info">
    <el-container style="text-align:center">
      <el-header>
        <h2>
          {{ this.company_info.ent_name }}&nbsp;&nbsp;
          <!-- 팔로우일경우 -->
          <span v-if="follow" style="color: Tomato;">
            <i
              class="fas fa-heart fa-2x"
              size:7x
              @click="clickfollowBtn"
              style="cursor:pointer"
            ></i>
          </span>
          <!-- 언팔로우일경우 -->
          <span v-if="follow == false" style="color: Tomato;">
            <i
              @click="clickfollowBtn"
              class="far fa-heart fa-2x"
              style="cursor:pointer"
            ></i>
          </span>
        </h2>
      </el-header>
      <br />
      <el-collapse v-model="activeNames" @change="handleChange">
        <el-collapse-item title="기본정보" name="1">
          <div>
            <el-container>
              <!-- 왼쪽 사진 -->
              <el-aside width="300px"
                ><el-image
                  style="width: 300px; height: 300px"
                  :src="ent_img"
                ></el-image
              ></el-aside>
              <!--  -->
              <el-main>
                <h4>기업 대표 : {{ this.company_info.ent_ceo }}</h4>
                {{ this.company_info.ent_info }}
                <br />
                {{ this.company_info.ent_introduce }}</el-main
              >
            </el-container>
          </div>
        </el-collapse-item>
        <el-collapse-item title="역사" name="2">
          <div>
            {{ this.company_info.ent_history }}
          </div>
        </el-collapse-item>
        <el-collapse-item title="태그" name="3">
          <div
            v-if="this.ent_tags.length > 0"
            style="width:100%; word-break:break-all;word-wrap:break-word;"
          >
            <el-tag
              v-for="item in ent_tags"
              style="margin:5px"
              :key="item.list_index"
              :type="warning"
              effect="plain"
              closable
              :disable-transitions="true"
              @click="GetTagCompany(item.list_name)"
            >
              {{ item.list_name }}
            </el-tag>
          </div>
          <div v-else style="align-text:center">
            선택된 태그가 없습니다
          </div>
        </el-collapse-item>
        <el-collapse-item title="연락처 및 찾아오는주소" name="4">
          <div>Tel. {{ this.company_info.ent_contact }}</div>
          <div>email. {{ this.company_info.ent_email }}</div>
          <div>address. {{ this.company_info.ent_address }}</div>
          <div>website. {{ this.company_info.ent_website }}</div>
        </el-collapse-item>
      </el-collapse>
    </el-container>
  </el-dialog>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  name: "PopularCompanyList",
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;

    axios.get("https://i5d206.p.ssafy.io:8443/poe/ByFollower").then((res) => {
      this.popularlist = res.data;
    });
    return {
      activeNames: ["1"],
      dialogVisible: false,
      follow: false,
      user_index: index,
      popularlist: [],
      ent_tags: [],
      company_info: {
        ent_index: "",
        ent_name: "",
        ent_image: "",
        ent_contact: "",
        ent_email: "",
        ent_ceo: "",
        ent_history: "",
        ent_address: "",
        ent_website: "",
        ent_introduce: null,
      },
    };
  },
  methods: {
    getcompanydata(companyindex) {
      // 팔로우했는지 체크해보기
      axios
        .post("https://i5d206.p.ssafy.io:8443/fol/check", {
          headers: { Authorization: this.token },
          fol_type: 0,
          follower: companyindex,
          following: this.user_index,
        })
        .then((res) => {
          // 팔로우가 되어있는것
          if (res.status == 200) {
            this.follow = true;
          }
          if (res.status == 204) {
            this.follow = false;
          }
        })
        .catch((err) => {
          // 팔로우가 안되어있는것
          console.log(err);
          if (err.response == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            this.$cookies.remove("PID_AUTH");
            localStorage.clear();
            this.$router.push("/");
          }
        });
      // 기업정보 가져오기
      axios
        .get(`https://i5d206.p.ssafy.io:8443/poe/index/${companyindex}`, {
          headers: { Authorization: this.token },
        })
        .then((res) => {
          this.company_info.ent_index = res.data.ent_index;
          this.company_info.ent_name = res.data.ent_name;
          this.company_info.ent_image = res.data.ent_image;
          this.company_info.ent_contact = res.data.ent_contact;
          this.company_info.ent_address = res.data.ent_address;
          this.company_info.ent_email = res.data.ent_email;
          this.company_info.ent_history = res.data.ent_history;
          this.company_info.ent_website = res.data.ent_website;
          this.company_info.ent_introduce = res.data.ent_introduce;
          this.company_info.ent_ceo = res.data.ent_ceo;
        })
        .catch((err) => {
          console.log(err.response);
          if (err.response == 401) {
            this.$cookies.remove("PID_AUTH");
            this.$message.error("로그인세션이 만료되었습니다");
            localStorage.clear();
            this.$router.push("/");
          }
        });
      // 기업본인 태그목록 불러오기
      axios
        .get("https://i5d206.p.ssafy.io:8443/cla/list", {
          headers: { Authorization: this.token },
          params: {
            ent_index: companyindex,
          },
        })
        .then((res) => {
          console.log(res);
          this.ent_tags = res.data;
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
    //팔로잉버튼
    clickfollowBtn() {
      if (this.follow) {
        console.log("팔로우 해제");
        console.log(this.user_index, this.item);
        axios
          .delete("https://i5d206.p.ssafy.io:8443/fol", {
            headers: { Authorization: this.token },
            data: {
              fol_type: 0,
              following: this.user_index,
              follower: this.company_info.ent_index,
            },
          })
          .then((res) => {
            console.log(res);
            this.follow = false;
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
      } else if (this.follow == false) {
        console.log("팔로잉");
        axios
          .post("https://i5d206.p.ssafy.io:8443/fol", {
            headers: { Authorization: this.token },
            fol_type: 0,
            following: this.user_index,
            follower: this.company_info.ent_index,
          })
          .then((res) => {
            console.log(res);
            this.follow = true;
          })
          .catch((err) => {
            console.log(err.response);
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

</style>
