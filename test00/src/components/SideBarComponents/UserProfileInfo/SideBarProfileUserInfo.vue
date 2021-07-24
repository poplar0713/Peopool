<template>
  <div style="width:100%;">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <!-- 개인회원이름 -->
      <el-form-item label="Name" prop="UserName">
        <el-input v-model="ruleForm.UserName"></el-input>
      </el-form-item>
      <!-- 개인회원 생년월일 -->
      <el-form-item label="Birth" prop="UserBirth">
        <el-input type="date" v-model="ruleForm.UserBirth"></el-input>
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
export default {
  data() {
    return {
      ruleForm: {
        SignupIndivPW: "",
        SignupIndivPWConfirm: "",
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
            message: "Please input your Name",
            trigger: "blur",
          },
        ],
        UserBirth: [
          {
            required: true,
            message: "Please input your Birth",
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
        UserTel: [
          {
            required: true,
            message: "Please input your Phone number",
            trigger: "change",
          },
        ],
        UserEmail: [
          {
            required: true,
            message: "Please input your Email",
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
