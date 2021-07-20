<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
    <!-- 개인회원 ID -->
    <el-form-item label="ID" prop="SignupIndivID">
      <el-input v-model="ruleForm.SignupIndivID"></el-input>
    </el-form-item>
    <!-- 개인회원 PW -->
    <el-form-item label="Password" prop="SignupIndivPW">
      <el-input type="password" v-model="ruleForm.SignupIndivPW"></el-input>
    </el-form-item>
    <!-- 개인회원 PW 확인 -->
    <el-form-item label="Password Confirmation" prop="SignupIndivPWConfirm">
      <el-input
        type="password"
        v-model="ruleForm.SignupIndivPWConfirm"
      ></el-input>
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
        SignupIndivID: "",
        SignupIndivPW: "",
        SignupIndivPWConfirm: "",
        open: false,
        Gender: "",
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
        Gender: [
          {
            required: true,
            message: "Please select your Gender",
            trigger: "change",
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
