<template>
  <!-- 카드 -->
  <el-card shadow="hover" style="margin-bottom:20px">
    <el-row>
      <el-col :span="21" @click="dialogVisible = true"
        ><div>
          <h4>{{ item.ent_name }}</h4>
        </div></el-col
      >
      <el-col :span="3">
        <!-- 팔로우 -->
        <span v-if="follow" style="color: Tomato;">
          <i
            class="fas fa-heart fa-2x"
            size:7x
            @click="clickfollowBtn"
            style="cursor:pointer"
          ></i>
        </span>
        <!--  -->
        <span v-if="follow == false" style="color: Tomato;">
          <i
            @click="clickfollowBtn"
            class="far fa-heart fa-2x"
            style="cursor:pointer"
          ></i>
        </span>
      </el-col>
    </el-row>
  </el-card>
  <!-- 모달창 -->
  <el-dialog v-model="dialogVisible" class="info">
    <el-container>
      <el-header>
        <h2>
          {{ item.ent_name }}
          <!-- 팔로우 -->
          <span v-if="follow" style="color: Tomato;">
            <i
              class="fas fa-heart fa-2x"
              size:7x
              @click="clickfollowBtn"
              style="cursor:pointer"
            ></i>
          </span>
          <!--  -->
          <span v-if="follow == false" style="color: Tomato;">
            <i
              @click="clickfollowBtn"
              class="far fa-heart fa-2x"
              style="cursor:pointer"
            ></i>
          </span>
        </h2>
      </el-header>
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
      <el-footer> </el-footer>
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
      follow: true,
    };
  },
  props: { item: Object },
  methods: {
    clickfollowBtn() {
      if (this.follow) {
        console.log("팔로우 해제");
        this.follow = false;
      } else {
        console.log("팔로잉");
        this.follow = true;
      }
    },
    handleClose() {
      this.dialogVisible = false;
    },
  },
};
</script>
