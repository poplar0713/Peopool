<template>
  <!-- 카드 -->
  <el-card shadow="hover" style="margin-bottom:20px">
    <el-row>
      <el-col :span="21" @click="dialogVisible = true"
        ><div>
          <h4>{{ item.name }}</h4>
        </div></el-col
      >
      <el-col :span="3">
        <!-- 팔로우가 되어있을때 -->
        <span v-if="follow" style="color: Tomato;">
          <i
            class="fas fa-heart fa-2x"
            size:7x
            @click="clickfollowBtn"
            style="cursor:pointer"
          ></i>
        </span>
        <!-- 팔로우가 안되어있을때 -->
        <span v-if="follow == false" style="color: Tomato;">
          <i
            @click="clickfollowBtn"
            class="far fa-heart fa-2x"
            style="cursor:pointer"
          ></i>
        </span>
      </el-col>
    </el-row>
  </el-card>
  <!-- 모달창 -->
  <el-dialog v-model="dialogVisible" class="info">
    <el-container>
      <el-header>
        <h2>
          {{ this.ent_info.ent_name }}
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
      <el-container>
        <el-aside width="300px"
          ><el-image
            style="width: 300px; height: 300px"
            :src="ent_img"
          ></el-image
        ></el-aside>
        <el-main>
          <h4>기업 대표 : {{ this.ent_info.ent_ceo }}</h4>
          {{ this.ent_info.ent_info }}</el-main
        >
      </el-container>
      <el-footer> </el-footer>
    </el-container>
  </el-dialog>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
export default {
  data() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 팔로우했는지 체크해보기
    axios
      .post("https://i5d206.p.ssafy.io:8443/fol/check", {
        headers: { Authorization: token },
        fol_type: 0,
        follower: this.item.follower,
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
        if (err.response.data.status == 401) {
          this.$message.error('로그인세션이 만료되었습니다');
          localStorage.clear();
          this.$router.push("/");
        }
      });
    //기업정보 가져오기
    axios
      .get(`https://i5d206.p.ssafy.io:8443/poe/index/${this.item.follower}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.ent_info.ent_name = res.data.ent_name;
        this.ent_info.ent_contact = res.data.ent_contact;
        this.ent_info.ent_email = res.data.ent_email;
        this.ent_info.ent_image = res.data.ent_image;
        this.ent_info.ent_ceo = res.data.ent_ceo;
        this.ent_info.ent_history = res.data.ent_history;
        this.ent_info.ent_address = res.data.ent_address;
        this.ent_info.ent_website = res.data.ent_website;
        this.ent_info.ent_introduce = res.data.ent_introduce;
      })
      .catch((err) => {
        console.log("token error");
        console.log(err.response.data.status);
        if (err.response.data.status == 401) {
          this.$message.error('로그인세션이 만료되었습니다');
          localStorage.clear();
          this.$router.push("/");
        }
      });
    return {
      dialogVisible: false,
      follow: false,
      user_index: index,
      ent_index: this.item.follower,
      ent_info: {
        ent_name: "",
        ent_contact: "",
        ent_email: "",
        ent_image: "",
        ent_ceo: "",
        ent_history: "",
        ent_address: "",
        ent_website: "",
        ent_introduce: "",
      },
    };
  },
  props: { item: Object },
  methods: {
    // 팔로우버튼
    clickfollowBtn() {
      if (this.follow) {
        console.log("팔로우 해제");
        console.log(this.user_index, this.item.ent_index);
        axios
          .delete("https://i5d206.p.ssafy.io:8443/fol", {
            headers: { Authorization: this.token },
            data: {
              fol_type: 0,
              following: this.user_index,
              follower: this.ent_index,
            },
          })
          .then((res) => {
            console.log(res);
            this.follow = false;
            location.reload();
          })
          .catch((err) => {
            console.log("token error");
            console.log(err.response.data.status);
            if (err.response.data.status == 401) {
              this.$message.error('로그인세션이 만료되었습니다');
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
            follower: this.item.ent_index,
          })
          .then((res) => {
            console.log(res);
            this.follow = true;
            location.reload();
          })
          .catch((err) => {
            console.log("token error");
            console.log(err.response.data.status);
            if (err.response.data.status == 401) {
              this.$message.error('로그인세션이 만료되었습니다');
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }
    },
    // 창닫기
    handleClose() {
      this.dialogVisible = false;
    },
  },
  // company 정보 저장하기
  GetFollowingCompany() {},
};
</script>

<style>
.info {
  padding: 3%;
  width: "50%";
}
</style>
