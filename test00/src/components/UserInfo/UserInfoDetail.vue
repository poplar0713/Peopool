<template>
  <el-text
    type="text"
    @click="dialogVisible = true"
    style="color:black; text-align:center; cursor:pointer"
    size="mini"
    >유저상세보기
  </el-text>

  <el-dialog center v-model="dialogVisible" width="60%">
    <div style="text-align: center; font-size: 1.3rem; margin-bottom: 1rem">
      <span>{{ this.userdata.ind_name }}님의 프로필</span>
      <span> </span>
    </div>
    <div style="text-align:center; margin-bottom: 2rem">
      <el-tag
        type="warning"
        v-for="item in this.ind_taglist"
        v-bind:key="item"
        style="margin-right: 0.5rem; cursor:pointer"
        @click="GetTagUser(item.taglist_name)"
        >{{ item.taglist_name }}</el-tag
      >
    </div>
    <div>
      <el-tabs type="border-card">
        <el-tab-pane label="소개" style="padding : 2%">
          <el-row>
            <el-col :span="12"
              ><span> <img :src="this.userdata.photofilepath" /> </span
            ></el-col>
            <el-col :span="8"
              ><span>
                <h4>
                  성명 : {{ this.userdata.ind_name }}({{
                    this.userdata.ind_gender
                  }})
                </h4>
                <h4>직무 : {{ this.userdata.cat_name }}</h4>
                <h4>경력 : {{ this.userdata.car_value }}</h4>
              </span></el-col
            >
            <el-divider></el-divider>
          </el-row>
          <h3>자기소개</h3>
          <div>{{ this.userdata.ind_introduce }}</div>
        </el-tab-pane>
        <el-tab-pane label="연락처" style="padding : 2%">
          <div style="text-align:center">
            <h4>연락처 : {{ this.userdata.ind_phone }}</h4>
            <h4>이메일: {{ this.userdata.ind_email }}</h4>
          </div>
        </el-tab-pane>
        <el-tab-pane label="이력서">
          <webviewer :initialDoc="userdata.resumefilepath" />
        </el-tab-pane>
        <el-tab-pane label="PR 영상">
          <video
            :src="userdata.videofilepath"
            height="360"
            width="640"
            controls=""
            style="width: 100%; height: 100%;"
          ></video>
        </el-tab-pane>
      </el-tabs>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button
          v-if="this.follow"
          round
          type="danger"
          @click="clickfollowBtn"
          ><i class="fas fa-heart"></i>&nbsp;&nbsp;팔로잉</el-button
        >
        <el-button v-else type="danger" plain round @click="clickfollowBtn"
          ><i class="far fa-heart"></i>&nbsp;&nbsp;팔로우</el-button
        >
        <el-button type="success" plain round @click="this.innerVisible = true"
          >면접 제안</el-button
        >
      </span>
    </template>
  </el-dialog>
  <!-- 면접제안하는부분 -->
  <el-dialog
    width="40%"
    v-model="innerVisible"
    append-to-body
    style="padding: 2%; text-align:center;"
  >
    <h2>면접 제안하기</h2>
    <div style="text-align:center; width:30%; margin: 0 auto;">
      <h3>1. 직군 입력</h3>
      <el-input
        v-model="this.reservationdata.sug_duty"
        placeholder="채용직군을 입력해주세요"
      ></el-input>
    </div>
    <el-divider></el-divider>
    <div div style="text-align:center;">
      <h3>2. 제안할 면접일 선택</h3>
      <span style="text-align:center; margin:10px">
        <el-date-picker
          value-format="YYYY-MM-DD HH:mm:ss"
          v-model="this.reservationdata.sug_timeone"
          type="datetime"
          placeholder="면접 일정 후보 1"
        >
        </el-date-picker>
      </span>
      <span style="text-align:center; margin:5px">
        <el-date-picker
          value-format="YYYY-MM-DD HH:mm:ss"
          v-model="this.reservationdata.sug_timetwo"
          type="datetime"
          placeholder="면접 일정 후보 2"
        >
        </el-date-picker>
      </span>
      <span style="text-align:center; margin:10px">
        <el-date-picker
          value-format="YYYY-MM-DD HH:mm:ss"
          v-model="reservationdata.sug_timethree"
          type="datetime"
          placeholder="면접 일정 후보 3"
        >
        </el-date-picker>
      </span>
    </div>
    <el-divider></el-divider>
    <div style="text-align: center; margin: 3%">
      <h3>3. 메세지 입력</h3>
      <el-input
        type="textarea"
        :rows="3"
        v-model="reservationdata.sug_message"
        placeholder="전하고 싶은 메시지를 입력해주세요"
      ></el-input>
    </div>
    <div style="text-align:center; margin: 4%">
      <el-button
        @click="(dialogVisible = false), interviewrequest()"
        type="success"
        :plain="true"
        >요청 보내기</el-button
      >
    </div>
  </el-dialog>
</template>

<script>
import axios from "axios";
import webviewer from "@/components/MainCompany/webviewer.vue";
import jwt_decode from "jwt-decode";

export default {
  name: "UserInfoDetail",
  props: ["userindex"],
  components: {
    webviewer,
  },
  data() {
    return {
      dialogVisible: false,
      innerVisible: false,
      ind_taglist: [],
      isHover: false,
      follow: "",
      companyindex: "",
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
        { cat_name: "" },
        { car_value: "" },
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

  mounted() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.companyindex = index;
    // 팔로우했는지 체크해보기

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
        this.userdata.cat_name = result.cat_name;
        this.userdata.car_value = result.car_value;
      });

    axios
      .post("https://i5d206.p.ssafy.io:8443/fol/check", {
        headers: { Authorization: token },
        fol_type: 1,
        follower: this.userindex,
        following: this.companyindex,
      })

      .then((res) => {
        // 팔로우가 되어있는것
        if (res.status == 200) {
          this.follow = true;
        }
        if (res.status == 204) {
          this.follow = false;
        }
      });

    axios
      .get("https://i5d206.p.ssafy.io:8443/has/tag", {
        params: {
          index: this.userindex,
          type: 0,
        },
      })
      .then((res) => {
        this.ind_taglist = res.data;
      })
      .catch((err) => {
        console.log(err.response);
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          console.log("token error");
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
  methods: {
    changeFront() {
      this.isHover = false;
    },
    changeBack() {
      this.isHover = true;
    },
    clickfollowBtn() {
      if (this.follow) {
        console.log("팔로우 해제");
        axios
          .delete("https://i5d206.p.ssafy.io:8443/fol", {
            data: {
              fol_type: 1,
              following: this.companyindex,
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
            following: this.companyindex,
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
    interviewrequest() {
      //요청보내기
      axios
        .post("https://i5d206.p.ssafy.io:8443/sug", {
          headers: { Authorization: this.token },
          ent_index: this.companyindex,
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

      this.innerVisible = false;
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

<style></style>
