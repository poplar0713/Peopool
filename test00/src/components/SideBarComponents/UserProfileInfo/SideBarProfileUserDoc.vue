<template>
  <!-- <span class="filetype">
    <span class="file-text"></span>
    <span class="file-btn">찾아보기</span>
    <span class="file-select"
      ><input type="file" class="input-file" size="1" @change="uploadChange"
    /></span>
  </span> -->

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
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
// import webviewer from "@/components/MainCompany/webviewer.vue";

export default {
  name: "SideBarProfileUserDoc",
  props: {
    // resumefilePath:String
  },

  components: {
    // webviewer
  },
  mounted() {},
  data() {
    return {
      fileList: "",
      filename: "첨부파일",
    };
  },
  methods: {
    handleChange(file, fileList) {
      this.fileList = fileList.slice(-3);
    },
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
    filenamechange() {
      alert("adf");
      // let Name = this.$refs.file.files[0].name;
      // console.log("file: ", this.$refs.file);
      // console.log(Name);
    },
    upload() {
      const token = this.$cookies.get("PID_AUTH");
      const decoded = jwt_decode(token);
      const index = decoded.index;

      var frm = new FormData();
      var Filedata = this.$refs.file.files[0];
      frm.append("upfile", Filedata);

      axios
        .post(`https://i5d206.p.ssafy.io:8443/poi/resume/${index}`, frm, {
          headers: { Authorization: token },
        })
        .then((response) => {
          console.log(response.status);
          if (response.status == 200) {
            <el-alert title="업로드 되었습니다" type="success"></el-alert>;
            this.$emit("uploadDoc");
            document.getElementsByClassName("file-text")[1].innerHTML = "";
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    uploadChange(file) {
      document.getElementsByClassName("file-text")[1].innerHTML = "";
      let filedata = file.target.files[0].name;
      this.filename = filedata;
      document.getElementsByClassName("file-text")[1].innerHTML = filedata;
      console.log(filedata);
    },
  },
};
</script>

<style scoped>
html,
body {
  height: 100%;
  width: 100%;
}
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
