<template>
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
    <!-- 개인회원 ID -->
    <el-form-item label="ID" prop="LoginIndivID">
        <el-input v-model="ruleForm.LoginIndivID"></el-input>
    </el-form-item>
    <!-- 개인회원 PW -->
    <el-form-item label="Password" prop="LoginIndivPW">
        <el-input type="password" v-model="ruleForm.LoginIndivPW"></el-input>
    </el-form-item>
    <!-- 생성 및 취소 버튼 -->
    <el-form-item>
        <el-button @click="resetForm('ruleForm')">Reset</el-button>
        <el-button type="warning" @click="submitForm('ruleForm'), openFullScreen1()">Login</el-button>
    </el-form-item>
    </el-form>
</template>

<script>
  export default {
    data() {
      return {
        fullscreenLoading:false,
        ruleForm: {
          LoginIndivID: '',
          LoginIndivPW: '',
        },
        rules: {
          LoginIndivID: [
            { required: true, message: 'Please input Activity ID', trigger: 'blur' },
            { min: 5, max: 10, message: 'Length should be 5 to 10', trigger: 'blur' }
          ],
          LoginIndivPW: [
            { required: true, message: 'Please input Activity Password', trigger: 'blur' },
            { min: 10, max: 15, message: 'Length should be 10 to 15', trigger: 'blur' }
          ],
        }
      };
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            // alert('submit!');
            this.$router.push('home');
            this.$store.state.LoginDialog=false
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      openFullScreen1() {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
      }, 1000);
    },
    }
  }
</script>

<style>

</style>
