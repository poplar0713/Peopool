<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    style="text-align:center"
  >
    <!-- 개인회원 이름 -->
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
  name: "FindUserId",
  components: {},
  data() {
    return {
      loading: true,
      fullscreenLoading: false,
      foundId: "",
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
            .get("https://i5d206.p.ssafy.io:8443/ind/findid", {
              params: {
                name: this.ruleForm.username,
                phone: this.ruleForm.userphone,
              },
              headers: { Authorization: this.$store.state.usertoken },
            })
            .then((result) => {
              this.foundId = result.data.ind_id;
              setTimeout(() => {
                this.$notify({
                  title: "아이디 찾기 결과",
                  message: this.foundId,
                });
              }, 3000);
            })
            .catch((err) => {
              if (err.response == 401) {
                this.$message.error("로그인세션이 만료되었습니다");
                this.$cookies.remove("PID_AUTH");
                localStorage.clear();
                this.$router.push("/");
              }
            });
          //
          this.$store.state.findUserId = false;
        } else {
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
