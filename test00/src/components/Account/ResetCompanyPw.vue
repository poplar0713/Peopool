<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    style="text-align:center"
  >
    <!-- 기업회원 ID -->
    <el-form-item label="ID" prop="companyid">
      <el-input v-model="ruleForm.companyid"></el-input>
    </el-form-item>
    <!-- 기업회원 연락처 -->
    <el-form-item label="E-mail" prop="companyemail">
      <el-input type="email" v-model="ruleForm.companyemail"></el-input>
    </el-form-item>
    <!-- 기업회원 새로운 PW -->
    <el-form-item label="새 비밀번호" prop="newpassword">
      <el-input type="password" v-model="ruleForm.newpassword"></el-input>
    </el-form-item>
    <!-- 기업회원 새로운 PW 확인 -->
    <el-form-item label="새 비밀번호 확인" prop="newpasswordconfirm">
      <el-input
        type="password"
        v-model="ruleForm.newpasswordconfirm"
      ></el-input>
    </el-form-item>

    <el-form-item>
      <el-button @click="resetForm('ruleForm')">입력값 초기화</el-button>
      <el-button
        type="warning"
        @click="submitForm('ruleForm')"
        v-loading.fullscreen.lock="fullscreenLoading"
        >비밀번호 초기화</el-button
      >
    </el-form-item>
  </el-form>
</template>

<script>
import axios from "axios";
export default {
  components: {},
  data() {
    const checkPWCF = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("비밀번호(확인)를 입력해주세요"));
      } else if (value !== this.ruleForm.newpassword) {
        callback(new Error("비밀번호를 확인해주세요"));
      } else {
        callback();
      }
    };
    return {
      loading: true,
      fullscreenLoading: false,
      ruleForm: {
        companyid: "",
        companyemail: "",
        newpassword: "",
        newpasswordconfirm: "",
      },
      rules: {
        companyid: [
          {
            required: true,
            message: "아이디를 입력해주세요",
            trigger: "blur",
          },
        ],
        companyemail: [
          {
            required: true,
            message: "이메일을 입력해주세요",
            trigger: "blur",
          },
        ],
        // 비밀번호
        newpassword: [
          {
            required: true,
            message: "비밀번호를 입력해주세요",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "5-10자리로 설정해주세요",
            trigger: "blur",
          },
        ],
        // 비밀번호확인
        newpasswordconfirm: [
          {
            required: true,
            message: "비밀번호를 확인해주세요",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "5-10자리로 설정해주세요",
            trigger: "blur",
          },
          { validator: checkPWCF, trigger: "blur" },
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
          // axios.get 비번찾기
          axios
            .get("https://i5d206.p.ssafy.io:8443/ent/findpw", {
              params: {
                email: this.ruleForm.companyemail,
                id: this.ruleForm.companyid,
              },
              headers: { Authorization: this.token },
            })
            .then((result) => {
              console.log(result);
              // 비밀번호 변경
              axios
                .put("https://i5d206.p.ssafy.io:8443/ent", {
                  headers: { Authorization: this.token },
                  ent_contact: result.data.ent_contact,
                  ent_email: result.data.ent_email,
                  ent_id: result.data.ent_id,
                  ent_index: result.data.ent_index,
                  ent_name: result.data.ent_name,
                  ent_password: this.ruleForm.newpassword,
                })
                .then((res) => {
                  console.log(res);
                  setTimeout(() => {
                    this.$message({
                      message: "비밀번호가 초기화되었습니다",
                      type: "success",
                    });
                    this.ruleForm.newpassword = "";
                    this.ruleForm.newpasswordconfirm = "";
                  }, 3000);
                })
                .catch((err) => {
                  console.log("token error");
                  console.log(err.response);
                  if (err.response == 401) {
                    this.$message.error('로그인세션이 만료되었습니다');
                    localStorage.clear();
                    this.$router.push("/");
                  }
                });
            })
            .catch((err) => {
              console.log("token error");
              console.log(err.response);
              if (err.response == 401) {
                this.$message.error('로그인세션이 만료되었습니다');
                localStorage.clear();
                this.$router.push("/");
              }
            });
          //
          this.$store.state.findCompanyPw = false;
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
