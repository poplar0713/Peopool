<template>
  <div style="width:80%">
    <!-- <div>
      <img :src="photofilepath" height="150" width="150" />
    </div> -->
    <el-scrollbar>
      <div
        style="width:100%;"
        v-loading="loading"
        element-loading-text="Loading..."
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
      >
        <el-upload
          class="avatar-uploader"
          :auto-upload="false"
          :show-file-list="false"
          :on-change="handleAvatarSuccess"
        >
          <img v-if="imageUrl" :src="imageUrl" class="avatar" />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>

        <el-form-item label="">
          <el-input
            id="introtextarea"
            type="textarea"
            v-model="userintroduce"
            maxlength="1000"
            show-word-limit
            :autosize="{ minRows: 8, maxRows: 10 }"
            :placeholder="phtext"
          ></el-input>
        </el-form-item>
        <div style="float:right">
          <el-form-item>
            <el-button
              type="warning"
              @click="submitForm('ruleForm')"
              v-loading.fullscreen.lock="fullscreenLoading"
              >Save</el-button
            >
          </el-form-item>
        </div>
        <!-- </el-form> -->
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
import axios from "axios";
import jwt_decode from "jwt-decode";
export default {
  name: "SideBarProfileUserIntroduction",
  props: {
    photofilepath: String,
    introduce: String,
    curphoto: Boolean,
  },
  mounted() {
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.userindex = index;
    this.cookietoken = token;
  },
  created() {
    this.userintroduce = this.introduce;
  },

  data() {
    return {
      phtext:
        "자기소개를 입력해주세요" +
        "\n" +
        "안녕하세요 저는 리더십있는 000입니다." +
        "\n" +
        "저는 다음과 같은 역량을 가지고 있습니다!" +
        "\n" +
        " #1 " +
        "\n" +
        "...",
      loading: false,
      userindex: "",
      cookietoken: null,
      imageUrl: this.photofilepath,
      userintroduce: this.introduce,
      changeprofile: false,
      // fileList: [],
      rules: {
        Introduction: [
          {
            required: true,
            message: "Please input your Introduction",
            trigger: "blur",
          },
        ],
      },
      fullscreenLoading: false,
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      if (file != null) {
        let docinput = document.getElementsByClassName("el-upload__input")[0];
        let file = docinput.files[0];
        const isJPEG = file.type === "image/jpeg";
        const isPNG = file.type === "image/png";
        const isJPG = file.type === "image/jpg";

        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG && !isPNG && !isJPEG) {
          this.$message.error("JPG, JPEG, PNG, JFIF 형식만 올려주세요.");
          return;
        }
        if (!isLt2M) {
          this.$message.error("2MB 이하의 사진이 필요해요.");
          return;
        }

        this.changeprofile = true;
        this.imageUrl = URL.createObjectURL(file);
      }
    },
    beforeAvatarUpload() {},

    submitForm() {
      console.log("submitform-", this.changeprofile);
      if (this.curphoto == 2) {
        this.$message.error("사진을 업로드 해주세요.");
        return;
      }
      // 저장하는 방법 찾아보기
      if (this.changeprofile) {
        let uploadinput = document.getElementsByClassName(
          "el-upload__input"
        )[0];

        var photodata = uploadinput.files[0];

        var frm = new FormData();
        // var introducedata = this.$refs[]
        frm.append("upfile", photodata);
        // frm.append("introduce", introduce.value);

        axios
          .post("https://i5d206.p.ssafy.io:8443/poi/photo", frm, {
            headers: { Authorization: this.$store.state.usertoken },
            params: {
              index: this.userindex,
              introduce: this.userintroduce,
            },
          })
          .then((res) => {
            console.log(res);
            this.$message.success("업로드 되었습니다");
            // <el-alert title="업로드 되었습니다" type="success"></el-alert>;
            this.$emit("uploadintro");
          })
          .catch((err) => {
            this.failed();
            if (err.response == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
              this.$cookies.remove("PID_AUTH");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      } else {
        axios
          .put("https://i5d206.p.ssafy.io:8443/poi/intro", {
            headers: {
              Authorization: this.$store.state.usertoken,
            },
            ind_index: this.userindex,
            ind_introduce: this.userintroduce,
          })
          .then((res) => {
            if (res.status == 200 || res.status == 204) {
              this.$message.success("업로드 되었습니다");
            }
            // this.$emit("uploadintro");
            // this.openFullScreen2();
          })
          .catch((err) => {
            this.failed();
            if (err.response == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
              this.$cookies.remove("PID_AUTH");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    openFullScreen2() {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
      }, 2000);
    },
    successmessage() {
      this.$message({
        message: "수정완료 되었습니다",
        type: "success",
      });
    },
    failed() {
      this.$message.error("양식을 확인해주세요");
    },
  },
};
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 200px;
  display: block;
  margin: 10px;
  /* object-fit: cover; */
}
/* .box {
  width: 150px;
  height: 150px;
  border-radius: 70%;
  overflow: hidden;
  margin: 30px;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
} */

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
