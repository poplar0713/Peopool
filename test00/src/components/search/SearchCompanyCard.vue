<template>
  <!-- 카드 -->
  <el-card
    shadow="hover"
    style="margin-bottom:20px; text-align:center"
    @click="dialogVisible = true"
  >
    <h1>{{ item.ent_name }}</h1>
  </el-card>
  <!-- 모달창 -->
  <el-dialog v-model="dialogVisible" class="info">
    <el-container style="text-align:center">
      <el-header>
        <h2>
          {{ item.ent_name }}&nbsp;&nbsp;
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
      <el-container>
        <el-aside width="300px"
          ><el-image
            style="width: 300px; height: 300px"
            :src="ent_img"
          ></el-image
        ></el-aside>
        <el-main>
          <h4>기업 대표 : {{ item.ent_ceo }}</h4>
          {{ item.ent_info }}</el-main
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
        follower: this.item.ent_index,
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
    return {
      dialogVisible: false,
      follow: false,
      user_index: index,
    };
  },
  props: { item: Object },
  methods: {
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
              follower: this.item.ent_index,
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
            follower: this.item.ent_index,
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
  },
};
</script>

<style>
.info {
  padding: 3%;
  width: "50%";
}
</style>
