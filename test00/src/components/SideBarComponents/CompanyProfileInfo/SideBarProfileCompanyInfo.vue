<template>
  <div
    style="width:80%;"
    v-loading="loading"
    element-loading-text="Loading..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
  >
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <!-- 기업이미지 -->
      <el-form-item label="Image" prop="ent_image">
        <div
          v-if="ruleForm.ent_image == ''"
          class="box"
          style="background: #BDBDBD;"
        >
          <img
            class="cprofile"
            id="cprofilephoto"
            src="https://i5d206.p.ssafy.io/file/thumbuser.png"
            height="150"
            width="150"
          />
        </div>
        <div v-else class="box" style="background: #BDBDBD;">
          <img
            class="cprofile"
            id="cprofilephoto"
            v-if="ruleForm.ent_image"
            :src="ruleForm.ent_image"
            height="150"
            width="150"
          />
        </div>
        <!-- {{ this.ruleForm.ent_image }}<br /> -->

        <span class="filetype">
          <span class="file-text"></span>
          <span class="file-btn">찾아보기</span>
          <span class="file-select"
            ><input
              type="file"
              class="input-file"
              size="1"
              @change="changept(this)"
              id="companyprofile"
              ref="companyprofile"
              multiple="multiple"
              accept="image/jpeg, image/jpg, image/png"
          /></span>
        </span>
        <!-- <input
          type="file"
          id="companyprofile"
          ref="companyprofile"
          @change="changept(this)"
          accept="image/jpeg, image/jpg, image/png"
          multiple="multiple"
        /> -->
      </el-form-item>
      <!-- 기업대표 -->
      <el-form-item label="CEO" prop="ent_ceo">
        <el-input v-model="ruleForm.ent_ceo"></el-input>
      </el-form-item>
      <!-- 기업역사 -->
      <el-form-item label="창립" prop="ent_history">
        <el-input v-model="ruleForm.ent_history"></el-input>
      </el-form-item>
      <!-- 기업주소 -->
      <el-form-item label="주소" prop="ent_address">
        <el-input v-model="ruleForm.ent_address"></el-input>
      </el-form-item>
      <!-- 기업웹사이트 -->
      <el-form-item label="사이트" prop="ent_website">
        <el-input v-model="ruleForm.ent_website"></el-input>
      </el-form-item>

      <el-form-item label="소개" prop="ent_introduce">
        <el-input v-model="ruleForm.ent_introduce"></el-input>
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
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  name: "SideBarProfileCompanyInfo",
  components: {},
  mounted() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.userindex = index;
    // 기업정보 가져오기
    axios
      .get(`https://i5d206.p.ssafy.io:8443/poe/path/${index}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        let result = res.data[0];
        console.log("company info res- ", res);
        console.log("company info - ", result);
        this.ruleForm.ent_index = result.ent_index;
        this.ruleForm.ent_image =
          "/file/" + result.image_savefolder + "/" + result.image_savefile;
        this.ruleForm.ent_ceo = result.ent_ceo;
        this.ruleForm.ent_history = result.ent_history;
        this.ruleForm.ent_address = result.ent_address;
        this.ruleForm.ent_website = result.ent_website;
        this.ruleForm.ent_image_pk = result.ent_image;
        this.ruleForm.ent_introduce = result.ent_introduce;

        console.log("axios ent_index: ", this.ruleForm.ent_image_pk);
      })
      .catch((err) => {
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
  data() {
    // 비밀번호확인 체크(비어있거나 비밀번호랑 다르면)
    const checkPWCF = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("비밀번호(확인)를 입력해주세요"));
      } else if (value !== this.ruleForm.Password) {
        callback(new Error("비밀번호를 확인해주세요"));
      } else {
        callback();
      }
    };
    return {
      loading: false,
      userindex: "",
      changeprofile: false,
      ruleForm: {
        ent_index: "",
        ent_image: "",
        ent_ceo: "",
        ent_history: "",
        ent_address: "",
        ent_website: "",
        Password: "",
        PasswordConfirm: "",
        ent_image_pk: 0,
        ent_introduce: "",
      },
      rules: {
        ent_ceo: [
          {
            required: true,
            message: "기업의 대표를 입력해주세요",
            trigger: "blur",
          },
        ],
        ent_history: [
          {
            required: true,
            message: "기업의 역사를 입력해주세요",
            trigger: "blur",
          },
        ],
        ent_address: [
          {
            required: true,
            message: "기업의 주소를 입력해주세요",
            trigger: "blur",
          },
        ],
        Password: [
          {
            required: true,
            message: "비밀번호를 입력해주세요",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "5-10자리 비밀번호를 입력해주세요",
            trigger: "blur",
          },
        ],
        PasswordConfirm: [
          {
            required: true,
            message: "비밀번호(확인)를 확인해주세요",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "5-10자리 비밀번호를 입력해주세요",
            trigger: "blur",
          },
          { validator: checkPWCF, trigger: "blur" },
        ],
        ent_introduce: [
          {
            required: true,
            message: "기업소개를 확인해주세요",
            trigger: "blur",
          },
        ],
      },
      fullscreenLoading: false,
    };
  },
  methods: {
    changept() {
      let photoip = document.getElementById("companyprofile");
      let imgtag = document.getElementById("cprofilephoto");
      imgtag.src = URL.createObjectURL(photoip.files[0]);
      this.changeprofile = true;
      imgtag.onload = function() {
        URL.revokeObjectURL(imgtag.src);
      };
      document.getElementsByClassName("file-text")[0].innerHTML = "";
      let filedata = photoip.files[0].name;
      this.filename = filedata;
      document.getElementsByClassName("file-text")[0].innerHTML = filedata;
      console.log(filedata);
    },
    submitForm(formName) {
      console.log("this ent_index - ", this.ruleForm.ent_image_pk);
      console.log("this boolean ent_indx", !this.ruleForm.ent_image_pk);
      if (!this.ruleForm.ent_image_pk && !this.changeprofile) {
        this.failedprofile();
        return;
      }

      if (this.changeprofile) {
        var frm = new FormData();
        var photodata = this.$refs.companyprofile.files[0];
        frm.append("upfile", photodata);
        axios
          .post(
            `https://i5d206.p.ssafy.io:8443/poe/photo/${this.userindex}`,
            frm,
            {
              headers: { Authorization: this.token },
            }
          )
          .then((res) => {
            console.log("cphoto: ", res);
            document.getElementsByClassName("file-text")[0].innerHTML = "";
          })
          .catch((err) => {
            console.log("cerr: ", err);
          });
      }
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 기업정보수정
          axios
            .put("https://i5d206.p.ssafy.io:8443/poe", {
              headers: { Authorization: this.token },
              ent_image: this.ruleForm.ent_image_pk,
              ent_index: this.ruleForm.ent_index,
              ent_ceo: this.ruleForm.ent_ceo,
              ent_history: this.ruleForm.ent_history,
              ent_address: this.ruleForm.ent_address,
              ent_website: this.ruleForm.ent_website,
              ent_introduce: this.ruleForm.ent_introduce,
            })
            .then((res) => {
              console.log(res);
              this.loading = true;
              setTimeout(() => {
                this.loading = false;
                this.successmessage();
                this.ruleForm.Password = "";
                this.ruleForm.PasswordConfirm = "";
              }, 2000);
            })
            .catch((err) => {
              if (err.response == 401) {
                this.$message.error("로그인세션이 만료되었습니다");
                this.$cookies.remove("PID_AUTH");
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
    // save 성공
    successmessage() {
      this.$message({
        message: "수정완료 되었습니다",
        type: "success",
      });
    },
    failedprofile() {
      this.$message.error("기업 이미지를 선택해 주세요");
    },
    // 양식 다 안채우고 save눌렀을 때
    failed() {
      this.$message.error("프로필양식을 확인해주세요");
    },
  },
};
</script>

<style scoped>
/* .box {
  width: 150px;
  height: 150px; */
/* border-radius: 70%; */
/* overflow: hidden;

  margin: 30px;
} */
/* .cprofile {
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
