<template>
  <el-scrollbar height="600px">
    <div>
      <el-main>
        <el-card
          class="box-card"
          style="width: 200px"
          v-for="user in userdata"
          :key="user"
        >
          <template #header>
            <div class="card-header">
              <img src="images/a.jpg" style="width: 150px" />
            </div>
          </template>
          <!-- <h1>{{ user.title }}</h1> -->
          <!-- <div v-for="tag in user.tag" :key="tag" class="text item">
            {{ tag }}
          </div> -->
          <div v-if="user.fol_type == 0">
            <UserDetail :user="user" :userindex="user.following" />
          </div>
          <div v-if="user.fol_type == 1">
            <UserDetail :user="user" :userindex="user.follower" />
          </div>
        </el-card>
      </el-main>
    </div>
  </el-scrollbar>
</template>

<script>
import UserDetail from "@/components/UserDetail.vue";
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  components: {
    UserDetail,
  },
  props: {
    userdata: Object,
  },
  data() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 내정보 가져오기
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
        this.followers = res.data;
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
    return {
      followers: [],
    };
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.box-card {
  display: inline-block;
  margin: 5px;
}
</style>
