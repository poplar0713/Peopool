<template>
  <el-button type="text" @click="dialogVisible = true" style="color:black"
    >{{ user.name }}
  </el-button>

  <!-- 팔로우가 되어있을때 -->
  <span v-if="follow" style="color: Tomato;">
    <i
      class="fas fa-heart fa-2x"
      size:7x
      @click="clickfollowBtn"
      style="cursor:pointer"
    ></i>
  </span>
  <!-- 팔로우가 안되어있을때 -->
  <span v-if="follow == false" style="color: Tomato;">
    <i
      @click="clickfollowBtn"
      class="far fa-heart fa-2x"
      style="cursor:pointer"
    ></i>
  </span>

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
              <webviewer initialDoc="파이팅 프런트.docx" />
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
import webviewer from "@/components/MainCompany/webviewer.vue";
import jwt_decode from "jwt-decode";
import axios from "axios";
export default {
  name: "UserDetail",
  components: {
    webviewer,
  },
  mounted() {},
  data() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 팔로우했는지 체크해보기
    axios
      .post("https://i5d206.p.ssafy.io:8443/fol/check", {
        fol_type: 1,
        follower: this.user.ind_index,
        following: index,
      })
      .then((res) => {
        // 팔로우가 되어있는것
        console.log(res), (this.follow = true);
      })
      .catch((err) => {
        // 팔로우가 안되어있는것
        console.log(err), (this.follow = false);
      });
    return {
      follow: false,
      company_index: index,
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
    clickfollowBtn() {
      if (this.follow) {
        console.log("팔로우 해제");
        axios
          .delete("https://i5d206.p.ssafy.io:8443/fol", {
            data: {
              fol_type: 1,
              following: this.company_index,
              follower: this.user.ind_index,
            },
          })
          .then((res) => {
            console.log(res);
            this.follow = false;
          })
          .catch((err) => console.log(err));
      } else if (this.follow == false) {
        console.log("팔로잉");
        axios
          .post("https://i5d206.p.ssafy.io:8443/fol", {
            fol_type: 1,
            following: this.company_index,
            follower: this.user.ind_index,
          })
          .then((res) => {
            console.log(res);
            this.follow = true;
          })
          .catch();
      }
    },
    handleClose(done) {
      this.$confirm("창을 닫으시겠습니까?")
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
