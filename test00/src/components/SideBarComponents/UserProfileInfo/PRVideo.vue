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
      <div id="formdiv">
        <span class="filetype">
          <span class="file-text"></span>
          <span class="file-btn">찾아보기</span>
          <span class="file-select"
            ><input
              type="file"
              class="input-file"
              size="1"
              @change="uploadChange"
              ref="file"
              multiple="multiple"
          /></span>
        </span>
        <button @click="upload" class="summitbtn">
          Upload
        </button>
      </div>
      <!-- <form v-on:submit.prevent enctype="multipart/form-data">
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
      </form> -->
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
    uploadChange(file) {
      document.getElementsByClassName("file-text")[0].innerHTML = "";
      let filedata = file.target.files[0].name;
      this.filename = filedata;
      document.getElementsByClassName("file-text")[0].innerHTML = filedata;
      console.log(filedata);
    },
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
  background-color: #E6A23C;
  color: rgba(255, 255, 255, 0.74) !important;
  border: none;
  height: 28px;
  font-size: 15px;
  padding: 2px 15px 0px 15px;
}
.filetype .file-text + .file-btn {
  display: inline-block;
  background-color: #E6A23C;
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
