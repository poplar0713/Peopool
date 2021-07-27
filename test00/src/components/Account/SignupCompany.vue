<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
    <!-- 기업명 -->
    <el-form-item label="Company Name" prop="ent_name">
      <el-input v-model="ruleForm.ent_name"></el-input>
    </el-form-item>
    <!-- 기업회원 ID -->
    <el-form-item label="ID" prop="ent_id">
      <el-input v-model="ruleForm.ent_id"></el-input>
    </el-form-item>
    <!-- 기업회원 PW -->
    <el-form-item label="Password" prop="ent_password">
      <el-input type="password" v-model="ruleForm.ent_password"></el-input>
    </el-form-item>
    <!-- 기업회원 PW 확인 -->
    <el-form-item label="Password Confirmation" prop="ent_password_cf">
      <el-input
        type="password"
        v-model="ruleForm.ent_password_cf"
        autocomplete="off"
      ></el-input>
    </el-form-item>
    <!-- 연락처 -->
    <el-form-item label="Tel" prop="ent_contact">
      <el-input type="tel" v-model="ruleForm.ent_contact"></el-input>
    </el-form-item>
    <!-- 이메일 -->
    <el-form-item label="Email" prop="ent_email">
      <el-input type="email" v-model="ruleForm.ent_email"></el-input>
    </el-form-item>
    <!-- 생성 및 취소 버튼 -->
    <el-form-item>
      <el-button @click="resetForm('ruleForm')">Reset</el-button>
      <el-button
        type="warning"
        @click="submitForm('ruleForm')"
        v-loading.fullscreen.lock="fullscreenLoading"
        >Create</el-button
      >
    </el-form-item>
  </el-form>
</template>

<script>
import axios from "axios";
export default {
  data() {
    // 비밀번호 체크
    const checkPWCF = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("비밀번호(확인)를 다시 입력해주세요"));
      } else if (value !== this.ruleForm.ent_password) {
        callback(new Error("비밀번호를 확인해주세요"));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        ent_name: "",
        ent_id: "",
        ent_password: "",
        ent_password_cf: "",
        ent_contact: "",
        ent_email: "",
        open: false,
      },
      rules: {
        ent_name: [
          {
            required: true,
            message: "회사명을 입력해주세요",
            trigger: "blur",
          },
        ],
        ent_id: [
          {
            required: true,
            message: "사용하실 아이디를 입력해주세요",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "Length should be 5 to 10",
            trigger: "blur",
          },
        ],
        ent_password: [
          {
            required: true,
            message: "비밀번호를 입력해주세요",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "5-10자리로 설정해주세요",
            trigger: "blur",
          },
        ],
        ent_password_cf: [
          {
            required: true,
            message: "비밀번호를 확인해주세요",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "5-10자리로 설정해주세요",
            trigger: "blur",
          },
          { validator: checkPWCF, trigger: "blur" },
        ],
        ent_contact: [
          {
            required: true,
            message: "연락처를 입력해주세요",
            trigger: "change",
          },
        ],
        ent_email: [
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
          this.openFullScreen2();
          this.$store.state.SignupDialogCompany = false;
          // 데이터정보 보내기
          axios
            .post("https://localhost:8443/ent", {
              ent_id: this.ruleForm.ent_id,
              ent_password: this.ruleForm.ent_password,
              ent_name: this.ruleForm.ent_name,
              ent_contact: this.ruleForm.ent_contact,
              ent_email: this.ruleForm.ent_email,
            })
            .then((res) => {
              if (res.status == 200) {
                this.$store.state.SignupDialogCompany = false;
                this.successmessage();
                console.log(this.ruleForm);
              }
            })
            .catch((err) => {
              console.log(err);
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
      }, 3000);
    },
    successmessage() {
      this.$message({
        message: "회원가입(기업용)이 완료되었습니다",
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
