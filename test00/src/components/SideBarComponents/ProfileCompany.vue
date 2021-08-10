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
      <SideBarProfileCompany />
    </el-dialog>
  </el-container>
</template>

<script>
import SideBarProfileCompany from "@/components/SideBarComponents/CompanyProfileInfo/SideBarProfileCompany.vue";

export default {
  data() {
    return {
      dialogVisibleTag: false,
    };
  },
  components: {
    SideBarProfileCompany,
  },
  methods: {
    handleClose(done) {
      this.$confirm("창을 닫으시겠습니까?")
        .then(() => {
          done();
          this.dialogVisibleTag = false;
        })
        .catch((err) => {
          console.log("token error");
          console.log(err.response);
          if (err.response == 401) {
            this.$message.error('로그인세션이 만료되었습니다');
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
  },
};
</script>

<style></style>
