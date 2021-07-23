<template>
  <el-button type="text" @click="dialogVisible = true" style="color:black"
    >Interview Calender</el-button
  >

  <el-dialog
    title="My Calender"
    v-model="dialogVisible"
    width="70%"
    :before-close="handleClose"
  >
    <el-calendar>
      <template #dateCell="{data}">
        <div :class="data.isSelected ? 'is-selected' : ''">
          {{
            data.day
              .split("-")
              .slice(1)
              .join("-")
          }}
          {{ data.isSelected ? "✔️" : "" }}

        </div>
      </template>
    </el-calendar>
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

<style>
.is-selected {
  color: #1989fa;
}
</style>
