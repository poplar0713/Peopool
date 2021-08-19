<template>
  <!-- 카드 -->
  <el-text
    size="mini"
    shadow="hover"
    style="text-align:center; cursor:pointer"
    @click="dialogVisible = true"
  >
    {{ this.company_info.ent_name }}
  </el-text>
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
                ><div v-if="this.company_info.ent_image">
                  <img
                    style="width: 100%; height: auto"
                    :src="this.company_info.ent_image_path"
                  />
                </div>
                <div v-else>
                  <img style="width: 100%; height: auto" :src="this.nonImage" />
                </div>
              </el-aside>
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
  name: "MainUserCompanyInfo",
  props: { companyindex: Number },
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
        follower: this.companyindex,
        following: index,
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
      .get(`https://i5d206.p.ssafy.io:8443/poe/path/${this.companyindex}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res.data);
        this.company_info.ent_index = res.data[0].ent_index;
        this.company_info.ent_name = res.data[0].ent_name;
        this.company_info.ent_contact = res.data[0].ent_contact;
        this.company_info.ent_address = res.data[0].ent_address;
        this.company_info.ent_email = res.data[0].ent_email;
        this.company_info.ent_history = res.data[0].ent_history;
        this.company_info.ent_website = res.data[0].ent_website;
        this.company_info.ent_introduce = res.data[0].ent_introduce;
        this.company_info.ent_ceo = res.data[0].ent_ceo;
        this.company_info.ent_image = res.data[0].ent_image;
        this.company_info.ent_image_path =
          "https://i5d206.p.ssafy.io/file/" +
          res.data[0].image_savefolder +
          "/" +
          res.data[0].image_savefile;
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
    // 기업본인 태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/cla/list", {
        headers: { Authorization: token },
        params: {
          ent_index: this.companyindex,
        },
      })
      .then((res) => {
        console.log(res.data);
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
        ent_image_path: "",
      },
      nonImage: "https://i5d206.p.ssafy.io/file/nonphoCom.png",
    };
  },
  methods: {
    //팔로잉버튼
    clickfollowBtn() {
      if (this.follow) {
        console.log("팔로우 해제");
        console.log(this.user_index, this.companyindex);
        axios
          .delete("https://i5d206.p.ssafy.io:8443/fol", {
            headers: { Authorization: this.token },
            data: {
              fol_type: 0,
              following: this.user_index,
              follower: this.companyindex,
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
            follower: this.companyindex,
          })
          .then((res) => {
            console.log(res);
            this.follow = true;
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
          query: { keyword: keyword },
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
