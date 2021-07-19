<template>
<!-- Form -->
  <div>
    <div class="loginbutton">
        <img src="@/assets/PeoPool.png" alt="" style="width:10%" @click="dialogFormVisible = true" >
    </div>
    <br>
    <el-dialog title="Login" v-model="dialogFormVisible">
        <el-tabs v-model="activeName" @tab-click="handleClick">
        <el-tab-pane label="개인회원" name="first">
          <el-form :model="form">
            <el-form-item label="ID" :label-width="formLabelWidth">
              <el-input v-model="form.individualmemberID" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="Password" :label-width="formLabelWidth">
              <el-input type="password" v-model="form.individualmemberPW" autocomplete="off"></el-input>
            </el-form-item>
          </el-form>
          <div>
            <el-button type="text" @click="dialogVisible1 = true" style="color:black">아직 회원이 아니신가요?</el-button>
            <el-dialog
              title="Signup"
              v-model="dialogVisible1"
              width="30%"
              :before-close="handleClose">
              <div>
                <el-form ref="form" :model="form" label-width="120px">
                  <el-form-item label="ID/Email">
                    <el-input v-model="form.signupindividualId"></el-input>
                  </el-form-item>
                  <el-form-item label="Password">
                    <el-input type="password" v-model="form.signupindividualPw"></el-input>
                  </el-form-item>
                  <el-form-item label="Password Confirm" label-width="35%">
                    <el-input type="password" v-model="form.signupindividualPwConfirm"></el-input>
                  </el-form-item>
                  <el-form-item label="공개여부">
                    <el-switch v-model="form.delivery"></el-switch>
                  </el-form-item>
                  <el-form-item label="성별">
                    <el-radio-group v-model="form.resource">
                      <el-radio label="男"></el-radio>
                      <el-radio label="女"></el-radio>
                    </el-radio-group>
                  </el-form-item>
                </el-form>
              </div>
              <template #footer>
                <span class="dialog-footer">
                  <el-button @click="dialogVisible1 = false">Cancel</el-button>
                  <el-button type="primary" 
                  @click="dialogVisible1 = false, onSubmit(), openFullScreen1()"
                  v-loading.fullscreen.lock="fullscreenLoading"
                  >Create</el-button>
                </span>
              </template>
            </el-dialog>
          </div>
        </el-tab-pane>
        <el-tab-pane label="기업회원" name="second">
            <el-form :model="form">
                <el-form-item label="ID" :label-width="formLabelWidth">
                <el-input v-model="form.companyID" autocomplete="off"></el-input>
                </el-form-item>
                <el-form-item label="Password" :label-width="formLabelWidth">
                <el-input type="password" v-model="form.companyPW" autocomplete="off"></el-input>
                </el-form-item>
            </el-form>
            <div>
                <el-button type="text" @click="dialogVisible2 = true" style="color:black">아직 (기업)회원이 아니신가요?</el-button>
                <el-dialog
                title="Signup"
                v-model="dialogVisible2"
                width="30%"
                :before-close="handleClose">
                <div>
                    <el-form ref="form" :model="form" label-width="120px">
                    <el-form-item label="회사명">
                        <el-input v-model="form.CompanyName"></el-input>
                    </el-form-item>
                    <el-form-item label="ID/Email">
                        <el-input v-model="form.signupCompanyId"></el-input>
                    </el-form-item>
                    <el-form-item label="Password">
                        <el-input type="password" v-model="form.signupCompanyPw"></el-input>
                    </el-form-item>
                    <el-form-item label="Password Confirm" label-width="35%">
                        <el-input type="password" v-model="form.signupCompanyPwConfirm"></el-input>
                    </el-form-item>
                    <el-form-item label="공개여부">
                        <el-switch v-model="form.delivery"></el-switch>
                    </el-form-item>
                    <el-form-item label="연락처">
                        <el-input type="tel" v-model="form.PhoneNumber"></el-input>
                    </el-form-item>
                    </el-form>
                </div>
                <template #footer>
                    <span class="dialog-footer">
                    <el-button @click="dialogVisible2 = false">Cancel</el-button>
                    <el-button type="primary" 
                    @click="dialogVisible2 = false, onSubmit(), openFullScreen1()"
                    v-loading.fullscreen.lock="fullscreenLoading"
                    >Create</el-button>
                    </span>
                </template>
                </el-dialog>
            </div>
        </el-tab-pane>
    </el-tabs>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">Cancel</el-button>
          <el-button type="warning" @click="dialogFormVisible = false, openFullScreen1()"><router-link style="text-decoration:none; color:white" to="/home">Login</router-link></el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        dialogTableVisible: false,
        dialogFormVisible: false,
        dialogVisible1: false,
        dialogVisible2: false,
        fullscreenLoading: false,
        form: {
          signupindividualId:'',
          signupindividualPw:'',
          signupindividualPwConfirm:'',
          CompanyName:'',
          signupCompanyId:'',
          signupCompanyPw:'',
          signupCompanyPwConfirm:'',
          individualmemberID:'',
          individualmemberPW:'',
          companyID:'',
          companyPW:'',
          PhoneNumber:'',
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: '',
          activeName: 'first',
        },
        formLabelWidth: '120px'
      };
    },
    methods: {
      handleClose(done) {
        this.$confirm('Are you sure to close this dialog?')
          .then(() => {
            done();
            this.dialogVisible = false
          })
          .catch(() => {});
      },
      onSubmit() {
        console.log('submit!');
      },
      openFullScreen1() {
        this.fullscreenLoading = true;
        setTimeout(() => {
          this.fullscreenLoading = false;
        }, 1000);
      },
      handleClick(tab, event) {
        console.log(tab, event);
      }
    }
  };
</script>

<style>
.loginbutton {
  /* width: 100px; 
  hegiht: 200px; 
  margin: 0 auto;
  top: 20%; 
  left: 40%; */
  cursor: pointer;
}
</style>
