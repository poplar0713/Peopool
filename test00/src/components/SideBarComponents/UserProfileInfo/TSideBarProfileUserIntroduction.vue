<template>
  <el-scrollbar>
    <div style="width:100%;">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
        <!-- 개인회원이름 -->
        <el-form-item label="" prop="Introduction">
          <el-input type="textarea" v-model="ruleForm.Introduction"></el-input>
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
  </el-scrollbar>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        Introduction: "",
      },
      rules: {
        Introduction: [
          {
            required: true,
            message: "Please input your Introduction",
            trigger: "blur",
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
          // 저장하는 방법 찾아보기
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
        this.successmessage();
      }, 3000);
    },
    successmessage() {
      this.$message({
        message: "Success",
        type: "success",
      });
    },
    failed() {
      this.$message.error("Oops, Input your Introduction");
    },
  },
};
</script>

<style></style>
