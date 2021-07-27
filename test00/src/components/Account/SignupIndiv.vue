<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
    <!-- 개인회원 ID -->
    <el-form-item label="ID" prop="ind_id">
      <el-input v-model="ruleForm.ind_id"></el-input>
      <el-button type="info" @click="checkID" v-if="allowedID === false"
        >중복확인</el-button
      >
      <p type="primary" v-if="allowedID === true">사용가능한 아이디입니다</p>
      <el-button @click="checkID" v-if="allowedID === true"
        >다른아이디 사용하기</el-button
      >
    </el-form-item>
    <!-- 개인회원 PW -->
    <el-form-item label="Password" prop="ind_password">
      <el-input type="password" v-model="ruleForm.ind_password"></el-input>
    </el-form-item>
    <!-- 개인회원 PW 확인 -->
    <el-form-item label="Password Confirmation" prop="ind_password_cf">
      <el-input type="password" v-model="ruleForm.ind_password_cf"></el-input>
    </el-form-item>
    <!-- 개인회원이름 -->
    <el-form-item label="Name" prop="ind_name">
      <el-input v-model="ruleForm.ind_name"></el-input>
    </el-form-item>
    <!-- 개인회원 생년월일 -->
    <el-form-item label="Birth" prop="ind_birth">
      <el-input v-model="ruleForm.ind_birth"></el-input>
    </el-form-item>
    <!-- 성별 -->
    <el-form-item label="Gender" prop="ind_gender">
      <el-radio-group v-model="ruleForm.ind_gender">
        <el-radio label="M"></el-radio>
        <el-radio label="F"></el-radio>
      </el-radio-group>
    </el-form-item>
    <!-- 연락처 -->
    <el-form-item label="Tel" prop="ind_phone">
      <el-input type="tel" v-model="ruleForm.ind_phone"></el-input>
    </el-form-item>
    <!-- 이메일 -->
    <el-form-item label="Email" prop="ind_email">
      <el-input type="email" v-model="ruleForm.ind_email"></el-input>
    </el-form-item>
    <!-- 생성 및 취소 버튼 -->
    <el-form-item>
      <el-button @click="resetForm('ruleForm')">Reset</el-button>
      <el-button
        type="warning"
        @click="submitForm('ruleForm')"
        v-loading.fullscreen.lock="fullscreenLoading"
        >Create</el-button
      >
    </el-form-item>
  </el-form>
</template>

<script>
import axios from "axios";
export default {
  // data
  data() {
    return {
      filled: false,
      allowedID: false,
      ind_password_cf: "",
      ruleForm: {
        ind_id: "",
        ind_password: "",
        ind_name: "",
        ind_birth: "",
        ind_gender: "",
        ind_phone: "",
        ind_email: "",
      },
      rules: {
        ind_id: [
          {
            required: true,
            message: "사용하실 아이디를 입력해주세요",
            trigger: "blur",
          },
          {
            min: 3,
            max: 10,
            message: "최소 3글자, 최대10글자의 아이디를 입력해주세요",
            trigger: "blur",
          },
        ],
        ind_password: [
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
        ind_password_cf: [
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
        ],
        ind_name: [
          {
            required: true,
            message: "Please input your Name",
            trigger: "blur",
          },
        ],
        ind_birth: [
          {
            required: true,
            message: "생년월일을 입력해주세요",
            trigger: "blur",
          },
          {
            min: 6,
            max: 6,
            message: "'YYMMDD'형식의 생년월일을 입력해주세요",
            trigger: "blur",
          },
        ],
        ind_gender: [
          {
            required: true,
            message: "성별을 체크해주세요",
            trigger: "change",
          },
        ],
        ind_phone: [
          {
            required: true,
            message: "연락처를 입력해주세요",
            trigger: "change",
          },
        ],
        ind_email: [
          {
            required: true,
            message: "이메일을 입력해주세요",
            trigger: "change",
          },
          {
            type: "email",
            message: "이메일형식을 바르게 입력해주세요",
            trigger: "change",
          },
        ],
      },
      fullscreenLoading: false,
    };
  },
  // watch
  watch: {
    ind_password_cf(value) {
      if (value != this.ind_password) {
        alert("비밀번호를 확인해주세요.");
      }
    },
  },
  // computed
  computed: {},
  // method
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          console.log(this.ruleForm);
          this.openFullScreen2();
          // 데이터정보 보내기
          axios
            .post("https://localhost:8443/ind", {
              ind_id: this.ruleForm.ind_id,
              ind_password: this.ruleForm.ind_password,
              ind_name: this.ruleForm.ind_name,
              ind_birth: this.ruleForm.ind_birth,
              ind_gender: this.ruleForm.ind_gender,
              ind_phone: this.ruleForm.ind_phone,
              ind_email: this.ruleForm.ind_email,
            })
            .then((res) => {
              if (res.status == 200) {
                this.$store.state.SignupDialogIndiv = false;
                this.successmessage();
                console.log(this.ruleForm);
                // this.$router.push(`user/${this.ruleForm.ind_id}`)
              }
            })
            .catch((err) => {
              console.log(err);
            });
        } else {
          console.log("error submit!!");
          this.failed();
          return false;
        }
        //
      });
    },
    // 초기화
    resetForm(formName) {
      this.$refs[formName].resetFields();
      // 로딩
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
    // 성공메시지
    successmessage() {
      this.$message({
        message: "회원가입(개인용)이 완료되었습니다",
        type: "success",
      });
    },
    // 실패메시지
    failed() {
      this.$message.error("Oops, check your identification info");
    },
    checkID() {
      // alert("진입성공");
      axios
        .post("https://localhost:8443/ind/checkid", {
          ind_id: this.ruleForm.ind_id,
        })
        .then((res) => {
          console.log(res);
          this.$message.error("이미 다른 사용자가 사용중인 아이디입니다.");
          this.ruleForm.ind_id = "";
          this.allowedID = false;
        })
        .catch((err) => {
          console.log(err);
          this.allowedID = true;
          this.$message({
            message: "사용가능한 아이디입니다.",
            type: "success",
          });
        });
    },
  },
};
</script>

<style></style>
