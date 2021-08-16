<template>
  <div style="width: 95%">
    <div>
      <div v-if="hasVideo">
        <video
          :src="vediofilepath"
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
      <!-- <el-upload
        limit="1"
        :action="indexpath"
        :file-list="fileList"
        accept="video/*"
        style="margin-left:10%"
      >
        <el-button size="small" @click="upload">PR영상 업로드</el-button>
      </el-upload> -->
      <form v-on:submit.prevent enctype="multipart/form-data">
        <input
          multiple="multiple"
          type="file"
          name="file"
          id="file"
          ref="file"
        />
        <button @click="upload">
          Upload
        </button>
      </form>
    </div>
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  name:"PRVideo",
  props: {
    vediofilepath: String,
  },
  data() {
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    return {
      mainsearch: "",
      search: "",
      username: "",
      hasVideo: true,
      hasDoc: true,
      ind_index: index,
      indexpath: `https://i5d206.p.ssafy.io:8443/poi/video/${index}`,
      videosrc: "@/assets/samplevideo.mp4",
      fileList: [],
    };
  },
  methods: {
    upload() {
      const token = this.$cookies.get("PID_AUTH");
      const decoded = jwt_decode(token);
      const index = decoded.index;

      var frm = new FormData();
      var Filedata = this.$refs.file.files[0];
      frm.append("upfile", Filedata);

      axios
        .post(`https://i5d206.p.ssafy.io:8443/poi/video/${index}`, frm, {
          headers: { Authorization: token },
        })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
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
