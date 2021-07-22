<template>
  <el-scrollbar height="400px">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <!-- 학교이름 -->
      <el-form-item label="" prop="Introduction">
        <el-input type="textarea" v-model="Introduction" :rows="8"></el-input>
      </el-form-item>
    </el-form>
  </el-scrollbar>
</template>

<script>
export default {
  data() {
    return {
      Introduction: "",
      fullscreenLoading: false,
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          this.$store.dispatch("getsignupdata", this.ruleForm);
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
