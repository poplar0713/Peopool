<template>
  <el-button type="text" @click="dialogVisibleTag = true" style="color:#939597"
    >Profile</el-button
  >
  <el-container>
    <el-dialog
      title="Profile"
      v-model="dialogVisibleTag"
      width="50%"
      height="100%"
      :before-close="handleClose"
    >
    <TabProfile/>
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
import TabProfile from "@/components/MainUser/TabProfile.vue";

export default {
  data() {
    return {
      dialogVisibleTag: false,
    };
  },
  components:{
    TabProfile
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
