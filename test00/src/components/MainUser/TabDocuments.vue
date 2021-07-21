<template>
  <el-upload
    class="upload-demo"
    action="https://jsonplaceholder.typicode.com/posts/"
    :on-preview="handlePreview"
    :on-remove="handleRemove"
    :before-remove="beforeRemove"
    multiple
    :limit="3"
    :on-exceed="handleExceed"
    :file-list="$store.state.fileList"
  >
    <el-button size="small">Upload</el-button>
        jpg/png files with a size less than 500kb
    <template #tip>
      <div class="el-upload__tip">
      </div>
    </template>
  </el-upload>
</template>

<script>
export default {
  data() {
    return {

    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, fileList) {
      this.$message.warning(
        `The limit is 3, you selected ${
          files.length
        } files this time, add up to ${files.length + fileList.length} totally`
      );
    },
    beforeRemove(file, fileList) {
      console.log(fileList);
      return this.$confirm(`Cancel the transfert of ${file.name} ?`);
    },
  },
};
</script>

<style></style>
