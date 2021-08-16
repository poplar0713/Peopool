<template>
  <div class="nameCard" @click="this.dialogVisible = true">
    <div>{{ this.user.ind_name }}</div>
    <div>직무 : {{ this.user.cat_name }}</div>
    <div>경력 : {{ this.user.car_value }}</div>
    <div>
      태그 :
      <span v-for="item in this.ind_taglist" v-bind:key="item">{{ item.taglist_name }}</span>
    </div>
    <div></div>
  </div>

  <el-dialog
    title='this.user.ind_name + "님의 상세소개"'
    v-model="dialogVisible"
    width="30%"
    center
  >
    <span>It should be noted that the content will not be aligned in center by default</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="centerDialogVisible = false">Confirm</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script>
import axios from "axios";
export default {
  props: ["user"],
  data() {
    return {
      dialogVisible: false,
      ind_taglist: [],
    };
  },

  mounted() {
    axios
      .get("https://i5d206.p.ssafy.io:8443/has/tag", {
        params: {
          index: this.user.ind_index,
          type: 0,
        },
      })
      .then((res) => {
        this.ind_taglist = res.data;
      })
      .catch((err) => {
        console.log(err.response);
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          console.log("token error");
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
};
</script>

<style>
@import url("https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap");
.nameCard {
  width: 316px;
  height: 200px;
  border-color: burlywood;
  border-width: 0.2rem;
  border-style: solid;
  font-size: 1.2rem;
  font-family: "Gothic A1", sans-serif;
}
</style>
