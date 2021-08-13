<template>
  <!-- Form -->
  <div>
    <el-button
      id="startbtn"
      type="warning"
      plain
      @click="$store.state.LoginDialog = true"
      style="width:30%"
      >시작하기 ></el-button
    >

    <br />
    <el-dialog title="Login" v-model="$store.state.LoginDialog" width="30%">
      <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="개인회원" name="first">
          <!-- 개인로그인 -->
          <LoginIndiv />
          <!-- 회원가입했니? -->
          <div style="text-align:center">
            <!-- 회원가입 -->
            <el-button
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
            <br /><br />
            <!-- 아이디찾기 -->
            <el-text
              @click="$store.state.findUserId = true"
              style="color:black; margin:10px; cursor:pointer"
              >아이디찾기</el-text
            >
            <el-dialog
              title="아이디 찾기"
              v-model="$store.state.findUserId"
              width="30%"
              :before-close="handleClose"
            >
              <FindUserId />
            </el-dialog>
            <!-- 비밀번호찾기 -->
            <el-text
              @click="$store.state.findUserPw = true"
              style="color:black; margin:10px; cursor:pointer"
              >비밀번호 초기화</el-text
            >
            <el-dialog
              title="비밀번호 초기화"
              v-model="$store.state.findUserPw"
              width="30%"
              :before-close="handleClose"
            >
              <ResetUserPw />
            </el-dialog>
          </div>
        </el-tab-pane>
        <!-- 기업 -->
        <!--  -->
        <!--  -->
        <el-tab-pane label="기업회원" name="second">
          <LoginCompany />
          <div style="text-align:center">
            <el-button
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
            <br /><br />
            <!-- 아이디찾기 -->
            <el-text
              @click="$store.state.findCompanyId = true"
              style="color:black; margin:10px; cursor:pointer"
              >아이디찾기</el-text
            >
            <el-dialog
              title="아이디 찾기"
              v-model="$store.state.findCompanyId"
              width="30%"
              :before-close="handleClose"
            >
              <FindCompanyId />
            </el-dialog>
            <!-- 비밀번호찾기 -->
            <el-text
              @click="$store.state.findCompanyPw = true"
              style="color:black; margin:10px; cursor:pointer"
              >비밀번호 초기화</el-text
            >
            <el-dialog
              title="비밀번호 초기화"
              v-model="$store.state.findCompanyPw"
              width="30%"
              :before-close="handleClose"
            >
              <ResetCompanyPw />
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
import FindUserId from "./FindUserId.vue";
import ResetUserPw from "./ResetUserPw.vue";
import FindCompanyId from "./FindCompanyId.vue";
import ResetCompanyPw from "./ResetCompanyPw.vue";
export default {
  name: "Login",
  components: {
    SignupIndiv,
    SignupCompany,
    LoginIndiv,
    LoginCompany,
    FindUserId,
    ResetUserPw,
    FindCompanyId,
    ResetCompanyPw,
  },
  data() {
    return {
      dialogTableVisible: false,
      fullscreenLoading: false,
      activeName: "first",
      name: "",
      formLabelWidth: "80px",
      form: {
        individualmemberID: "",
        individualmemberPW: "",
        companyID: "",
        companyPW: "",
        PhoneNumber: "",
        open: false,
      },
    };
  },
  methods: {
    // 창닫기
    handleClose(done) {
      this.$confirm("창을 닫으시겠습니까?")
        .then(() => {
          done();
          this.dialogVisible = false;
        })
        .catch((err) => {
          console.log("token error");
          console.log(err.response);
          if (err.response == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
    // 제출
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

<style scoped>
#startbtn {
  background-color: #ffc000;
  color: #5f2d9a;
  font-weight: bold;
  font-size: 20px;
  height: 50px;
  margin-left: 60px;
  border-radius: 30px;
}
#startbtn:hover {
  background-color: #f7d46b;
}
</style>
