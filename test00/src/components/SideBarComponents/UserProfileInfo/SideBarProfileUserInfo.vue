<template>
  <div style="width:100%;">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
      <!-- 개인회원이름 -->
      <el-form-item label="Name" prop="UserName">
        {{ this.ruleForm.UserName }}
      </el-form-item>
      <!-- 개인회원 생년월일 -->
      <el-form-item label="Birth" prop="UserBirth">
        {{ this.ruleForm.UserBirth }}
      </el-form-item>
      <!-- 공개여부 -->
      <el-form-item label="Open to the public" prop="open">
        <el-switch v-model="ruleForm.open"></el-switch>
      </el-form-item>
      <!-- 성별 -->
      <el-form-item label="Gender" prop="Gender">
        {{ this.ruleForm.Gender }}
      </el-form-item>
      <!-- 연락처 -->
      <el-form-item label="Tel" prop="UserTel">
        <el-input type="tel" v-model="ruleForm.UserTel"></el-input>
      </el-form-item>
      <!-- 이메일 -->
      <el-form-item label="Email" prop="UserEmail">
        <el-input type="email" v-model="ruleForm.UserEmail"></el-input>
      </el-form-item>
      <!-- 개인회원 PW -->
      <el-form-item label="Password" prop="Password">
        <el-input type="password" v-model="ruleForm.Password"></el-input>
      </el-form-item>
      <!-- 개인회원 PW 확인 -->
      <el-form-item label="Password Confirmation" prop="PasswordConfirm">
        <el-input type="password" v-model="ruleForm.PasswordConfirm"></el-input>
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
    <!-- 비밀번호를 변경하시겠습니까? -->
    <el-dialog
      title="비밀번호를 변경하시겠습니까"
      v-model="changepwdialog"
      width="30%"
      :before-close="handleClose"
      v-loading="loading"
    element-loading-text="Loading..."
    element-loading-spinner="el-icon-loading"
    element-loading-background="rgba(0, 0, 0, 0.8)"
    >
      <span>
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
          <!-- 개인회원 신규PW -->
          <el-form-item label="NewPassword" prop="NewPassword">
            <el-input type="password" v-model="ruleForm.NewPassword"></el-input>
          </el-form-item>
          <!-- 개인회원 신규PW 확인 -->
          <el-form-item
            label="New Password Confirmation"
            prop="NewPasswordConfirm"
          >
            <el-input
              type="password"
              v-model="ruleForm.NewPasswordConfirm"
            ></el-input>
          </el-form-item>
        </el-form>
      </span>

      <template #footer>
        <span class="dialog-footer">
          <el-button @click="(changepwdialog = false), CancelChangePassword()"
            >취소</el-button
          >
          <el-button
            type="primary"
            @click="(changepwdialog = false), ChangePassword()"
            >변경</el-button
          >
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";

export default {
  data() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 회원정보 가져오기
    axios
      .get(`/ind/${index}`)
      .then((res) => {
        this.ruleForm.UserName = res.data.ind_name;
        this.ruleForm.UserBirth = res.data.ind_birth;
        this.ruleForm.Gender = res.data.ind_gender;
        this.ruleForm.UserTel = res.data.ind_phone;
        this.ruleForm.UserEmail = res.data.ind_email;
        this.ruleForm.UserIndex = res.data.ind_index;
        this.ruleForm.UserId = res.data.ind_id;
      })
      .catch((err) => {
        console.log(err);
      });
    const checkPW = (rule, value) => {
      if (value !== this.$store.state.secretkey) {
        this.changepwdialog = true;
      }
    };
    const checkPWCF = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("비밀번호(확인)를 입력해주세요"));
      } else if (value !== this.ruleForm.Password) {
        callback(new Error("비밀번호를 확인해주세요"));
      } else {
        callback();
      }
    };
    const checkNewPWCF = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("비밀번호(확인)를 입력해주세요"));
      } else if (value !== this.ruleForm.NewPassword) {
        callback(new Error("비밀번호를 확인해주세요"));
      } else {
        callback();
      }
    };
    return {
      loading:false,
      changepwdialog: false,
      userpw: "",
      ruleForm: {
        Password: "",
        PasswordConfirm: "",
        NewPassword: "",
        NewPasswordConfirm: "",
        UserName: "",
        UserBirth: "",
        open: false,
        Gender: "",
        UserTel: "",
        UserEmail: "",
        UserId: "",
        UserIndex: 0,
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
          { validator: checkPW, trigger: "blur" },
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
        NewPassword: [
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
        NewPasswordConfirm: [
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
          { validator: checkNewPWCF, trigger: "blur" },
        ],
        UserTel: [
          {
            required: true,
            message: "연락처를 입력해주세요",
            trigger: "change",
          },
        ],
        UserEmail: [
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
      console.log(this.$store.state.secretkey);
      this.$refs[formName].validate((valid) => {
        if (valid) {
          // alert('submit!');
          // this.openFullScreen2();
          // 회원정보 수정
          axios
            .put("/ind", {
              ind_birth: this.ruleForm.UserBirth,
              ind_email: this.ruleForm.UserEmail,
              ind_gender: this.ruleForm.Gender,
              ind_id: this.ruleForm.UserId,
              ind_index: this.ruleForm.UserIndex,
              ind_name: this.ruleForm.UserName,
              ind_password: this.ruleForm.Password,
              ind_phone: this.ruleForm.UserTel,
            })
            .then((res) => {
              console.log(res);
              this.$store.state.secretkey=this.ruleForm.Password
              this.loading=true
              setTimeout(() => {
                this.loading=false
                this.successmessage();
              }, 1000);
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
    openFullScreen2() {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
      }, 1000);
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
    // 창 닫기
    handleClose(done) {
      this.$confirm("정말 닫으시겠습니까?")
        .then(() => {
          done();
          this.changepwdialog = false;
        })
        .catch(() => {});
    },
    // 비밀번호 변경
    ChangePassword() {
      this.ruleForm.Password = this.ruleForm.NewPassword;
    },
    // 비밀번호 변경 취소
    CancelChangePassword() {
      this.ruleForm.Password = "";
    },
  },
};
</script>

<style></style>
