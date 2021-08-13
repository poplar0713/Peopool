<template>
  <div>
    <div class="box" style="background: #BDBDBD;">
      <img class="profile" id="profilephoto" :src="photofilepath" />
    </div>
    <el-scrollbar>
      <div
        style="width:100%;"
        v-loading="loading"
        element-loading-text="Loading..."
        element-loading-spinner="el-icon-loading"
        element-loading-background="rgba(0, 0, 0, 0.8)"
      >
        <el-form
          :model="ruleForm"
          :rules="rules"
          ref="ruleForm"
          v-on:submit.prevent
          enctype="multipart/form-data"
        >
          <el-form-item label="" prop="Introduction">
            <input
              multiple="multiple"
              type="file"
              name="photo"
              id="photo"
              ref="photo"
              accept="image/jpeg, image/jpg, image/png"
              @change="changept(this)"
            />
          </el-form-item>

          <el-input
            id="introtextarea"
            type="textarea"
            v-model="ruleForm.Introduction"
          ></el-input>

          <div style="float:right">
            <el-form-item>
              <el-button @click="resetForm('ruleForm')">Reset</el-button>
              <el-button
                type="warning"
                @click="submitForm('ruleForm')"
                v-loading.fullscreen.lock="fullscreenLoading"
                >Save</el-button
              >
            </el-form-item>
            <!-- <div><img :src="photofilepath" width="200px" /></div> -->
          </div>
        </el-form>
      </div>
    </el-scrollbar>
  </div>
</template>

<script>
import axios from "axios";
import jwt_decode from "jwt-decode";

// import jwt_decode from "jwt-decode";
export default {
  props: {
    photofilepath: String,
  },
  mounted() {
    console.log("asdfsd");
    console.log("photofile ", this.photofilepath);
    // const token = this.$cookies.get("PID_AUTH");
    // const decoded = jwt_decode(token);
    // const index = decoded.index;
    // this.userindex = index;
    // axios
    //   .get(`https://i5d206.p.ssafy.io:8443/poi/${index}`, {
    //     headers: { Authorization: token },
    //   })
    //   .then((res) => {
    //     this.ruleForm.Introduction = res.data.ind_introduce;
    //     this.ruleForm.photo = "/file/" + res.data.ind_photo;
    //   })
    //   .catch((err) => {
    //     console.log("token error");
    //     console.log(err.response);
    //     if (err.response == 401) {
    //       this.$message.error("로그인세션이 만료되었습니다");
    //       localStorage.clear();
    //       this.$router.push("/");
    //     }
    //   });
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.userindex = index;
  },
  data() {
    console.log("userindex : ", this.userindex);
    return {
      loading: false,
      userindex: "",
      ruleForm: {
        photo: "https://i5d206.p.ssafy.io" + this.photofilepath,
        Introduction: "",
      },
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
    changept() {
      let photoip = document.getElementById("photo");
      let imgtag = document.getElementById("profilephoto");
      imgtag.src = URL.createObjectURL(photoip.files[0]);
      imgtag.onload = function() {
        URL.revokeObjectURL(imgtag.src);
      };
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.openFullScreen2();
          console.log(this.$refs[formName]);

          // 저장하는 방법 찾아보기
          var frm = new FormData();
          var photodata = this.$refs.photo.files[0];
          var introduce = document.getElementById("introtextarea");
          console.log(introduce.value);
          console.log("userindex : ", this.userindex);
          // var introducedata = this.$refs[]
          frm.append("upfile", photodata);
          // frm.append("introduce", introduce.value);

          axios
            .post("https://i5d206.p.ssafy.io:8443/poi/photo", frm, {
              headers: { Authorization: this.token },
              params: {
                index: this.userindex,
                introduce: introduce.value,
              },
            })
            .then((res) => {
              console.log(res);
              <el-alert title="업로드 되었습니다" type="success"></el-alert>;
              this.loading = true;
              setTimeout(() => {
                this.loading = false;
                this.successmessage();
              }, 2000);
            })
            .catch((err) => {
              console.log("token error");
              console.log(err.response);
              if (err.response == 401) {
                this.$message.error("로그인세션이 만료되었습니다");
                localStorage.clear();
                this.$router.push("/");
              }
            });
        } else {
          console.log("error submit!!");
          this.failed();
          return false;
        }
      });
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

<style>
.box {
  width: 150px;
  height: 150px;
  /* border-radius: 70%; */
  overflow: hidden;

  margin: 30px;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
