<template>
  <el-button type="text" @click="dialogVisibleTag = true" style="color:black"
    >Profile</el-button
  >
  <el-container>
    <el-dialog
      title="Profile"
      v-model="dialogVisibleTag"
      width="50%"
      height="70%"
      :before-close="handleClose"
    >
      <SideBarProfileUser />
    </el-dialog>
  </el-container>
</template>

<script>
import SideBarProfileUser from "@/components/SideBarComponents/UserProfileInfo/SideBarProfileUser.vue";

export default {
  data() {
    return {
      dialogVisibleTag: false,
    };
  },
  components: {
    SideBarProfileUser,
  },
  methods: {
    handleClose(done) {
      this.$confirm("창을 닫으시겠습니까?")
        .then(() => {
          done();
          this.dialogVisibleTag = false;
        })
        .catch(() => {});
    },
  },
};
</script>

<style></style>
