<template>
  <!-- 카드 -->
  <el-card shadow="hover" style="margin-bottom:20px">
    <el-row>
      <el-col :span="21" @click="dialogVisible = true"
        ><div>
          <h4>{{ item.ent_name }}</h4>
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
          {{ item.ent_name }}
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
        console.log(err), (this.follow = false);
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
            data: {
              fol_type: 0,
              following: this.user_index,
              follower: this.item.ent_index,
            },
          })
          .then((res) => {
            console.log(res);
            this.follow = false;
            location.reload();
          })
          .catch((err) => console.log(err));
      } else if (this.follow == false) {
        console.log("팔로잉");
        axios
          .post("https://i5d206.p.ssafy.io:8443/fol", {
            fol_type: 0,
            following: this.user_index,
            follower: this.item.ent_index,
          })
          .then((res) => {
            console.log(res);
            this.follow = true;
            location.reload();
          })
          .catch();
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
