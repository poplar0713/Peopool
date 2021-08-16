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
          :rules="rules"
          v-on:submit.prevent
          enctype="multipart/form-data"
          ref="ruleForm"
        >
          <el-form-item label="" prop="photointro">
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
          <el-form-item label="">
            <el-input
              id="introtextarea"
              type="textarea"
              v-model="userintroduce"
              maxlength="1000"
              show-word-limit
              autosize="true"
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
  },
  created() {
    console.log("this.introduce created- ", this.introduce);
    console.log("this.path created- ", this.photofilepath);
    this.userintroduce = this.introduce;
  },

  data() {
    return {
      loading: false,
      userindex: "",
      userintroduce: this.introduce,
      changeprofile: false,
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
      this.changeprofile = true;
      let photoip = document.getElementById("photo");
      let imgtag = document.getElementById("profilephoto");
      imgtag.src = URL.createObjectURL(photoip.files[0]);
      imgtag.onload = function() {
        URL.revokeObjectURL(imgtag.src);
      };
    },
    submitForm() {
      if (!this.curphoto && !this.changeprofile) {
        this.$message.error("사진을 업로드 해주세요.");
        return;
      }
      // 저장하는 방법 찾아보기
      if (this.changeprofile) {
        var frm = new FormData();
        var photodata = this.$refs.photo.files[0];

        console.log("userindex : ", this.userindex);
        // var introducedata = this.$refs[]
        frm.append("upfile", photodata);
        // frm.append("introduce", introduce.value);

        axios
          .post("https://i5d206.p.ssafy.io:8443/poi/photo", frm, {
            headers: { Authorization: this.token },
            params: {
              index: this.userindex,
            },
          })
          .then((res) => {
            console.log(res);
            this.$emit("uploadintro");
          })
          .catch((err) => {
            this.failed();
            console.log("token error");
            console.log(err.response);
            if (err.response == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }

      axios
        .put("https://i5d206.p.ssafy.io:8443/poi/intro", {
          headers: { Authorization: this.token },
          params: {
            ind_index: this.userindex,
            ind_introduce: this.userintroduce,
          },
        })
        .then((res) => {
          console.log(res);
          <el-alert title="업로드 되었습니다" type="success"></el-alert>;
          // this.$emit("uploadintro");
          // this.openFullScreen2();
        })
        .catch((err) => {
          this.failed();
          console.log("token error");
          console.log(err.response);
          if (err.response == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            localStorage.clear();
            this.$router.push("/");
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

<style scoped>
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
