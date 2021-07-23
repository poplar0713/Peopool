<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
    <!-- 개인회원 ID -->
    <el-form-item label="ID" prop="LoginIndivID">
      <el-input v-model="ruleForm.LoginIndivID"></el-input>
    </el-form-item>
    <!-- 개인회원 PW -->
    <el-form-item label="Password" prop="LoginIndivPW">
      <el-input type="password" v-model="ruleForm.LoginIndivPW"></el-input>
    </el-form-item>
    <div><KakaoLogin/></div>
    <el-form-item>
      <el-button @click="resetForm('ruleForm')">Reset</el-button>
      <el-button
        type="warning"
        @click="submitForm('ruleForm')"
        v-loading.fullscreen.lock="fullscreenLoading"
        >Login</el-button
      >
    </el-form-item>
  </el-form>
</template>

<script>
import KakaoLogin from './KakaoLogin.vue';
export default {
  components: { KakaoLogin },
  data() {
    return {
      loading: true,
      fullscreenLoading: false,
      ruleForm: {
        LoginIndivID: "",
        LoginIndivPW: "",
      },
      rules: {
        LoginIndivID: [
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
        LoginIndivPW: [
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
      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          this.openFullScreen2();
          this.$router.push("/user/"+this.ruleForm.LoginIndivID);
          this.$store.state.LoginDialog = false;
          
        } else {
          console.log("error submit!!");
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
  },
};
</script>

<style></style>
