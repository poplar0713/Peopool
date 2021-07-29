<template>
  <div style="width: 100%; height:100%">
    <div>
      <div v-if="hasVideo">
        <!-- <video
          src="@/assets/samplevideo.mp4"
          height="300"
          width="500"
          controls
          style="width:100%; height:300"
        ></video> -->
      </div>
      <div v-else class="videoNotFound">
        <h4>PR 동영상을 아직 업로드 하지 않았습니다.</h4>
        <h4>Profile에서 동영상을 업데이트해주세요.</h4>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      mainsearch: "",
      search: "",
      username: this.$store.state.temp.UserName,
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
