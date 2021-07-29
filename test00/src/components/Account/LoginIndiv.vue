<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    style="text-align:center"
  >
    <!-- 개인회원 ID -->
    <el-form-item label="ID" prop="LoginIndivID">
      <el-input v-model="ruleForm.LoginIndivID"></el-input>
    </el-form-item>
    <!-- 개인회원 PW -->
    <el-form-item label="Password" prop="LoginIndivPW">
      <el-input type="password" v-model="ruleForm.LoginIndivPW"></el-input>
    </el-form-item>

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
// import axios from "axios";
// import axios from 'axios'
export default {
  components: {},
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
            message: "아이디를 입력해주세요",
            trigger: "blur",
          },
        ],
        LoginIndivPW: [
          {
            required: true,
            message: "비밀번호를 입력해주세요",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    // 로그인
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          this.openFullScreen2();
          // axios.post 로그인구현
          this.$store
            .dispatch("requestLogin", {
              ind_id: this.ruleForm.LoginIndivID,
              ind_password: this.ruleForm.LoginIndivPW,
            })
            .then(function(result) {
              this.$router.push('user');
              alert("accessToken: " + result.data.accessToken);
            })
            .catch(function(err) {
              alert(err);
            });
          //
          this.$store.state.LoginDialog = false;
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },

    // 폼초기화
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    // 스크린로딩
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
