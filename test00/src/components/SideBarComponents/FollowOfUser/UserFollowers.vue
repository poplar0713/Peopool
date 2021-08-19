<template>
  <el-button type="text" @click="getFollowers" style="color:black"
    >팔로워</el-button
  >

  <el-dialog title="" v-model="dialogVisible" width="30%">
    <h2 style="margin:0 auto; text-align:center">Followers <br>{{ this.followersNumber }}</h2>

    <el-table
      :data="
        Followers.filter(
          (data) =>
            !search || data.name.toLowerCase().includes(search.toLowerCase())
        )
      "
      width="100%"
      height="250px"
    >
    <el-table-column width="100%">
        <template #default="scope">
          <CompanyInfoSquareImage :companyindex="scope.row.following" />
        </template>
      </el-table-column>
      <el-table-column align="center">
        <template #header>
          <el-input v-model="search" size="mini" placeholder="검색어를 입력해주세요" />
        </template>
        <template #default="scope">
          <div class="grid-content bg-purple">
            <CompanyInfoName :companyindex="scope.row.following" />
          </div>
        </template>
      </el-table-column>
    </el-table>
    <!--  -->
  </el-dialog>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import CompanyInfoName from "@/components/CompanyInfo/CompanyInfoName.vue";
import CompanyInfoSquareImage from "@/components/CompanyInfo/CompanyInfoSquareImage.vue";


export default {
  name: "UserFollowers",
  components: { CompanyInfoName,CompanyInfoSquareImage },
  data() {
    return {
      dialogVisible: false,
      user_index: "",
      Followers: [],
      search: "",
      followersNumber: 0,
    };
  },
  mounted() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.user_index = index;
  },
  methods: {
    getFollowers() {
      this.dialogVisible = true;
      //팔로잉정보 가져오기
      axios
        .get("https://i5d206.p.ssafy.io:8443/fol/follower", {
          params: {
            index: this.user_index,
            type: 0,
          },
          headers: { Authorization: this.$store.state.usertoken },
        })
        // 팔로워데이터 넣어주기
        .then((res) => {
          console.log(res);
          this.Followers = res.data;
        })
        .catch((err) => {
          if (err.response == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            this.$cookies.remove("PID_AUTH");
            localStorage.clear();
            this.$router.push("/");
          }
        });
      // 팔로워숫자 가져오기
      axios
        .get("https://i5d206.p.ssafy.io:8443/fol/counter", {
          params: {
            index: this.user_index,
            type: 0,
          },
          headers: { Authorization: this.$store.state.usertoken },
        })
        // 팔로워데이터 넣어주기
        .then((res) => {
          console.log(res);
          this.followersNumber = res.data;
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
            fol_type: 0,
            follower: row.follower,
            following: row.following,
          },
          headers: { Authorization: this.$store.state.usertoken },
        })
        .then((res) => {
          console.log(res),
            this.Followers.splice(this.Followers.indexOf(row), 1);
        })
        .catch((err) => {
          if (err.response == 401) {
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
