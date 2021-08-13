<template>
  <!-- 정보열기 -->
  <el-card
    shadow="hover"
    style="margin-bottom:20px; text-align:center"
    @click="dialogVisible = true"
  >
    <h1>{{ this.company_info.ent_name }}</h1>
  </el-card>
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
  name: "SearchCompanyCard",
  props: { item: Number },
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 팔로우했는지 체크해보기
    axios
      .post("https://i5d206.p.ssafy.io:8443/fol/check", {
        headers: { Authorization: token },
        fol_type: 0,
        follower: this.item,
        following: index,
      })
      .then((res) => {
        // 팔로우가 되어있는것
        console.log(res), (this.follow = true);
      })
      .catch((err) => {
        // 팔로우가 안되어있는것
        console.log(err);
        this.follow = false;
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    // 기업정보 가져오기
    axios
      .get(`https://i5d206.p.ssafy.io:8443/poe/index/${this.item}`, {
        headers: { Authorization: token },
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
          console.log("token error");
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    // 기업본인 태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/cla/list", {
        headers: { Authorization: token },
        params: {
          ent_index: this.item,
        },
      })
      .then((res) => {
        console.log(res);
        this.ent_tags = res.data;
      })
      .catch((err) => {
        if (err.response == 401) {
          console.log("token error");
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    return {
      activeNames: ["1"],
      dialogVisible: false,
      follow: false,
      user_index: index,
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
              follower: this.item,
            },
          })
          .then((res) => {
            console.log(res);
            this.follow = false;
          })
          .catch((err) => {
            console.log("token error");
            console.log(err.response);
            if (err.response == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
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
            follower: this.item,
          })
          .then((res) => {
            console.log(res);
            this.follow = true;
          })
          .catch((err) => {
            console.log("token error");
            console.log(err.response);
            if (err.response == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }
    },
    handleClose() {
      this.dialogVisible = false;
    },
    // 해당 태그의 기업들 검색으로
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
          query: { keyword: `${keyword}` },
        });
      }, 2000);
      setTimeout(() => {
        location.reload();
      }, 2001);
    },
  },
};
</script>

<style>
.info {
  padding: 3%;
  width: "50%";
}
</style>
