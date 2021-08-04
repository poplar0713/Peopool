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
            !search ||
            data.company_name.toLowerCase().includes(search.toLowerCase()) ||
            data.company_field.toLowerCase().includes(search.toLowerCase())
        )
      "
      width="100%"
      height="250px"
    >
      <el-table-column label="Company" prop="follower"> </el-table-column>
      <el-table-column label="Field" prop="company_field"> </el-table-column>
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="mini" placeholder="Type to search" />
        </template>
        <template #default="scope">
          <el-button
            size="mini"
            type="primary"
            @click="unfollow(scope.row, scope.row.follower, this.user_index)"
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
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.user_index = index;
    //팔로잉정보 가져오기
    axios
      .get("/fol/following", {
        params: {
          index: index,
          type: 0,
        },
      })
      // 팔로워데이터 넣어주기
      .then((res) => {
        console.log(res);
        this.followings = res.data;
      })
      .catch((err) => console.log(err));
    return {
      dialogVisible: false,
      user_index: 0,
      followings: [],
      search: "",
    };
  },
  methods: {
    unfollow(row, follower, user) {
      console.log(row, follower, user);
      // 언팔로우
      axios
        .delete("/fol", {
          fol_type: 0,
          following: user,
          follower: follower,
        })
        .then(
          (res) => console.log(res),
          this.followings.splice(this.followings.indexOf(row), 1)
        )
        .catch();
    },
  },
};
</script>

<style></style>
