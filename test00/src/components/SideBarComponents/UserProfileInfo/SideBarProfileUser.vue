<template>
  <el-tabs :tab-position="tabPosition" style="height: 100%;">
    <el-tab-pane label="기본정보"><SideBarProfileUserInfo /></el-tab-pane>
    <!-- <el-tab-pane label="Level of Education"><SideBarProfileUserEducation /></el-tab-pane> -->
    <el-tab-pane label="프로필사진 및 소개"
      ><SideBarProfileUserIntroduction :photofilepath="userdata.photofilepath"
    /></el-tab-pane>
    <el-tab-pane label="소개영상"><PRVideo :vediofilepath="userdata.videofilepath"/></el-tab-pane>
    <el-tab-pane label="태그관리"><SideBarProfileUserTags /></el-tab-pane>
    <el-tab-pane label="서류관리"
      ><SideBarProfileUserDoc :docfilepath="userdata.resumefilepath"
    /></el-tab-pane>
    <el-tab-pane label="회원탈퇴"><DeleteUserAccount /></el-tab-pane>
  </el-tabs>
</template>

<script>
import SideBarProfileUserInfo from "./SideBarProfileUserInfo.vue";
import SideBarProfileUserIntroduction from "./SideBarProfileUserIntroduction.vue";
import SideBarProfileUserTags from "./SideBarProfileUserTags.vue";
import PRVideo from "./PRVideo.vue";
import SideBarProfileUserDoc from "./SideBarProfileUserDoc.vue";
import DeleteUserAccount from "./DeleteUserAccount.vue";
import axios from "axios";
import jwt_decode from "jwt-decode";
export default {
  name: "SideBarProfileUser",
  components: {
    SideBarProfileUserInfo,
    SideBarProfileUserIntroduction,
    SideBarProfileUserTags,
    PRVideo,
    SideBarProfileUserDoc,
    DeleteUserAccount,
  },
  data() {
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    axios
      .get(`https://i5d206.p.ssafy.io:8443/poi/${index}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        this.userdata.photofilepath =
          "/file/" + res.data.photo_savefolder + "/" + res.data.photo_savefile;
        this.userdata.resumefilepath =
          "/file/" + res.data.resume_savefolder + "/" + res.data.resume_savefile;
        this.userdata.videofilepath =
          "/file/" + res.data.video_savefolder + "/" + res.data.video_savefile;
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
    return {
      tabPosition: "left",
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
        { ind_career: "" },
      ],
    };
  },
};
</script>

<style></style>
