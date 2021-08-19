<template>
  <div style="width:80%">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <!-- 학력 -->
      <el-form-item label="School" prop="School">
        <el-radio-group v-model="ruleForm.school">
          <el-radio label="초등학교"></el-radio>
          <el-radio label="중학교"></el-radio>
          <el-radio label="고등학교"></el-radio>
          <el-radio label="대학교"></el-radio>
        </el-radio-group>
      </el-form-item>
      <!-- 학교이름 -->
      <el-form-item label="School Name" prop="SchoolName">
        <el-input v-model="ruleForm.SchoolName"></el-input>
      </el-form-item>
      <!-- 졸업 -->
      <el-form-item label="Graduation" prop="Graduation">
        <el-input type="date" v-model="ruleForm.Graduation"></el-input>
      </el-form-item>
      <!-- 공개여부 -->
      <el-form-item label="Open to the public" prop="open">
        <el-switch v-model="ruleForm.open"></el-switch>
      </el-form-item>
    </el-form>
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
  </div>
</template>

<script>
export default {
  data() {
    return {
      ruleForm: {
        SchoolName: "",
        Graduation: "",
        open: false,
        School: "1",
      },
      rules: {
        SchoolName: [
          {
            required: true,
            message: "Please input your SchoolName",
            trigger: "blur",
          },
        ],
        Graduation: [
          {
            required: true,
            message: "Please input your Graduation date",
            trigger: "blur",
          },
        ],
        School: [
          {
            required: true,
            message: "Please select your School",
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
          this.$store.dispatch("getsignupdata", this.ruleForm);
          this.openFullScreen2();
          this.$store.state.SignupDialogIndiv = false;
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
      this.$message.error("Oops, check your Information");
    },
  },
};
</script>

<style></style>
