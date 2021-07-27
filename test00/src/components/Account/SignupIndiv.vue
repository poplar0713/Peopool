<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
    <!-- 개인회원 ID -->
    <el-form-item label="ID" prop="ind_id">
      <el-input v-model="ruleForm.ind_id"></el-input>
      <el-button type="info" @click="checkID">중복확인</el-button>
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
            message: "Please input Activity ID",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "Length should be 5 to 10",
            trigger: "blur",
          },
        ],
        ind_password: [
          {
            required: true,
            message: "Please input Activity Password",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "Length should be 10 to 15",
            trigger: "blur",
          },
        ],
        ind_password_cf: [
          { required: true, message: "Please check Password", trigger: "blur" },
          {
            min: 5,
            max: 10,
            message: "Length should be 10 to 15",
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
            message: "Please input your Birth(YYMMDD)",
            trigger: "blur",
          },
          // {
          //   type:"Number",
          //   message: "Please input your Birth(YYMMDD)",
          //   trigger: "blur",
          // },
        ],
        ind_gender: [
          {
            required: true,
            message: "Please select your Gender",
            trigger: "change",
          },
        ],
        ind_phone: [
          {
            required: true,
            message: "Please input your Phone number",
            trigger: "change",
          },
        ],
        ind_email: [
          {
            required: true,
            message: "Please input your Email",
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
            .catch(() => {});
        } else {
          console.log("error submit!!");
          this.failed();
          return false;
        }
        //
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
  checkID(){
    axios.post('https://localhost:8443/idv/checkid',{

    })
  }
};
</script>

<style></style>
