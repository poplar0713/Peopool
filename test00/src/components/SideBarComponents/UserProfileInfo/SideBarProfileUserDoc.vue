<template>
  <div style="margin:20px">
    <el-upload
      class="upload-demo"
      ref="upload"
      :auto-upload="false"
      :on-change="handlefiletype"
      :on-exceed="handleExceed"
      :limit="1"
    >
      <template #trigger>
        <el-button size="small" type="primary">찾아보기</el-button>
      </template>
      <el-button
        style="margin-left: 10px; backgroud-color:#5f2d9a"
        size="small"
        @click="submitUpload"
        >업로드</el-button
      >
      <template #tip>
        <div class="el-upload__tip">
          500kb 이하의 PDF, PPT, Docx 파일을 선택해주세요.
        </div>
      </template>
    </el-upload>
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
    handlefiletype() {
      let docinput = document.getElementsByClassName("el-upload__input")[2];
      let docs = docinput.files[0];
      const isPDF = docs.type === "application/pdf";
      const isDOC =
        docs.type ===
        "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
      const isPPT =
        docs.type ===
        "application/vnd.openxmlformats-officedocument.presentationml.presentation";

      const isLt5K = docs.size / 1024 / 1024 < 500000;

      if (!isPDF && !isDOC && !isPPT) {
        this.$message.error("JPG, JPEG, PNG, JFIF 형식만 올려주세요.");

        return;
      }
      if (!isLt5K) {
        this.$message.error("500KB 이하의 문서가 필요해요.");
        return;
      }
      return (isPDF || isDOC || isPPT) && isLt5K;
    },
    submitUpload() {
      if (!this.handlefiletype()) {
        return;
      }
      const token = this.$cookies.get("PID_AUTH");
      const decoded = jwt_decode(token);
      const index = decoded.index;
      let docinput = document.getElementsByClassName("el-upload__input")[2];
      var frm = new FormData();
      var Filedata = docinput.files[0];
      frm.append("upfile", Filedata);
      axios
        .post(`https://i5d206.p.ssafy.io:8443/poi/resume/${index}`, frm, {
          headers: { Authorization: token },
        })
        .then((response) => {
          console.log(response.status);
          if (response.status == 200) {
            this.$message.success("업로드 되었습니다");
            this.$emit("uploadDoc");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },

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
        `하나의 파일만 올려주세요. ${
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
            document.getElementsByClassName("file-text")[2].innerHTML = "";
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    uploadChange(file) {
      document.getElementsByClassName("file-text")[2].innerHTML = "";
      let filedata = file.target.files[0].name;
      this.filename = filedata;
      document.getElementsByClassName("file-text")[2].innerHTML = filedata;
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
