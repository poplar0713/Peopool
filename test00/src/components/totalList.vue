<template>
  <el-scrollbar height="600px">
    <div>
      <el-main>
        <el-card
          class="box-card"
          style="width: 200px"
          v-for="user in followers"
          :key="user"
        >
          <template #header>
            <div class="card-header">
              <img src="a.jpg" style="width: 150px" />
              <br />
            </div>
          </template>
          <h1>{{ user.title }}</h1>
          <div v-for="tag in user.tag" :key="tag" class="text item">
            {{ tag }}
          </div>
          <UserDetail :user="user" />
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
    followData: Array,
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
      })
      // 팔로워데이터 넣어주기
      .then((res) => {
        console.log(res);
        this.followers = res.data;
      })
      .catch((err) => console.log(err));
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
