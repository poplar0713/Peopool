<template>
  <div
    style="width:100%;"
    v-loading="loading"
    element-loading-text="Loading..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
  >
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <!-- 기업이미지 -->
      <el-form-item label="Image" prop="ent_image">
        {{ this.ruleForm.ent_image }}<br />
        <el-input type="" v-model="ruleForm.ent_image"></el-input>
      </el-form-item>
      <!-- 기업대표 -->
      <el-form-item label="CEO" prop="ent_ceo">
        <el-input v-model="ruleForm.ent_ceo"></el-input>
      </el-form-item>
      <!-- 기업역사 -->
      <el-form-item label="History" prop="ent_history">
        <el-input v-model="ruleForm.ent_history"></el-input>
      </el-form-item>
      <!-- 기업주소 -->
      <el-form-item label="Address" prop="ent_address">
        <el-input v-model="ruleForm.ent_address"></el-input>
      </el-form-item>
      <!-- 기업웹사이트 -->
      <el-form-item label="WebSite" prop="ent_website">
        <el-input v-model="ruleForm.ent_website"></el-input>
      </el-form-item>
      <!-- 기업회원 PW -->
      <!-- <el-form-item label="Password" prop="Password">
        <el-input type="password" v-model="ruleForm.Password"></el-input>
      </el-form-item> -->
      <!-- 기업회원 PW 확인 -->
      <!-- <el-form-item label="Password Confirmation" prop="PasswordConfirm">
        <el-input type="password" v-model="ruleForm.PasswordConfirm"></el-input>
      </el-form-item> -->
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
  components: {},
  mounted() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 기업정보 가져오기
    axios
      .get(`https://i5d206.p.ssafy.io:8443/poe/index/${index}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.ruleForm.ent_index = res.data.ent_index;
        this.ruleForm.ent_image = res.data.ent_image;
        this.ruleForm.ent_ceo = res.data.ent_ceo;
        this.ruleForm.ent_history = res.data.ent_history;
        this.ruleForm.ent_address = res.data.ent_address;
        this.ruleForm.ent_website = res.data.ent_website;
      })
      .catch((err) => {
        console.log("token error");
        console.log(err.response.data.status);
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
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
      ruleForm: {
        ent_index: "",
        ent_image: "",
        ent_ceo: "",
        ent_history: "",
        ent_address: "",
        ent_website: "",
        Password: "",
        PasswordConfirm: "",
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
      },
      fullscreenLoading: false,
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 기업정보수정
          axios
            .put("https://i5d206.p.ssafy.io:8443/poe", {
              headers: { Authorization: this.token },
              ent_image: this.ruleForm.ent_image,
              ent_index: this.ruleForm.ent_index,
              ent_ceo: this.ruleForm.ent_ceo,
              ent_history: this.ruleForm.ent_history,
              ent_address: this.ruleForm.ent_address,
              ent_website: this.ruleForm.ent_website,
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
              console.log("token error");
              console.log(err.response.data.status);
              if (err.response.data.status == 401) {
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
    // save 성공
    successmessage() {
      this.$message({
        message: "수정완료 되었습니다",
        type: "success",
      });
    },
    // 양식 다 안채우고 save눌렀을 때
    failed() {
      this.$message.error("프로필양식을 확인해주세요");
    },
  },
};
</script>

<style></style>
