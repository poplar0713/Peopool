<template>
  <div style="width: 95%">
    <div>
      <div v-if="hasVideo">
        <video
          src="@/assets/samplevideo.mp4"
          height="360"
          width="640"
          controls
          style="width:100%; height:100%"
        ></video>
      </div>
      <div v-else class="videoNotFound">
        <h4>PR 동영상을 아직 업로드 하지 않았습니다</h4>
        <h4>오른쪽 상단의 버튼을 눌러 영상을 업로드 해보세요</h4>
      </div>
    </div>
    <div style="float:right;">
      <el-upload
        limit="1"
        action="https://jsonplaceholder.typicode.com/posts/"
        :file-list="fileList"
        accept="video/*"
        style="margin-left:10%"
      >
        <el-button size="small">PR영상 업로드</el-button>
      </el-upload>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      mainsearch: "",
      search: "",
      username: "",
      hasVideo: true,
      hasDoc: true,
      videosrc: "@/assets/samplevideo.mp4",
      fileList: [],
    };
  },
  methods: {
    uploadFile() {},
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file.size);
    },
    handleExceed() {
      this.$message.warning(`최대 1개의 파일만 업로드 가능합니다`);
    },
    beforeRemove(file) {
      return this.$confirm(`Cancel the transfert of ${file.name} ?`);
    },
  },
};
</script>

<style></style>
