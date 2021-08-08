<template>
  <div>
    <div>
      <el-row :gutter="24">
        <el-col :span="6">
          <h1><i class="el-icon-s-grid"></i> {{ title }}</h1></el-col
        >
        <el-col :span="6" :offset="12"
          ><FollowingAppAll :following="following"
        /></el-col>
      </el-row>
    </div>
    <el-main>
      <el-space>
        <el-card
          class="box-card"
          style="width: 250px"
          v-for="user in following"
          :key="user"
        >
          <template #header>
            <div class="card-header">
              <img src="images/a.jpg" style="width: 150px" />
              <br />
            </div>
          </template>
          <UserDetail :user="user" :userindex="user.follower" />
          <div v-for="tag in user.tag" :key="tag" class="text item">
            {{ tag }}
          </div>
        </el-card>
      </el-space>
    </el-main>
  </div>
</template>

<script>
import UserDetail from "@/components/UserDetail.vue";
import FollowingAppAll from "@/components/MainCompany/FollowingAppAll.vue";
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  components: {
    UserDetail,
    FollowingAppAll,
  },

  mounted() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.company_index = index;
    //팔로잉정보 가져오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/fol/following", {
        params: {
          index: index,
          type: 1,
        },
        headers: { Authorization: token },
      })
      // 팔로워데이터 넣어주기
      .then((res) => {
        console.log(res);
        this.following = res.data;
      })
      .catch((err) => {
        console.log("token error");
        console.log(err.response.data.status);
        if (err.response.data.status == 401) {
          alert("로그인세션이이 만료 되었습니다.");
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
  props: {
    msg: String,
    title: String,
  },
  data() {
    return {
      following: [],
      company_index: "",
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
img {
  display: block;
  margin: 0px auto;
}
</style>
