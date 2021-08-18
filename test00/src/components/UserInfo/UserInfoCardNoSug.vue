<template>
  <el-card
    shadow="hover"
    style="margin-bottom:20px; cursor:pointer; height:110%"
    @click="dialogVisible = true"
  >
    <el-row>
      <el-col :span="8">
        <div>
          <span v-if="this.userdata.photo_index">
            <img :src="this.userdata.photofilepath" style="width:100%; heigth: auto" />
          </span>
          <span v-else>
            <img :src="this.nonImage" style="width:100%; heigth: auto" />
          </span>
        </div>
      </el-col>
      <el-col :span="16"
        ><div>
          <div>
            <h3 style="margin-top:0">{{ this.userdata.ind_name }}</h3>
          </div>
          <div>직무 - {{ this.userdata.cat_name }}</div>
          <div>{{ this.userdata.car_value }}</div>
        </div></el-col
      >
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
              ><div>
                <span v-if="this.userdata.photo_index == '-'">
                  <el-image :src="this.nonImage"
                /></span>
                <span v-else>
                  <el-image :src="this.userdata.photofilepath" />
                </span></div
            ></el-col>
            <el-col :span="8"
              ><span>
                <h4>성명 : {{ this.userdata.ind_name }}</h4>
                <h4>
                  직무 : {{ this.userdata.cat_name }} ({{
                    this.userdata.car_value
                  }})
                </h4>
              </span></el-col
            >
            <el-divider></el-divider>
          </el-row>
          <h3>자기소개</h3>
          <div>{{ this.userdata.ind_introduce }}</div>
          <el-divider></el-divider>
          <div>
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
      </span>
    </template>
  </el-dialog>
</template>

<script>
import axios from "axios";
import webviewer from "@/components/MainCompany/webviewer.vue";
import jwt_decode from "jwt-decode";

export default {
  name: "UserInfoCard",
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
      nonImage: "https://i5d206.p.ssafy.io/file/thumbuser.png",
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
      .get(`https://i5d206.p.ssafy.io/poi/${this.userindex}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.userdata.photofilepath =
          "https://i5d206.p.ssafy.io/file/" +
          res.data.photo_savefolder +
          "/" +
          res.data.photo_savefile;
        this.userdata.resumefilepath =
          "https://i5d206.p.ssafy.io/file/" +
          res.data.resume_savefolder +
          "/" +
          res.data.resume_savefile;
        this.userdata.videofilepath =
          "https://i5d206.p.ssafy.io/file/" +
          res.data.video_savefolder +
          "/" +
          res.data.video_savefile;
        this.userdata.resume_originfile = res.data.resume_originfile;
        this.userdata.photo_originfile = res.data.photo_originfile;
        this.userdata.video_originfile = res.data.video_originfile;
        this.userdata.ind_switch = res.data.ind_switch;
        this.userdata.ind_introduce = res.data.ind_introduce;
        this.userdata.photo_index = res.data.photo_index;
        this.userdata.resume_index = res.data.resume_index;
        this.userdata.video_index = res.data.resume_index;
        this.userdata.ind_index = res.data.ind_index;
        this.userdata.ind_name = res.data.ind_name;
        this.userdata.ind_email = res.data.ind_email;
        this.userdata.ind_phone = res.data.ind_phone;
        this.userdata.ind_gender = res.data.ind_gender;
        this.userdata.cat_name = res.data.cat_name;
        this.userdata.car_value = res.data.car_value;
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
            if (err.response == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
              localStorage.clear();
              this.$cookies.remove("PID_AUTH");
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
            if (err.response == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
              this.$cookies.remove("PID_AUTH");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }
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
