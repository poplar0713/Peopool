<template>
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
          <h4>기업 대표 : {{ ent_ceo }}</h4>
          {{ ent_info }}</el-main
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

  <el-card width="40%">
    <el-row>
      <el-col :span="8" @click="dialogVisible = true">
        <el-image
          style="width: 100px; height: 100px"
          :src="ent_img"
          :fit="fit"
        ></el-image>
      </el-col>
      <el-col :span="10" @click="dialogVisible = true">
        <h3>{{ ent_name }}</h3>
      </el-col>
      <el-col :span="6">
        <div v-if="follow">
          <el-button @click="clickfollowBtn">언팔로우</el-button>
        </div>
        <div v-else><el-button @click="clickfollowBtn">팔로우</el-button></div>
      </el-col>
    </el-row>
  </el-card>
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
  props: ["ent_id", "ent_name", "ent_info", "ent_ceo", "ent_img", "follow"],
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
