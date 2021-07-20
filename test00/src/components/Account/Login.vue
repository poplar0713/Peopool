<template>
  <!-- Form -->
  <div>
    <div class="loginbutton">
      <img src="@/assets/PeoPool.png" alt="" style="width:30%" />
      <el-button
        type="warning"
        plain
        @click="$store.state.LoginDialog = true"
        style="width:30%"
        >시작하기</el-button
      >
    </div>
    <br />
    <el-dialog title="Login" v-model="$store.state.LoginDialog">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="개인회원" name="first">
          <!-- 개인로그인 -->
          <LoginIndiv />
          <!-- 회원가입했니? -->
          <div>
            <el-button
              type="text"
              @click="$store.state.SignupDialogIndiv = true"
              style="color:black"
              >아직 회원이 아니신가요?</el-button
            >
            <el-dialog
              title="Signup"
              v-model="$store.state.SignupDialogIndiv"
              width="30%"
              :before-close="handleClose"
            >
              <!-- 개인회원가입 -->
              <SignupIndiv />
            </el-dialog>
          </div>
        </el-tab-pane>
        <el-tab-pane label="기업회원" name="second">
          <LoginCompany />
          <div>
            <el-button
              type="text"
              @click="$store.state.SignupDialogCompany = true"
              style="color:black"
              >아직 (기업)회원이 아니신가요?</el-button
            >
            <el-dialog
              title="Signup"
              v-model="$store.state.SignupDialogCompany"
              width="30%"
              :before-close="handleClose"
            >
              <SignupCompany />
            </el-dialog>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>
  </div>
</template>

<script>
import SignupCompany from "@/components/Account/SignupCompany.vue";
import SignupIndiv from "@/components/Account/SignupIndiv.vue";
import LoginIndiv from "@/components/Account/LoginIndiv.vue";
import LoginCompany from "@/components/Account/LoginCompany.vue";

export default {
  components: {
    SignupIndiv,
    SignupCompany,
    LoginIndiv,
    LoginCompany,
  },
  data() {
    return {
      dialogTableVisible: false,
      // dialogFormVisible: false,
      // dialogVisible2: false,
      fullscreenLoading: false,
      activeName: "first",
      form: {
        individualmemberID: "",
        individualmemberPW: "",
        companyID: "",
        companyPW: "",
        PhoneNumber: "",
        open: false,
      },
      name: "",
      formLabelWidth: "120px",
    };
  },
  methods: {
    //
    handleClose(done) {
      this.$confirm("Are you sure to close this page?")
        .then(() => {
          done();
          this.dialogVisible = false;
        })
        .catch(() => {});
    },
    //
    onSubmit() {
      console.log("submit!");
      this.$router.push("home");
    },
    //
    openFullScreen1() {
      this.fullscreenLoading = true;
      setTimeout(() => {
        this.fullscreenLoading = false;
      }, 1000);
    },
    //
    handleClick(tab, event) {
      console.log(tab, event);
    },
  },
};
</script>

<style>
.loginbutton {
  /* width: 100px; 
  hegiht: 200px; 
  margin: 0 auto;
  top: 20%; 
  left: 40%; */
  /* cursor: pointer; */
}
</style>
