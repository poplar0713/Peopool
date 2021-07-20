<template>
  <el-button type="text" @click="dialogVisible = true" style="color:#939597"
    >TagBar</el-button
  >

  <el-dialog
    title="Tips"
    v-model="dialogVisible"
    width="30%"
    :before-close="handleClose"
  >
    <span>This is a message</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogVisible = false"
          >Confirm</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm("Are you sure to close this dialog?")
        .then(() => {
          done();
          this.dialogVisible = false;
        })
        .catch(() => {});
    },
  },
};
</script>

<style></style>
