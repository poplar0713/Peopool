<template>
  <div
    style="width: 70%;"
    v-loading="loading"
    element-loading-text="Loading..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
  >
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <!-- 기업이미지 -->
      <el-form-item label="이미지" prop="ent_image">
        <!-- <div
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
        </div> -->
        <el-upload
          class="avatar-uploader"
          :auto-upload="false"
          :show-file-list="false"
          :on-change="handleAvatarSuccess"
        >
          <img
            v-if="ruleForm.ent_image"
            :src="ruleForm.ent_image"
            class="avatar"
          />
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
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
        <el-input
          id="introtextarea"
          type="textarea"
          v-model="ruleForm.ent_introduce"
          maxlength="1000"
          show-word-limit
          :autosize="{ minRows: 6, maxRows: 7 }"
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
          "https://i5d206.p.ssafy.io/file/" +
          result.image_savefolder +
          "/" +
          result.image_savefile;

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
      phtext:
        "자기소개를 입력해주세요" +
        "\n" +
        "안녕하세요 저희 회사는 000입니다." +
        "\n" +
        "다음과 같은 사람을 찾습니다!" +
        "\n" +
        " #1 " +
        "\n" +
        "...",
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
    handleAvatarSuccess(res, file) {
      console.log("handle-", file);
      if (file != null) {
        let docinput = document.getElementsByClassName("el-upload__input")[0];
        let img = docinput.files[0];
        console.log("img-", img);
        const isJPEG = img.type === "image/jpeg";
        const isPNG = img.type === "image/png";
        const isJPG = img.type === "image/jpg";

        const isLt2M = img.size / 1024 / 1024 < 2;

        if (!isJPG && !isPNG && !isJPEG) {
          this.$message.error("JPG, JPEG, PNG, JFIF 형식만 올려주세요.");
          return;
        }
        if (!isLt2M) {
          this.$message.error("2MB 이하의 사진이 필요해요.");
          return;
        }

        this.changeprofile = true;
        this.ruleForm.ent_image = URL.createObjectURL(img);
      }
    },

    submitForm(formName) {
      if (this.ruleForm.ent_image_pk == 1 && !this.changeprofile) {
        this.failedprofile();
        return;
      }

      if (this.changeprofile) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            let docinput = document.getElementsByClassName(
              "el-upload__input"
            )[0];
            var frm = new FormData();
            var photodata = docinput.files[0];
            frm.append("upfile", photodata);
            axios
              .post(
                `https://i5d206.p.ssafy.io:8443/poe/photo/${this.userindex}`,
                frm,
                {
                  headers: { Authorization: this.$store.state.usertoken },
                }
              )
              .then((res) => {
                if (res.status == 200) {
                  this.successmessage();
                }
              })
              .catch((err) => {
                console.log("err: ", err);
              });
            axios
              .put("https://i5d206.p.ssafy.io:8443/poe", {
                headers: { Authorization: this.$store.state.usertoken },
                ent_image: this.ruleForm.ent_image_pk,
                ent_index: this.ruleForm.ent_index,
                ent_ceo: this.ruleForm.ent_ceo,
                ent_history: this.ruleForm.ent_history,
                ent_address: this.ruleForm.ent_address,
                ent_website: this.ruleForm.ent_website,
                ent_introduce: this.ruleForm.ent_introduce,
              })
              .then((res) => {
                if (res.status == 200) {
                  console.log("OK");
                }
              })
              .catch((err) => {
                if (err.response == 401) {
                  this.$message.error("로그인세션이 만료되었습니다");
                  this.$cookies.remove("PID_AUTH");
                  localStorage.clear();
                  this.$router.push("/");
                }
              });
          }
        });
      } else {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            axios
              .put("https://i5d206.p.ssafy.io:8443/poe", {
                headers: { Authorization: this.$store.state.usertoken },
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
                this.successmessage();
              })
              .catch((err) => {
                if (err.response == 401) {
                  this.$message.error("로그인세션이 만료되었습니다");
                  this.$cookies.remove("PID_AUTH");
                  localStorage.clear();
                  this.$router.push("/");
                }
              });
          }
        });
      }

      // 기업정보수정
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
  height: 178px;
  display: block;
  margin: 10px;
  /* object-fit: cover; */
}
</style>
