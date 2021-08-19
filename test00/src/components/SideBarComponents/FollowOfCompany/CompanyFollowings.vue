<template>
  <el-button
    type="text"
    @click="getFollowings"
    style="color:black"
    >Followings</el-button
  >

  <el-dialog title="Followings" v-model="dialogVisible" width="30%">
    <h2 style="margin:0 auto; text-align:center">
      {{ this.followingsNumber }}
    </h2>
    <!--  -->
    <el-table
      :data="
        followings.filter(
          (data) =>
            !search || data.name.toLowerCase().includes(search.toLowerCase())
        )
      "
      width="100%"
      height="250px"
    >
    <el-table-column width="100%">
      <template #default="scope">
        <UserInfoCircleImage :userindex="scope.row.follower"/>
      </template>
    </el-table-column>
      <el-table-column align="center">
        <template #header>
          <el-input v-model="search" size="mini" placeholder="검색어를 입력해주세요" />
        </template>
        <template #default="scope">
          <el-row>
            <el-col :span="12"
              ><div class="grid-content bg-purple">
                <UserInfoName :userindex="scope.row.follower" /></div
            ></el-col>
            <el-col :span="12"
              ><div class="grid-content bg-purple-light">
                <el-button
                  size="mini"
                  type="primary"
                  @click="unfollow(scope.row)"
                  >following</el-button
                >
              </div></el-col
            >
          </el-row>
        </template>
      </el-table-column>
    </el-table>
    <!--  -->
  </el-dialog>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import UserInfoName from "@/components/UserInfo/UserInfoName.vue";
import UserInfoCircleImage from "@/components/UserInfo/UserInfoCircleImage.vue";

export default {
  name: "CompanyFollowings",
  mounted() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.company_index = index;
  },
  components: { UserInfoName,UserInfoCircleImage },
  data() {
    return {
      dialogVisible: false,
      company_index: "",
      followings: [],
      search: "",
      followingsNumber: 0,
    };
  },
  methods: {
    getFollowings() {
      this.dialogVisible = true
      //팔로잉정보 가져오기
      axios
        .get("https://i5d206.p.ssafy.io:8443/fol/following", {
          params: {
            index: this.company_index,
            type: 1,
          },
          headers: { Authorization: this.$store.state.usertoken },
        })
        // 팔로워데이터 넣어주기
        .then((res) => {
          console.log(res);
          this.followings = res.data;
        })
        .catch((err) => {
          if (err.response.data.status == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            this.$cookies.remove("PID_AUTH");
            localStorage.clear();
            this.$router.push("/");
          }
        });
      // 팔로잉숫자 가져오기
      axios
        .get("https://i5d206.p.ssafy.io:8443/fol/counting", {
          params: {
            index: this.company_index,
            type: 1,
          },
          headers: { Authorization: this.$store.state.usertoken },
        })
        // 팔로워데이터 넣어주기
        .then((res) => {
          console.log(res);
          this.followingsNumber = res.data;
        })
        .catch((err) => {
          if (err.response.data.status == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            this.$cookies.remove("PID_AUTH");
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
    unfollow(row) {
      console.log(row.following, row.follower);
      // 언팔로우
      axios
        .delete("https://i5d206.p.ssafy.io:8443/fol", {
          data: {
            fol_type: 1,
            follower: row.follower,
            following: row.following,
          },
          headers: { Authorization: this.$store.state.usertoken },
        })
        .then((res) => {
          console.log(res),
            this.followings.splice(this.followings.indexOf(row), 1);
        })
        .catch((err) => {
          if (err.response.data.status == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            this.$cookies.remove("PID_AUTH");
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
  },
};
</script>

<style></style>
