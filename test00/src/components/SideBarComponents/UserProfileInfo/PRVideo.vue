<template>
  <div style="width:80%">
    <div>
      <div v-if="!hasVideo">
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
        <h4>하단의 버튼을 눌러 영상을 업로드 해보세요!</h4>
      </div>
    </div>
    <div>
      <el-upload
        class="upload-demo"
        ref="upload"
        :auto-upload="false"
        :on-change="handlefiletype"
        :on-exceed="handleExceed"
        :file-list="fileList"
        :limit="1"
      >
        <template #trigger>
          <el-button size="small" type="primary">찾아보기</el-button>
        </template>
        <el-button
          style="margin-left: 10px; backgroud-color:#5f2d9a"
          size="small"
          @click="upload"
          >업로드</el-button
        >
        <template #tip>
          <div class="el-upload__tip">
            500kb 이하의 MP4 파일을 선택해주세요.
          </div>
        </template>
      </el-upload>
    </div>
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  name: "PRVideo",
  props: {
    vediofilepath: String,
    hasVideo: Boolean,
  },
  data() {
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    return {
      mainsearch: "",
      search: "",
      username: "",
      hasDoc: true,
      ind_index: index,
      indexpath: `https://i5d206.p.ssafy.io:8443/poi/video/${index}`,
      videosrc: "@/assets/samplevideo.mp4",
      fileList: [],
    };
  },
  methods: {
    handlefiletype() {
      let docinput = document.getElementsByClassName("el-upload__input")[1];
      let videofile = docinput.files[0];
      // video/mp4
      console.log("handlemp4-", videofile);
      const isMP4 = videofile.type === "video/mp4";
      // let flag = false;
      const isLt5K = videofile.size / 1024 / 1024 < 500000;

      if (!isMP4) {
        this.$message.error("MP4 형식만 올려주세요.");
        // this.fileList.splice(0);
        // flag = true;
        // console.log("delete?-", this.fileList);
        return;
      }
      if (!isLt5K) {
        this.$message.error("500KB 이하의 영상이 필요해요.");
        return;
      }

      return isMP4 && isLt5K;
    },

    upload() {
      // console.log("upload-", this.fileList);
      if (!this.handlefiletype()) {
        return;
      }
      const token = this.$cookies.get("PID_AUTH");
      const decoded = jwt_decode(token);
      const index = decoded.index;
      let docinput = document.getElementsByClassName("el-upload__input")[1];
      let videofile = docinput.files[0];
      var frm = new FormData();
      // let Filedata =;
      frm.append("upfile", videofile);

      axios
        .post(`https://i5d206.p.ssafy.io:8443/poi/video/${index}`, frm, {
          headers: { Authorization: token },
        })
        .then((res) => {
          if (res.status == 200) {
            <el-alert title="업로드 되었습니다" type="success"></el-alert>;
            this.$emit("uploadPR");
          }
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

<style scoped>
#formdiv {
  margin: 20px;
}

.filetype {
  position: relative;
  display: inline-block;
  vertical-align: top;
  *margin-right: 4px;
}

.filetype * {
  vertical-align: middle;
}

.filetype .file-text {
  position: relative;
  width: 200px;
  text-overflow: ellipsis;
  white-space: nowrap;
  overflow: hidden;
  display: inline-block;
  height: 25px;
  background-color: #ecefef;
  margin: 0;
  border: 1px solid #cdd3d4;
  line-height: 20px;
  z-index: 10;
}

.filetype .file-select {
  position: absolute;
  top: 0;
  right: 0;
  width: 80px;
  overflow: hidden;
}

.filetype .file-select .input-file {
  width: 60px;
  filter: alpha(opacity=0);
  opacity: 0;
  height: 20px;
}
.summitbtn {
  background-color: #e6a23c;
  color: rgba(255, 255, 255, 0.74) !important;
  border: none;
  height: 28px;
  font-size: 15px;
  padding: 2px 15px 0px 15px;
}
.filetype .file-text + .file-btn {
  display: inline-block;
  background-color: #e6a23c;
  height: 27px;
  line-height: 22px;
  padding: 2px 15px 0px 15px;
  color: rgba(255, 255, 255, 0.74) !important;
  cursor: pointer;
  margin-left: 5px;
  font-size: 15px;
  margin-right: 5px;
}
</style>
