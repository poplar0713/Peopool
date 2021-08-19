<template>
  <el-card
    shadow="hover"
    style="margin-bottom:20px; cursor:pointer"
    @click="dialogVisible = true"
  >
    <el-row>
      <el-col :span="8">
        <div>
          <img
            :src="userdata.photofilepath"
            style="max-width: 100%; height: auto;"
          />
        </div>
      </el-col>
      <el-col :span="16"
        ><div>
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
