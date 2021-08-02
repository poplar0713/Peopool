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
      <el-table-column label="Company" prop="company_name"> </el-table-column>
      <el-table-column label="Field" prop="company_field"> </el-table-column>
      <el-table-column align="right">
        <template #header>
          <el-input v-model="search" size="mini" placeholder="Type to search" />
        </template>
        <template #default="scope">
          <el-button
            size="mini"
            type="primary"
            @click="unfollow(scope.row.company_name, this.user)"
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
    //팔로워정보 가져오기
    axios
      .get("http://52.79.162.52:8443/fol/following", {
        params: {
          index: index,
          type: this.$store.state.type,
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
      user: "김백수",
      followings: [
        {
          company_id: 0,
          company_name: "삼성전자",
          company_field: "전자",
        },
        {
          company_id: 1,
          company_name: "삼성SDS",
          company_field: "삼성",
        },
        {
          company_id: 2,
          company_name: "카카오",
          company_field: "IT",
        },
        {
          company_id: 3,
          company_name: "갓카오",
          company_field: "IT",
        },
        {
          company_id: 4,
          company_name: "카갓오",
          company_field: "IT",
        },
      ],
      search: "",
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
