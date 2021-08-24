<template>
  <el-card
    shadow="hover"
    style="margin-bottom:20px; cursor:pointer"
    @click="dialogVisible = true"
  >
    <el-row>
      <el-col :span="6">
        <div style="margin:0 auto;">
          <span v-if="this.userdata.photo_index">
            <div
              style="overflow:hidden;  width: 80px;
    height: 80px; 
    border-radius: 70%;"
            >
              <img
                :src="this.userdata.photofilepath"
                style=" object-fit: cover; height:100%; width:100%; "
              />
            </div>
            <!-- <el-avatar
              :src="this.userdata.photofilepath"
              :size="70"
              style="-webkit-transform:scale(1.1);"
            ></el-avatar> -->
          </span>

          <span v-else>
            <img :src="this.nonImage" style="width:100%; heigth:auto" />
          </span>
        </div>
      </el-col>
      <el-col :span="18"
        ><div style=" margin-left:20px">
          <div>
            <h3 style="margin-top:0">
              <UserInfoName :userindex="item.ind_index" />
            </h3>
          </div>
          <el-button
            type="success"
            plain
            round
            :disabled="disablecard == true"
            @click="intpass(item.int_index)"
            >입사제안</el-button
          >
          <el-button
            type="danger"
            plain
            round
            :disabled="disablecard == true"
            @click="intfail(item.int_index)"
            >탈락</el-button
          >
        </div></el-col
      >
    </el-row>
  </el-card>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import UserInfoName from "@/components/UserInfo/UserInfoName.vue";

export default {
  name: "ExamineCard",
  components: {
    UserInfoName,
  },
  props: ["item"],
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;

    axios
      .get(`https://i5d206.p.ssafy.io:8443/poi/${this.item.ind_index}`, {
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
    return {
      InterviewDialogVisible: false,
      company_index: index,
      disablecard: false,
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
    };
  },
  methods: {
    intpass(intindex) {
      console.log(intindex);
      axios
        .put(`https://i5d206.p.ssafy.io:8443/int/pass?index=${intindex}`, {
          headers: { Authorization: this.$store.state.usertoken },
        })
        .then(() => {
          this.disablecard = true;
        });
    },
    intfail(intindex) {
      console.log(intindex);
      axios
        .put(`https://i5d206.p.ssafy.io:8443/int/fail?index=${intindex}`, {
          headers: { Authorization: this.$store.state.usertoken },
        })
        .then(() => {
          this.disablecard = true;
        });
    },
  },
};
</script>

<style>
.box-card {
  border-width: 3px;
  border-radius: 10px 10px 10px 10px;
  border-color: #fce08c;
  border-style: solid;
  /* box-shadow: 5px 5px 5px 5px wheat; */
}
</style>
