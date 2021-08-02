<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    style="text-align:center"
  >
    <!-- 개인회원 ID -->
    <el-form-item label="ID" prop="LoginCompanyID">
      <el-input v-model="ruleForm.LoginCompanyID"></el-input>
    </el-form-item>
    <!-- 개인회원 PW -->
    <el-form-item label="Password" prop="LoginCompanyPW">
      <el-input type="password" v-model="ruleForm.LoginCompanyPW"></el-input>
    </el-form-item>
    <!-- 생성 및 취소 버튼 -->
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
export default {
  data() {
    return {
      ruleForm: {
        LoginCompanyID: "",
        LoginCompanyPW: "",
      },
      rules: {
        LoginCompanyID: [
          {
            required: true,
            message: "아이디를 입력해주세요",
            trigger: "blur",
          },
        ],
        LoginCompanyPW: [
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
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert("submit!");
          this.Loging();
          this.$store
            .dispatch("requestLoginent", {
              ent_id: this.ruleForm.LoginCompanyID,
              ent_password: this.ruleForm.LoginCompanyPW,
            })
            .then((result) => {
              localStorage.setItem("token", result.data.accessToken);
              this.$store.state.type = "1";
              setTimeout(() => {
                this.$router.push("company");
              }, 3000);
            })
            .catch((err) => {
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
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    Loging() {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
        this.$store.state.LoginDialog = false;

        this.Hello();
      }, 3000);
    },
  },
  Hello() {
    this.$message({
      showClose: true,
      message: "Hello, Nojobman",
      type: "success",
    });
  },
};
</script>

<style></style>
