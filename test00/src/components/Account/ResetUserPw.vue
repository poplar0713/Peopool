<template>
  <el-form
    :model="ruleForm"
    :rules="rules"
    ref="ruleForm"
    style="text-align:center"
  >
    <!-- 개인회원 ID -->
    <el-form-item label="ID" prop="userid">
      <el-input v-model="ruleForm.userid"></el-input>
    </el-form-item>
    <!-- 개인회원 연락처 -->
    <el-form-item label="연락처" prop="userphone">
      <el-input type="tel" v-model="ruleForm.userphone"></el-input>
    </el-form-item>
    <!-- 개인회원 새로운 PW -->
    <el-form-item label="새 비밀번호" prop="newpassword">
      <el-input type="password" v-model="ruleForm.newpassword"></el-input>
    </el-form-item>
    <!-- 개인회원 새로운 PW 확인 -->
    <el-form-item label="새 비밀번호 확인" prop="newpasswordconfirm">
      <el-input
        type="password"
        v-model="ruleForm.newpasswordconfirm"
      ></el-input>
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
        userid: "",
        userphone: "",
        newpassword: "",
        newpasswordconfirm: "",
      },
      rules: {
        userid: [
          {
            required: true,
            message: "아이디를 입력해주세요",
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
            .get("https://i5d206.p.ssafy.io:8443/ind/findpw", {
              params: {
                id: this.ruleForm.userid,
                phone: this.ruleForm.userphone,
              },
            })
            .then((result) => {
              console.log(result);
              // 비밀번호 변경
              axios
                .put("https://i5d206.p.ssafy.io:8443/ind", {
                  ind_birth: result.data.ind_birth,
                  ind_email: result.data.ind_email,
                  ind_gender: result.data.ind_gender,
                  ind_id: result.data.ind_id,
                  ind_index: result.data.ind_index,
                  ind_name: result.data.ind_name,
                  ind_password: this.ruleForm.newpassword,
                  ind_phone: result.data.ind_phone,
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
                  console.log(err);
                });

              setTimeout(() => {
                //코드작성
              }, 3000);
            })
            .catch((err) => {
              alert(err);
            });
          //
          this.$store.state.findUserPw = false;
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
