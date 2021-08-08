<template>
  <el-button type="text" @click="dialogVisible = true" style="color:black"
    >Followers</el-button
  >

  <el-dialog
    title="Followers"
    v-model="dialogVisible"
    width="30%"
    :before-close="handleClose"
  >
    <!--  -->
    <el-table
      :data="
        followers.filter(
          (data) =>
            !search ||
            data.company_name.toLowerCase().includes(search.toLowerCase()) ||
            data.company_field.toLowerCase().includes(search.toLowerCase())
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
          <el-button
            size="mini"
            type="primary"
            @click="unfollow(scope.row.company_name, this.user)"
            >정보보러가기</el-button
          >
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
      dialogVisible: false,
      followers: [],
      search: "",
      company_index: "",
    };
  },
  methods: {
    unfollow(company, user) {
      console.log(company, user);
    },
  },
};
</script>

<style></style>
