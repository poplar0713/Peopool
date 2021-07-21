<template>
  <el-button type="text" @click="dialogVisible = true" style="color:black"
    >{{ user.name }}
  </el-button>
  <el-button circle><i class="far fa-heart"></i></el-button>

  <div>
    <el-dialog
      :title="user.name"
      v-model="dialogVisible"
      width="50%"
      style="color:black"
    >
      <div>
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item title="Introduction" name="1">
            <div>
              취업하고싶다.
            </div>
          </el-collapse-item>
          <el-collapse-item title="Level of Education" name="2">
            <div>
              SSAFY Univ
            </div>
          </el-collapse-item>
          <el-collapse-item title="Experience" name="3">
            <div>
              치킨시켜먹기
            </div>
          </el-collapse-item>
          <el-collapse-item title="Documents" name="4">
            <div>
              관련서류목록
            </div>
          </el-collapse-item>
          <el-collapse-item title="reservation" name="5">
            <div>
              <el-row>
                <el-col :span="12"
                  ><div class="grid-content bg-purple">
                    <span class="demonstration"></span>
                    <el-date-picker
                      v-model="reservationdata.date1"
                      type="date"
                      placeholder="Pick a date"
                      :default-value="new Date(2021, 7, 20)"
                    >
                    </el-date-picker></div
                ></el-col>
                <el-col :span="12"
                  ><div class="grid-content bg-purple-light">
                    <el-time-select
                      v-model="reservationdata.time1"
                      start="08:30"
                      step="00:15"
                      end="18:30"
                      placeholder="Select time"
                    >
                    </el-time-select></div
                ></el-col>
              </el-row>
            </div>
            <div>
              <el-row>
                <el-col :span="12"
                  ><div class="grid-content bg-purple">
                    <span class="demonstration"></span>
                    <el-date-picker
                      v-model="reservationdata.date2"
                      type="date"
                      placeholder="Pick a date"
                      :default-value="new Date(2021, 7, 20)"
                    >
                    </el-date-picker></div
                ></el-col>
                <el-col :span="12"
                  ><div class="grid-content bg-purple-light">
                    <el-time-select
                      v-model="reservationdata.time2"
                      start="08:30"
                      step="00:15"
                      end="18:30"
                      placeholder="Select time"
                    >
                    </el-time-select></div
                ></el-col>
              </el-row>
            </div>
            <div>
              <el-row>
                <el-col :span="12"
                  ><div class="grid-content bg-purple">
                    <span class="demonstration"></span>
                    <el-date-picker
                      v-model="reservationdata.date3"
                      type="date"
                      placeholder="Pick a date"
                      :default-value="new Date(2021, 7, 20)"
                    >
                    </el-date-picker></div
                ></el-col>
                <el-col :span="12"
                  ><div class="grid-content bg-purple-light">
                    <el-time-select
                      v-model="reservationdata.time3"
                      start="08:30"
                      step="00:15"
                      end="18:30"
                      placeholder="Select time"
                    >
                    </el-time-select></div
                ></el-col>
              </el-row>
            </div>
            <el-button
              @click="(dialogVisible = false), interviewrequest()"
              type="success"
              style="float: right; margin:10px;"
              :plain="true"
              >Interview Request</el-button
            >
          </el-collapse-item>
        </el-collapse>
      </div>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">Cancel</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "userdetail",
  setup() {},
  created() {
    console.log(this.user);
  },
  data() {
    return {
      dialogVisible: false,
      activeNames: ["1"],
      activeName: "1",
      reservationdata: [
        { date1: "" },
        { time1: "" },
        { date2: "" },
        { time2: "" },
        { date3: "" },
        { time3: "" },
      ],
    };
  },
  props: {
    user: Object,
  },
  methods: {
    handleClose(done) {
      this.$confirm("Are you sure to close this dialog?")
        .then(() => {
          done();
          this.dialogVisible = false;
        })
        .catch(() => {});
    },
    successmessage() {
      this.$message({
        message: "Congrats, this is a success message.",
        type: "success",
      });
    },
    interviewrequest() {
      this.$message({
        showClose: true,
        message: "Success",
        type: "success",
      });
      return this.reservationdata;
    },
  },
};
</script>

<style></style>
