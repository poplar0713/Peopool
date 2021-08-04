<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    style="text-align:center"
  >
    <!-- 기업회원이름 -->
    <el-form-item label="회사명" prop="companyname">
      <el-input v-model="ruleForm.companyname"></el-input>
    </el-form-item>
    <!-- 기업회원 이메일 -->
    <el-form-item label="이메일" prop="companyemail">
      <el-input type="email" v-model="ruleForm.companyemail"></el-input>
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
  name: "FindCompanyId",
  components: {},
  data() {
    return {
      loading: true,
      fullscreenLoading: false,
      foundId: "",
      ruleForm: {
        companyname: "",
        companyemail: "",
      },
      rules: {
        companyname: [
          {
            required: true,
            message: "회사명을 입력해주세요",
            trigger: "blur",
          },
        ],
        companyemail: [
          {
            required: true,
            message: "이메일을 입력해주세요",
            trigger: "blur",
          },
          {
            type: "email",
            message: "이메일형식을 바르게 입력해주세요",
            trigger: "change",
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
            .get("https://i5d206.p.ssafy.io:8443/ent/findid", {
              params: {
                name: this.ruleForm.companyname,
                email: this.ruleForm.companyemail,
              },
            })
            .then((result) => {
              this.foundId = result.data.ent_id;
              setTimeout(() => {
                this.$notify({
                  title: "아이디 찾기 결과",
                  message: this.foundId,
                });
              }, 3000);
            })
            .catch((err) => {
              console.log(err);
            });
          //
          this.$store.state.findCompanyId = false;
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
