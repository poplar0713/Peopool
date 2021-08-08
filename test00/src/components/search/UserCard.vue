<template>
  <!-- 카드 -->
  <el-card shadow="hover" style="margin-bottom:20px">
    <el-row>
      <el-col :span="21"
        ><div>
          <UserDetail :user="item" :userindex="item.ind_index" /></div
      ></el-col>

    </el-row>
  </el-card>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";

import UserDetail from "@/components/UserDetail.vue";
export default {
  components: {
    UserDetail,
  },
  props: { item: Object },
  data() {
    // 본인(기업)토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 팔로우했는지 체크해보기
    axios
      .post("https://i5d206.p.ssafy.io:8443/fol/check", {
        fol_type: 1,
        follower: this.item.ind_index,
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
      follow: false,
      user_index: index,
    };
  },

  methods: {
    clickfollowBtn() {
      if (this.follow) {
        console.log("팔로우 해제");
        console.log(this.user_index, this.item.ind_index);
        axios
          .delete("https://i5d206.p.ssafy.io:8443/fol", {
            data: {
              fol_type: 1,
              following: this.user_index,
              follower: this.item.ind_index,
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
            fol_type: 1,
            following: this.user_index,
            follower: this.item.ind_index,
          })
          .then((res) => {
            console.log(res);
            this.follow = true;
            location.reload();
          })
          .catch();
      }
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
