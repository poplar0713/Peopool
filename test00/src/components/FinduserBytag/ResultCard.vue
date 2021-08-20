<template>
  <!-- <div
    class="nameCard"
    :class="[isHover ? 'backside' : 'frontside']"
    @click="this.dialogVisible = true"
    @mouseover="changeBack"
    @mouseleave="changeFront"
  >
    <el-row style="text-align: center;">
      <el-col :span="10">
        <div v-if="userdata.photo_index == ''">
          <img src="https://i5d206.p.ssafy.io/file/thumbuser.png" />
        </div>
        <div v-else>
          <img src="this.userdata.photofilepath" />
        </div>
      </el-col>
      <el-col :span="14">
        <div>
          <h3>{{ this.userdata.ind_name }}</h3>
        </div>
        <div>
          <h3>직무 : {{ this.userdata.cat_name }}</h3>
        </div>
        <div>
          <h3>[{{ this.userdata.car_value }}]</h3>
        </div>
      </el-col>
    </el-row>
  </div> -->
  <el-card
    shadow="hover"
    style="margin-bottom:20px; text-align:center"
    @click="dialogVisible = true"
  >
    <el-row>
      <el-col :span="12">
        <div>
          <img :src="userdata.photofilepath" style="max-width: 100%; height: auto;" />
        </div>
      </el-col>
      <el-divider direction="vertical"></el-divider>
      <el-col :span="12">
        <div>
          <p>{{ this.userdata.ind_name }}</p>
          <p>{{ this.userdata.cat_name }}</p>
          <p>{{ this.userdata.car_value }}</p>
        </div>
      </el-col>
    </el-row>
  </el-card>

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
        style="margin-right: 0.5rem"
        @click="GetTagUser(item.taglist_name)"
        >{{ item.taglist_name }}</el-tag
      >
    </div>
    <div>
      <el-tabs type="border-card">
        <el-tab-pane label="소개" style="padding : 2%">
          <el-row>
            <el-col :span="12"
              ><span v-if="userdata.photo_index == ''"
                ><img src="https://i5d206.p.ssafy.io/file/thumbuser.png"
              /></span>
              <span v-else><img src="this.userdata.photofilepath" /> </span>
            </el-col>
            <el-col :span="8"
              ><span>
                <h4>성명 : {{ this.userdata.ind_name }}</h4>
                <h4>직무 : {{ this.userdata.cat_name }} ({{ this.userdata.car_value }})</h4>
              </span></el-col
            >
            <el-divider></el-divider>
          </el-row>
          <h3>자기소개</h3>
          <div>{{ this.userdata.ind_introduce }}</div>
        </el-tab-pane>
        <el-tab-pane label="이력서">
          <div v-if="userdata.resume_index == ''" class="fileDoc">
            등록된 이력서 및 포트폴리오가 없습니다.
          </div>
          <div v-else>
            <webviewer v-if="userdata.resumefilepath" :initialDoc="userdata.resumefilepath" />
          </div>
        </el-tab-pane>
        <el-tab-pane label="PR 영상">
          <div v-if="userdata.video_index == ''">
            소개영상이 없습니다.
          </div>
          <div v-else>
            <span v-if="userdata.videofilepath"> <video src="userdata.videofilepath" /> </span>
          </div>
        </el-tab-pane>
      </el-tabs>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button v-if="this.follow" plain round type="danger" @click="clickfollowBtn"
          >팔로우 해제</el-button
        >
        <el-button v-else type="danger" plain round @click="clickfollowBtn">팔로우</el-button>
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
      <el-button @click="(dialogVisible = false), interviewrequest()" type="success" :plain="true"
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
        
        if (err.response == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          this.$cookies.remove("PID_AUTH");
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
        
        axios
          .delete("https://i5d206.p.ssafy.io:8443/fol", {
            data: {
              fol_type: 1,
              following: this.companyindex,
              follower: this.userindex,
            },
            headers: { Authorization: this.$store.state.usertoken },
          })
          .then((res) => {
            console.log(res);
            this.follow = false;
          })
          .catch((err) => {
            
            if (err.response == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
              this.$cookies.remove("PID_AUTH");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      } else if (this.follow == false) {
        
        axios
          .post("https://i5d206.p.ssafy.io:8443/fol", {
            headers: { Authorization: this.$store.state.usertoken },
            fol_type: 1,
            following: this.companyindex,
            follower: this.userindex,
          })
          .then((res) => {
            console.log(res);
            this.follow = true;
          })
          .catch((err) => {
            
            if (err.response == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
              this.$cookies.remove("PID_AUTH");
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
          headers: { Authorization: this.$store.state.usertoken },
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
          
          if (err.response == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            this.$cookies.remove("PID_AUTH");
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

<style>
@import url("https://fonts.googleapis.com/css2?family=Gothic+A1&display=swap");
.nameCard {
  width: 380px;
  height: 180px;
  font-size: 1rem;
  font-family: "Gothic A1", sans-serif;
  cursor: pointer;
  padding: 1rem;
  border-radius: 3%;
}
.frontside {
  border-width: 0.2rem;
  border-color: gold;
  border-style: solid;
}
.backside {
  border-width: 0.2rem;
  border-style: solid;
  background-color: gold;
  color: purple;
  box-shadow: 10px 5px 5px purple;
}
</style>
