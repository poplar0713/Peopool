<template>
  <div
    style="width:30rem;"
    v-loading="loading"
    element-loading-text="Loading..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
  >
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <!-- 기업이름 -->
      <el-form-item label="> 회사명 : " prop="CompanyName">
        <strong>{{ this.ruleForm.CompanyName }}</strong>
      </el-form-item>
      <!-- 기업연락처 -->
      <el-form-item label="> 회사 대표 번호 : " prop="UserTel">
        <el-input type="tel" v-model="this.ruleForm.CompanyTel"></el-input>
      </el-form-item>
      <!-- 기업이메일 -->
      <el-form-item label="> 회사 대표 Email : " prop="UserEmail">
        <el-input type="email" v-model="this.ruleForm.CompanyEmail"></el-input>
      </el-form-item>
      <!-- 기업회원 PW -->
      <el-form-item label="계정 Password" prop="Password">
        <el-input type="password" v-model="this.ruleForm.Password"></el-input>
      </el-form-item>
      <!-- 기업회원 PW 확인 -->
      <el-form-item label="계정 Password 확인" prop="PasswordConfirm">
        <el-input type="password" v-model="this.ruleForm.PasswordConfirm"></el-input>
      </el-form-item>
      <div style="float:right">
        <el-form-item>
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
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  name: "SideBarProfileCompanyBasicInfo",
  components: {},
  mounted() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 회원정보 가져오기
    axios
      .get(`https://i5d206.p.ssafy.io:8443/ent/${index}`)
      .then((res) => {
        this.ruleForm.CompanyName = res.data.ent_name;
        this.ruleForm.CompanyTel = res.data.ent_contact;
        this.ruleForm.CompanyEmail = res.data.ent_email;
        this.ruleForm.CompanyId = res.data.ent_id;
        this.ruleForm.CompanyIndex = res.data.ent_index;
      })
      .catch((err) => {
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
  data() {
    // 비밀번호확인 체크(비어있거나 비밀번호랑 다르면)
    const checkPWCF = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("비밀번호(확인)를 입력해주세요"));
      } else if (value !== this.ruleForm.Password) {
        callback(new Error("비밀번호를 확인해주세요"));
      } else {
        callback();
      }
    };
    return {
      loading: false,
      ruleForm: {
        Password: "",
        PasswordConfirm: "",
        CompanyName: "",
        open: false,
        CompanyTel: "",
        CompanyEmail: "",
        CompanyId: "",
        CompanyIndex: 0,
      },
      rules: {
        Password: [
          {
            required: true,
            message: "비밀번호를 입력해주세요",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "5-10자리 비밀번호를 입력해주세요",
            trigger: "blur",
          },
        ],
        PasswordConfirm: [
          {
            required: true,
            message: "비밀번호(확인)를 확인해주세요",
            trigger: "blur",
          },
          {
            min: 5,
            max: 10,
            message: "5-10자리 비밀번호를 입력해주세요",
            trigger: "blur",
          },
          { validator: checkPWCF, trigger: "blur" },
        ],
        CompanyTel: [
          {
            required: true,
            message: "연락처를 입력해주세요",
            trigger: "change",
          },
        ],
        CompanyEmail: [
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
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // 회원정보 수정
          axios
            .put("https://i5d206.p.ssafy.io:8443/ent", {
              headers: { Authorization: this.$store.state.usertoken },
              ent_email: this.ruleForm.CompanyEmail,
              ent_id: this.ruleForm.CompanyId,
              ent_index: this.ruleForm.CompanyIndex,
              ent_name: this.ruleForm.CompanyName,
              ent_password: this.ruleForm.Password,
              ent_contact: this.ruleForm.CompanyTel,
            })
            .then((res) => {
              console.log(res);
              this.loading = true;
              setTimeout(() => {
                this.loading = false;
                this.successmessage();
                this.ruleForm.Password = "";
                this.ruleForm.PasswordConfirm = "";
              }, 2000);
            })
            .catch((err) => {
              if (err.response == 401) {
                this.$message.error("로그인세션이 만료되었습니다");
                this.$cookies.remove("PID_AUTH");
                localStorage.clear();
                this.$router.push("/");
              }
            });
        } else {
          console.log("error submit!!");
          this.failed();
          return false;
        }
      });
    },
    // save 성공
    successmessage() {
      this.$message({
        message: "수정완료 되었습니다",
        type: "success",
      });
    },
    // 양식 다 안채우고 save눌렀을 때
    failed() {
      this.$message.error("프로필양식을 확인해주세요");
    },
  },
};
</script>

<style></style>
