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
      <el-table-column label="User" prop="name"> </el-table-column>
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
      })
      // 팔로워데이터 넣어주기
      .then((res) => {
        console.log(res);
        this.followings = res.data;
      })
      .catch((err) => console.log(err));
  },
  data() {
    return {
      dialogVisible: false,
      company_index: "",
      followings: [],
      search: "",
    };
  },
  methods: {
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
        })
        .then((res) => {
          console.log(res),
            this.followings.splice(this.followings.indexOf(row), 1);
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>

<style></style>
