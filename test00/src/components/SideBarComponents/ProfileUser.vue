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
    <SideBarProfileUser/>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisibleTag = false">Cancel</el-button>
          <el-button type="primary" @click="(dialogVisibleTag = false), tagok()"
            >Confirm</el-button
          >
        </span>
      </template>
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
  components:{
    SideBarProfileUser
  },
  methods: {
    handleClose(done) {
      this.$confirm("Are you sure to close this dialog?")
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
