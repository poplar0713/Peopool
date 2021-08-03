<template>
  <el-scrollbar>
    <div
      style="width:100%;"
      v-loading="loading"
      element-loading-text="Loading..."
      element-loading-spinner="el-icon-loading"
      element-loading-background="rgba(0, 0, 0, 0.8)"
    >
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
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
import axios from "axios";
import jwt_decode from "jwt-decode";
export default {
  mounted() {
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.userindex = index;
    axios
      .get(`poi/${index}`)
      .then((res) => (this.ruleForm.Introduction = res.data.ind_introduce))
      .catch((err) => console.log(err));
  },
  data() {
    return {
      loading: false,
      userindex: "",
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
          axios
            .put("/poi", {
              ind_index: this.userindex,
              ind_introduce: this.ruleForm.Introduction,
              ind_photo: "string",
              ind_resume: "string",
              ind_switch: "string",
              ind_video: "string",
            })
            .then((res) => {
              console.log(res);
              this.loading = true;
              setTimeout(() => {
                this.loading = false;
                this.successmessage();
              }, 3000);
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
        message: "수정완료 되었습니다",
        type: "success",
      });
    },
    failed() {
      this.$message.error("양식을 확인해주세요");
    },
  },
};
</script>

<style></style>