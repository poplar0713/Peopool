<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    style="text-align:center"
  >
    <!-- 개인회원 ID -->
    <el-form-item label="이름" prop="username">
      <el-input v-model="ruleForm.username"></el-input>
    </el-form-item>
    <!-- 개인회원 PW -->
    <el-form-item label="연락처" prop="userphone">
      <el-input type="password" v-model="ruleForm.userphone"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button @click="resetForm('ruleForm')">Reset</el-button>
      <el-button
        type="warning"
        @click="submitForm('ruleForm')"
        v-loading.fullscreen.lock="fullscreenLoading"
        >Find</el-button
      >
    </el-form-item>
  </el-form>
</template>

<script>
import axios from "axios";
export default {
  components: {},
  data() {
    return {
      loading: true,
      fullscreenLoading: false,
      ruleForm: {
        username: "",
        userphone: "",
      },
      rules: {
        username: [
          {
            required: true,
            message: "이름을 입력해주세요",
            trigger: "blur",
          },
        ],
        userphone: [
          {
            required: true,
            message: "연락처를 입력해주세요",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    // 찾기
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          this.openFullScreen2();
          // axios.get 아이디찾기
          axios
            .get("/ind/findid", {
              params: {
                name: this.ruleForm.username,
                phone: this.ruleForm.userphone,
              },
            })
            .then((result) => {
              console.log(result)
              setTimeout(() => {
                this.$router.push("user");
              }, 3000);
            })
            .catch((err) => {
              
              alert(err);
            });
          //
          this.$store.state.findUserId = false;
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
