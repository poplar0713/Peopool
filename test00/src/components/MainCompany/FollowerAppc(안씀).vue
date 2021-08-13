<template>
  <div>
    <div>
      <el-row :gutter="24">
        <el-col :span="6" :offset="12"
          ><FollowerAppcAll :follower="follower"
        /></el-col>
      </el-row>
    </div>
    <el-main>
      <el-space>
        <el-card
          class="box-card"
          style="width: 250px"
          v-for="user in follower"
          :key="user"
        >
          <template #header>
            <div class="card-header">
              <img src="images/a.jpg" style="width: 150px" />
              <br />
            </div>
          </template>
          <UserDetail :user="user" :userindex="user.following" />
        </el-card>
      </el-space>
    </el-main>
  </div>
</template>

<script>
import UserDetail from "@/components/UserDetail.vue";
import FollowerAppcAll from "@/components/MainCompany/FollowerAppcAll.vue";
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  name: "HelloWorld",
  components: {
    UserDetail,
    FollowerAppcAll,
  },

  mounted() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.company_index = index;
    //팔로워정보 가져오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/fol/follower", {
        params: {
          index: index,
          type: 1,
        },
        headers: { Authorization: token },
      })
      // 팔로워데이터 넣어주기
      .then((res) => {
        console.log(res);
        this.follower = res.data;
      })
      .catch((err) => {
        console.log("token error");
        console.log(err.response);
        if (err.response == 401) {
          this.$message.error('로그인세션이 만료되었습니다');
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
  props: {
    msg: String,
  },
  data() {
    return {
      follower: [],
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
