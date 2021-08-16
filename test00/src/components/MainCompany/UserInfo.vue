<template>
  <div>
    <el-text
      type="text"
      @click="dialogVisible = true"
      style="color:black; text-align:center; cursor:pointer"
      size="mini"
      >상세보기
    </el-text>
    <div style="text-align:center">
      <el-dialog
        :title="this.userdata.ind_name"
        v-model="dialogVisible"
        width="50%"
        style="color:black"
      >
        <!-- 팔로우가 되어있을때 -->
        <div v-if="follow" style="color: Tomato;">
          <i
            class="fas fa-heart fa-2x"
            size:7x
            @click="clickfollowBtn"
            style="cursor:pointer"
          ></i>
        </div>
        <!-- 팔로우가 안되어있을때 -->
        <div v-if="follow == false" style="color: Tomato;">
          <i
            @click="clickfollowBtn"
            class="far fa-heart fa-2x"
            style="cursor:pointer"
          ></i>
        </div>
        <br />
        <!-- 태그 -->
        <div
          v-if="this.user_tags.length > 0"
          style="width:100%; word-break:break-all;word-wrap:break-word;"
        >
          <el-tag
            v-for="item in user_tags"
            style="margin:5px"
            :key="item.taglist_index"
            :type="warning"
            effect="plain"
            :disable-transitions="true"
            @click="GetTagUser(item.taglist_name)"
          >
            {{ item.taglist_name }}
          </el-tag>
        </div>
        <div v-else style="align-text:center">
          선택된 태그가 없습니다
        </div>
        <br />
        <div>
          <el-collapse v-model="activeName" accordion>
            <el-collapse-item title="Introduction" name="1">
              <el-row :gutter="20">
                <el-col :span="6">
                  <div class="box" style="background: #BDBDBD;">
                    <img
                      class="profile"
                      id="profilephoto"
                      :src="userdata.photofilepath"
                      align="center"
                    />
                  </div>
                </el-col>
                <el-col :span="6" :offset="6">
                  <div style="margin-top:30px">
                    {{ this.userdata.ind_introduce }}
                  </div>
                </el-col>
              </el-row>
            </el-collapse-item>
            <el-collapse-item title="자기소개영상" name="2">
              <div>
                <video
                  :src="userdata.videofilepath"
                  height="360"
                  width="640"
                  controls=""
                  style="width: 100%; height: 100%;"
                ></video>
              </div>
            </el-collapse-item>
            <el-collapse-item title="연락처" name="3">
              <div>Tel : {{ this.userdata.ind_phone }}</div>
              <div>E-mail : {{ this.userdata.ind_email }}</div>
            </el-collapse-item>
            <el-collapse-item title="Documents" name="4">
              <div>
                <!-- 웹뷰어 되는것 확인 -->
                <!-- <webviewer initialDoc="/docx_pdf/test.pdf"></webviewer> -->
                <webviewer :initialDoc="userdata.resumefilepath" />
              </div>
              <div style="text-align: right;">
                <a
                  :href="userdata.resumefilepath"
                  :download="userdata.resume_originfile"
                  id="downatag"
                  >📰 이력서 다운로드</a
                >
              </div>
            </el-collapse-item>
            <el-collapse-item title="reservation" name="5">
              <div style="text-align:center; width:50%; margin: 0 auto;">
                <el-input
                  v-model="reservationdata.sug_duty"
                  placeholder="채용직군을 입력해주세요"
                ></el-input>
              </div>
              <div style="text-align:center; margin:10px">
                <el-date-picker
                  value-format="YYYY-MM-DD HH:mm:ss"
                  v-model="reservationdata.sug_timeone"
                  type="datetime"
                  placeholder="Select date and time"
                >
                </el-date-picker>
              </div>
              <div style="text-align:center; margin:5px">
                <el-date-picker
                  value-format="YYYY-MM-DD HH:mm:ss"
                  v-model="reservationdata.sug_timetwo"
                  type="datetime"
                  placeholder="Select date and time"
                >
                </el-date-picker>
              </div>
              <div style="text-align:center; margin:10px">
                <el-date-picker
                  value-format="YYYY-MM-DD HH:mm:ss"
                  v-model="reservationdata.sug_timethree"
                  type="datetime"
                  placeholder="Select date and time"
                >
                </el-date-picker>
              </div>
              <div>
                <el-input
                  v-model="reservationdata.sug_message"
                  placeholder="전하고싶은 메시지를 입력해주세요"
                ></el-input>
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
  </div>
</template>

<script>
import webviewer from "@/components/MainCompany/webviewer.vue";
import jwt_decode from "jwt-decode";
import axios from "axios";
export default {
  name: "MainCompanyUserInfo",
  components: {
    webviewer,
  },
  created() {},
  props: {
    user: Object,
    userindex: Object,
  },
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 팔로우했는지 체크해보기
    axios
      .post("https://i5d206.p.ssafy.io:8443/fol/check", {
        headers: { Authorization: token },
        fol_type: 1,
        follower: this.userindex,
        following: index,
      })

      .then((res) => {
        // 팔로우가 되어있는것
        if (res.status == 200) {
          this.follow = true;
        }
        if (res.status == 204) {
          this.follow = false;
        }
      })
      .catch((err) => {
        // 팔로우가 안되어있는것
        console.log(err);
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    // 유저정보 가져오기
    axios
      .get(`https://i5d206.p.ssafy.io:8443/poi/${this.userindex}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        var result = res.data[0];
        this.userdata.photofilepath =
          "/file/" + result.photo_savefolder + "/" + result.photo_savefile;
        this.userdata.resumefilepath =
          "/file/" + result.resume_savefolder + "/" + result.resume_savefile;
        this.userdata.videofilepath =
          "/file/" + result.video_savefolder + "/" + result.video_savefile;
        this.userdata.resume_originfile = result.resume_originfile;
        this.userdata.photo_originfile = result.photo_originfile;
        this.userdata.video_originfile = result.video_originfile;
        this.userdata.ind_switch = result.ind_switch;
        this.userdata.ind_introduce = result.ind_introduce;
        this.userdata.photo_index = result.photo_index;
        this.userdata.resume_index = result.resume_index;
        this.userdata.video_index = result.resume_index;
        this.userdata.ind_index = result.ind_index;
        this.userdata.ind_name = result.ind_name;
        this.userdata.ind_email = result.ind_email;
        this.userdata.ind_phone = result.ind_phone;
        this.userdata.ind_gender = result.ind_gender;
      });
    // 유저 태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/has/tag", {
        headers: { Authorization: token },
        params: {
          index: this.userindex,
          type: 0,
        },
      })
      .then((res) => {
        console.log(res);
        this.user_tags = res.data;
      })
      .catch((err) => {
        if (err.response == 401) {
          console.log("token error");
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    return {
      follow: false,
      company_index: index,
      dialogVisible: false,
      // activeNames: ["1"],
      activeName: "1",
      user_tags: [],
      userdata: [
        { ind_name: "" },
        { ind_gender: "" },
        { ind_phone: "" },
        { ind_email: "" },
        { photofilepath: "" },
        { resume_originfile: "" },
        { photo_index: "" },
        { resume_index: "" },
        { video_index: "" },
        { ind_index: 0 },
        { video_originfile: "" },
        { videofilepath: "" },
        { photo_originfile: "" },
        { resumefilepath: "" },
        { ind_switch: "" },
        { ind_introduce: "" },
      ],
      reservationdata: [
        { ent_index: 0 },
        { ind_index: 0 },
        { sug_decision: "string" },
        { sug_duty: "string" },
        { sug_index: 0 },
        { sug_send: "string" },
        { sug_state: "string" },
        { sug_timeone: "string" },
        { sug_timethree: "string" },
        { sug_timetwo: "string" },
        { sug_message: "string" },
      ],
    };
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
              follower: this.userindex,
            },
            headers: { Authorization: this.token },
          })
          .then((res) => {
            console.log(res);
            this.follow = false;
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
      } else if (this.follow == false) {
        console.log("팔로잉");
        axios
          .post("https://i5d206.p.ssafy.io:8443/fol", {
            headers: { Authorization: this.token },
            fol_type: 1,
            following: this.company_index,
            follower: this.userindex,
          })
          .then((res) => {
            console.log(res);
            this.follow = true;
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
      }
    },
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
    successmessage() {
      this.$message({
        message: "Congrats, this is a success message.",
        type: "success",
      });
    },
    interviewrequest() {
      console.log(this.company_index);
      console.log(this.userindex);
      console.log(this.reservationdata.sug_duty);
      console.log(this.reservationdata.sug_timeone);
      console.log(this.reservationdata.sug_timetwo);
      console.log(this.reservationdata.sug_timethree);
      console.log(this.reservationdata.sug_message);
      //요청보내기
      axios
        .post("https://i5d206.p.ssafy.io:8443/sug", {
          headers: { Authorization: this.token },
          ent_index: this.company_index,
          ind_index: this.userindex,
          sug_duty: this.reservationdata.sug_duty,
          sug_timeone: this.reservationdata.sug_timeone,
          sug_timetwo: this.reservationdata.sug_timetwo,
          sug_timethree: this.reservationdata.sug_timethree,
          sug_message: this.reservationdata.sug_message,
        })
        .then((res) => {
          console.log(res);
          this.$message({
            showClose: true,
            message: "면접요청이 발송되었습니다",
            type: "success",
          });
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
    // 해당 태그의 기업들 검색으로
    GetTagUser(keyword) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
        this.$router.push({
          name: "SearchUser",
          query: { keyword: `${keyword}` },
        });
      }, 2000);
      setTimeout(() => {
        location.reload();
      }, 2001);
    },
  },
};
</script>

<style>
.box {
  width: 150px;
  height: 150px;
  /* border-radius: 70%; */
  overflow: hidden;
  margin: 30px;
}
.profile {
  width: 100%;
  height: 100%;
  object-fit: cover;
}
#downatag {
  color: #000;
  text-decoration: none;
  font-size: 18px;
}
</style>
