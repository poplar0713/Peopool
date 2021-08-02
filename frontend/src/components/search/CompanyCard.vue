<template>
  <!-- 카드 -->
  <el-card shadow="hover" style="margin-bottom:20px">
    <el-row>
      <!-- <el-col :span="4" @click="dialogVisible = true">
        <div>
        <el-image
          style="width: 100px; height: 100px"
          :src="ent_img"
          :fit="fit"
        ></el-image>
      </div></el-col> -->
      <el-col :span="16" @click="dialogVisible = true"
        ><div>
          <h3>{{ item.ent_name }}</h3>
        </div></el-col
      >
      <el-col :span="8">
        <div v-if="follow">
          <el-button @click="clickfollowBtn">언팔로우</el-button>
        </div>
        <div v-else><el-button @click="clickfollowBtn">팔로우</el-button></div>
      </el-col>
    </el-row>
  </el-card>
  <!-- 모달창 -->
  <el-dialog v-model="dialogVisible" class="info">
    <el-container>
      <el-header
        ><h2>{{ ent_name }}</h2></el-header
      >
      <el-container>
        <el-aside width="300px"
          ><el-image
            style="width: 300px; height: 300px"
            :src="ent_img"
          ></el-image
        ></el-aside>
        <el-main>
          <h4>기업 대표 : {{ item.ent_ceo }}</h4>
          {{ item.ent_info }}</el-main
        >
      </el-container>
      <el-footer>
        <el-button v-if="follow" @click="clickfollowBtn" type="danger"
          >팔로우 해제</el-button
        >
        <el-button v-else @click="clickfollowBtn" type="warining"
          >팔로우</el-button
        >
      </el-footer>
    </el-container>
  </el-dialog>
</template>

<style>
.info {
  padding: 3%;
  width: "50%";
}
</style>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
    };
  },
  props: { item: Object },
  methods: {
    clickfollowBtn() {
      if (this.follow) {
        console.log("팔로우 해제");
      } else {
        console.log("팔로잉");
      }
    },
    handleClose() {
      this.dialogVisible = false;
    },
  },
};
</script>
