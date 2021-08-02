<template>
  <div style="width:100%;">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <!-- 개인회원이름 -->
      <el-form-item label="Name" prop="UserName">
        <el-input v-model="ruleForm.UserName">{{ this.ruleForm.UserName }}</el-input>
      </el-form-item>
      <!-- 개인회원 생년월일 -->
      <el-form-item label="Birth" prop="UserBirth">
        <el-input v-model="ruleForm.UserBirth" placeholder="YYMMDD형식으로 작성해주세요"></el-input>
      </el-form-item>
      <!-- 개인회원 PW -->
      <el-form-item label="Password" prop="Password">
        <el-input type="password" v-model="ruleForm.Password"></el-input>
      </el-form-item>
      <!-- 개인회원 PW 확인 -->
      <el-form-item label="Password Confirmation" prop="PasswordConfirm">
        <el-input type="password" v-model="ruleForm.PasswordConfirm"></el-input>
      </el-form-item>
      <!-- 공개여부 -->
      <el-form-item label="Open to the public" prop="open">
        <el-switch v-model="ruleForm.open"></el-switch>
      </el-form-item>
      <!-- 성별 -->
      <el-form-item label="Gender" prop="Gender">
        <el-radio-group v-model="ruleForm.Gender">
          <el-radio label="male"></el-radio>
          <el-radio label="female"></el-radio>
        </el-radio-group>
      </el-form-item>
      <!-- 연락처 -->
      <el-form-item label="Tel" prop="UserTel">
        <el-input type="tel" v-model="ruleForm.UserTel"></el-input>
      </el-form-item>
      <!-- 이메일 -->
      <el-form-item label="Email" prop="UserEmail">
        <el-input type="email" v-model="ruleForm.UserEmail"></el-input>
      </el-form-item>
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
      </div>
    </el-form>
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  data() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 회원정보 가져오기
    axios
      .get(`https://localhost:8443/ind/${index}`)
      .then((res) => {
        console.log(res.data.ind_name);
        this.ruleForm.UserName = res.data.ind_name;
        this.ruleForm.UserBirth = res.data.ind_birth;
        this.ruleForm.Gender = res.data.ind_gender;
        this.ruleForm.UserTel = res.data.ind_phone;
        this.ruleForm.UserEmail = res.data.ind_email;
      })
      .catch((err) => {
        console.log(err);
      });
    return {
      ruleForm: {
        Password: "",
        PasswordConfirm: "",
        UserName: "",
        UserBirth: "",
        open: false,
        Gender: "",
        UserTel: "",
        UserEmail: "",
      },
      rules: {
        SignupIndivID: [
          {
            required: true,
            message: "Please input Activity ID",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "Length should be 5 to 10",
            trigger: "blur",
          },
        ],
        SignupIndivPW: [
          {
            required: true,
            message: "Please input Activity Password",
            trigger: "blur",
          },
          {
            min: 10,
            max: 15,
            message: "Length should be 10 to 15",
            trigger: "blur",
          },
        ],
        SignupIndivPWConfirm: [
          { required: true, message: "Please check Password", trigger: "blur" },
          {
            min: 10,
            max: 15,
            message: "Length should be 10 to 15",
            trigger: "blur",
          },
        ],
        UserName: [
          {
            required: true,
            message: "이름을 입력해주세요",
            trigger: "blur",
          },
        ],
        UserBirth: [
          {
            required: true,
            message: "생년월일을 입력해주세요",
            trigger: "blur",
          },
          {
            min: 6,
            max: 6,
            message: "'YYMMDD'형식의 생년월일을 입력해주세요",
            trigger: "blur",
          },
        ],
        Gender: [
          {
            required: true,
            message: "성별을 체크해주세요",
            trigger: "change",
          },
        ],
        UserTel: [
          {
            required: true,
            message: "연락처를 입력해주세요",
            trigger: "change",
          },
        ],
        UserEmail: [
          {
            required: true,
            message: "이메일을 입력해주세요",
            trigger: "change",
          },
          {
            type: "email",
            message: "이메일형식을 바르게 입력해주세요",
            trigger: "change",
          },
        ],
      },
      fullscreenLoading: false,
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          this.openFullScreen2();
          this.$store.state.SignupDialogIndiv = false;
          this.successmessage();
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
      }, 3000);
    },
    successmessage() {
      this.$message({
        message: "Welcome to PeoPool channel",
        type: "success",
      });
    },
    failed() {
      this.$message.error("Oops, check your identification info");
    },
  },
};
</script>

<style></style>
