<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
    <!-- 기업명 -->
    <el-form-item label="Company Name" prop="CompanyName">
      <el-input v-model="ruleForm.CompanyName"></el-input>
    </el-form-item>
    <!-- 기업회원 ID -->
    <el-form-item label="ID" prop="SignupCompanyID">
      <el-input v-model="ruleForm.SignupCompanyID"></el-input>
    </el-form-item>
    <!-- 개인회원 PW -->
    <el-form-item label="Password" prop="SignupCompanyPW">
      <el-input type="password" v-model="ruleForm.SignupCompanyPW"></el-input>
    </el-form-item>
    <!-- 개인회원 PW 확인 -->
    <el-form-item label="Password Confirmation" prop="SignupCompanyPWConfirm">
      <el-input
        type="password"
        v-model="ruleForm.SignupCompanyPWConfirm"
      ></el-input>
    </el-form-item>
    <!-- 공개여부 -->
    <el-form-item label="Open to the public" prop="open">
      <el-switch v-model="ruleForm.open"></el-switch>
    </el-form-item>
    <!-- 생성 및 취소 버튼 -->
    <el-form-item>
      <el-button @click="resetForm('ruleForm')">Reset</el-button>
      <el-button type="warning" @click="submitForm('ruleForm')"
        >Create</el-button
      >
    </el-form-item>
  </el-form>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        CompanyName: "",
        SignupCompanyID: "",
        SignupCompanyPW: "",
        SignupCompanyPWConfirm: "",
        open: false,
      },
      rules: {
        CompanyName: [
          {
            required: true,
            message: "Please input Company Name",
            trigger: "blur",
          },
          // { min: 5, max: 10, message: 'Length should be 5 to 10', trigger: 'blur' }
        ],
        SignupCompanyID: [
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
        SignupCompanyPW: [
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
        SignupCompanyPWConfirm: [
          { required: true, message: "Please check Password", trigger: "blur" },
          {
            min: 10,
            max: 15,
            message: "Length should be 10 to 15",
            trigger: "blur",
          },
          {
            required: "this.SignupCompanyPW==this.SignupCompanyPWConfirm",
            message: "Please Check your PW or PW confirmation",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          this.$store.state.SignupDialogIndiv = false;
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
  },
};
</script>

<style></style>
