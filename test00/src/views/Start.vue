<template>
<!-- Form -->
  <div>
    <el-button type="warning" plain @click="dialogFormVisible = true" class="loginbutton">start</el-button>

    <el-dialog title="Login" v-model="dialogFormVisible">
      <el-form :model="form">
        <el-form-item label="ID" :label-width="formLabelWidth">
          <el-input v-model="form.id" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="Password" :label-width="formLabelWidth">
          <el-input type="password" v-model="form.password" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div>
        <el-button type="text" @click="dialogVisible = true">아직 회원이 아니신가요?</el-button>
        <el-dialog
          title="Signup"
          v-model="dialogVisible"
          width="30%"
          :before-close="handleClose">
          <div>
            <el-form ref="form" :model="form" label-width="120px">
              <el-form-item label="ID/Email">
                <el-input v-model="form.signupId"></el-input>
              </el-form-item>
              <el-form-item label="Password">
                <el-input type="password" v-model="form.signupPw"></el-input>
              </el-form-item>
              <el-form-item label="Activity zone">
                <el-select v-model="form.region" placeholder="please select your zone">
                  <el-option label="Zone one" value="shanghai"></el-option>
                  <el-option label="Zone two" value="beijing"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="Activity time">
                <el-col :span="11">
                  <el-date-picker type="date" placeholder="Pick a date" v-model="form.date1" style="width: 100%;"></el-date-picker>
                </el-col>
                <el-col class="line" :span="2">-</el-col>
                <el-col :span="11">
                  <el-time-picker placeholder="Pick a time" v-model="form.date2" style="width: 100%;"></el-time-picker>
                </el-col>
              </el-form-item>
              <el-form-item label="Instant delivery">
                <el-switch v-model="form.delivery"></el-switch>
              </el-form-item>
              <el-form-item label="Activity type">
                <el-checkbox-group v-model="form.type">
                  <el-checkbox label="Online activities" name="type"></el-checkbox>
                  <el-checkbox label="Promotion activities" name="type"></el-checkbox>
                  <el-checkbox label="Offline activities" name="type"></el-checkbox>
                  <el-checkbox label="Simple brand exposure" name="type"></el-checkbox>
                </el-checkbox-group>
              </el-form-item>
              <el-form-item label="Resources">
                <el-radio-group v-model="form.resource">
                  <el-radio label="Sponsor"></el-radio>
                  <el-radio label="Venue"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="Activity form">
                <el-input type="textarea" v-model="form.desc"></el-input>
              </el-form-item>
            </el-form>
          </div>
          <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible = false">Cancel</el-button>
              <el-button type="primary" 
              @click="dialogVisible = false, onSubmit(), openFullScreen1()"
              v-loading.fullscreen.lock="fullscreenLoading"
              >Create</el-button>
            </span>
          </template>
        </el-dialog>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogFormVisible = false">Cancel</el-button>
          <el-button type="primary" @click="dialogFormVisible = false, openFullScreen1()"><router-link to="/home">Login</router-link></el-button>
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
        dialogVisible: false,
        fullscreenLoading: false,
        form: {
          signupId:'',
          signupPw:'',
          id:'',
          password:'',
          name: '',
          region: '',
          date1: '',
          date2: '',
          delivery: false,
          type: [],
          resource: '',
          desc: ''
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
    }
  };
</script>

<style>
.loginbutton {
  width: 300px; 
  hegiht: 200px; 
  position: absolute; 
  top: 50%; 
  left: 40%; 
  margin-top: -100px; 
  margin-left: -100px;
}
</style>
