<template>
  <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
    <!-- 개인회원 ID -->
    <el-form-item label="ID" prop="ind_id">
      <el-input
        v-model="ruleForm.ind_id"
        placeholder="중복확인을 해주세요"
      ></el-input>
      <el-button @click="checkID">중복확인</el-button>
      <!-- <p type="primary" v-if="allowedID === true">사용가능한 아이디입니다</p>
      <el-button @click="checkID" v-if="allowedID === true"
        >다른아이디 사용하기</el-button
      > -->
    </el-form-item>
    <!-- 개인회원 PW -->
    <el-form-item label="Password" prop="ind_password">
      <el-input
        type="password"
        v-model="ruleForm.ind_password"
        autocomplete="off"
      ></el-input>
    </el-form-item>
    <!-- 개인회원 PW 확인 -->
    <el-form-item label="Password Confirmation" prop="ind_password_cf">
      <el-input
        type="password"
        v-model="ruleForm.ind_password_cf"
        autocomplete="off"
      ></el-input>
    </el-form-item>
    <!-- 개인회원이름 -->
    <el-form-item label="Name" prop="ind_name">
      <el-input v-model="ruleForm.ind_name"></el-input>
    </el-form-item>
    <!-- 개인회원 생년월일 -->
    <el-form-item label="Birth" prop="ind_birth">
      <el-input
        v-model="ruleForm.ind_birth"
        placeholder="YYMMDD형식으로 작성해주세요"
      ></el-input>
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
        @click="SignupInd('ruleForm')"
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
    // 비밀번호 체크
    const checkPWCF = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("비밀번호(확인)를 다시 입력해주세요"));
      } else if (value !== this.ruleForm.ind_password) {
        callback(new Error("비밀번호(를 확인해주세요"));
      } else if (length.value < 5 || length.value > 10) {
        callback(new Error("5-10자리로 설정해주세요"));
      } else {
        callback();
      }
    };
    // 아이디 중복 체크
    const validationID = (rule, value, callback) => {
      // this.checkID();
      if (value === "") {
        callback(new Error("ID를 입력해주세요"));
      } else if (length.value < 5 || length.value > 10) {
        callback(new Error("1-15자리로 설정해주세요"));
      } else {
        callback();
      }
    };
    return {
      filled: false,
      allowedID: true,
      ruleForm: {
        ind_id: "",
        ind_password: "",
        ind_password_cf: "",
        ind_name: "",
        ind_birth: "",
        ind_gender: "",
        ind_phone: "",
        ind_email: "",
      },
      rules: {
        // 아이디
        ind_id: [
          {
            validator: validationID,
            trigger: "blur",
          },
        ],
        // 비밀번호
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
        // 비밀번호확인
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
          { validator: checkPWCF, trigger: "blur" },
        ],
        // 이름
        ind_name: [
          {
            required: true,
            message: "이름을 입력해주세요",
            trigger: "blur",
          },
        ],
        // 생년월일
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
        // 성별
        ind_gender: [
          {
            required: true,
            message: "성별을 체크해주세요",
            trigger: "change",
          },
        ],
        // 연락처
        ind_phone: [
          {
            required: true,
            message: "연락처를 입력해주세요",
            trigger: "change",
          },
        ],
        // 이메일
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
  watch: {},
  // computed
  computed: {},
  // method
  methods: {
    SignupInd(formName) {
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
    // id중복체크
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
