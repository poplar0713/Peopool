<template>
  <el-button type="text" @click="dialogVisible = true" style="color:black"
    >Followings</el-button
  >

  <el-dialog
    title="Followings"
    v-model="dialogVisible"
    width="30%"
    :before-close="handleClose"
  >
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
      <el-table-column label="Company" prop="name"> </el-table-column>
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="mini" placeholder="Type to search" />
        </template>
        <template #default="scope">
          <el-button size="mini" type="primary" @click="unfollow(scope.row)"
            >following</el-button
          >
          <!-- {{scope.row.company_name}} -->
        </template>
      </el-table-column>
    </el-table>
    <!--  -->
  </el-dialog>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  data() {
    return {
      dialogVisible: false,
      user_index: "",
      followings: [],
      search: "",
    };
  },
  mounted() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.user_index = index;
    //팔로잉정보 가져오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/fol/following", {
        params: {
          index: index,
          type: 0,
        },
        headers: { Authorization: token },
      })
      // 팔로워데이터 넣어주기
      .then((res) => {
        console.log(res);
        this.followings = res.data;
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
  methods: {
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
          headers: { Authorization: this.token },
        })
        .then((res) => {
          console.log(res),
            this.followings.splice(this.followings.indexOf(row), 1);
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
    handleClose(done) {
      this.$confirm("창을 닫으시겠습니까?")
        .then(() => {
          done();
          this.dialogVisible = false;
          location.reload();
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
  },
};
</script>

<style></style>
