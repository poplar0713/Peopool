<template>
  <h3 style="text-align:center">해당 태그에 다음 피풀인이 기업의 많은 관심을 받고 있어요!</h3>
  <br>
  <el-carousel :interval="4000" type="card" height="200px">
    <el-carousel-item v-for="item in result" :key="item">
      <el-row @click="(dialogUser = true), getuserdata(item)">
        <el-col :span="8"
          ><div class="grid-content bg-purple">
            <img src="images/a.jpg" alt="" /></div
        ></el-col>
        <el-col :span="16"
          ><div class="grid-content bg-purple-light">
            {{ item.ind_name }}
          </div></el-col
        >
      </el-row>
    </el-carousel-item>
  </el-carousel>
  <!--  -->
  <div style="text-align:center">
    <el-dialog
      :title="this.userdata.ind_name"
      v-model="dialogUser"
      width="50%"
      style="color:black"
      ><!-- 팔로우가 되어있을때 -->
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
      <div>
        <el-collapse v-model="activeName" accordion>
          <el-collapse-item title="Introduction" name="1">
            <div>
              {{ this.userdata.ind_introduce }}
            </div>
          </el-collapse-item>
          <el-collapse-item title="자기소개영상" name="2">
            <div>
              {{ this.userdata.ind_video }}
            </div>
          </el-collapse-item>
          <el-collapse-item title="연락처" name="3">
            <div>Tel : {{ this.userdata.ind_phone }}</div>
            <div>E-mail : {{ this.userdata.ind_email }}</div>
          </el-collapse-item>
          <el-collapse-item title="Documents" name="4">
            <div>
              <webviewer initialDoc="파이팅 프런트.docx" />
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
              @click="(dialogUser = false), interviewrequest()"
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
          <el-button @click="dialogUser = false">Cancel</el-button>
        </span>
      </template>
    </el-dialog>
  </div>
  <!--  -->
  <h2># {{ $route.params.keyword }} 의 검색결과</h2>
  <el-row :gutter="24">
    <el-col :span="6" v-for="item in nowPageData" :key="item">
      <UserCard :item="item"></UserCard>
    </el-col>
  </el-row>
  <div style="text-align:center">
    <el-pagination
      layout="prev, pager, next"
      :total="result.length"
      :page-size="8"
      :page-sizes="[8, 16, 24, 32]"
      v-model:currentPage="nowPage"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import jwt_decode from "jwt-decode";
// import { useRoute } from "vue-router";
import UserCard from "@/components/search/UserCard.vue";

export default {
  name: "SearchUser",
  components: {
    UserCard,
  },
  data() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    return {
      company_index: index,
      dialogUser: false,
      userdata: [],
      activeName: "1",
      follow: false,
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
    getuserdata(item) {
      this.userdata = item;
      // 팔로우했는지 체크해보기
      axios
        .post("https://i5d206.p.ssafy.io:8443/fol/check", {
          headers: { Authorization: this.token },
          fol_type: 1,
          follower: item.ind_index,
          following: this.company_index,
        })

        .then((res) => {
          // 팔로우가 되어있는것
          console.log(res), (this.follow = true);
        })
        .catch((err) => {
          // 팔로우가 안되어있는것
          console.log(err);
          this.follow = false;
          if (err.response.data.status == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
    interviewrequest() {
      console.log(this.company_index);
      console.log(this.item.ind_index);
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
          ind_index: this.item.ind_index,
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
          console.log(err.response.data.status);
          if (err.response.data.status == 401) {
            this.$message.error("로그인세션이 만료되었습니다");
            localStorage.clear();
            this.$router.push("/");
          }
        });
    },
    clickfollowBtn() {
      if (this.follow) {
        console.log("팔로우 해제");
        axios
          .delete("https://i5d206.p.ssafy.io:8443/fol", {
            data: {
              fol_type: 1,
              following: this.company_index,
              follower: this.userdata.ind_index,
            },
            headers: { Authorization: this.token },
          })
          .then((res) => {
            console.log(res);
            this.follow = false;
          })
          .catch((err) => {
            console.log("token error");
            console.log(err.response.data.status);
            if (err.response.data.status == 401) {
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
            follower: this.userdata.ind_index,
          })
          .then((res) => {
            console.log(res);
            this.follow = true;
          })
          .catch((err) => {
            console.log("token error");
            console.log(err.response.data.status);
            if (err.response.data.status == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }
    },
  },
  setup() {
    // 토큰가져오기
    const token = localStorage.getItem("token");
    // const route = useRoute();
    // const keyword = route.params.keyword;
    // 데이터저장 비동기방식
    const result = ref([]);
    (async () => {
      const res = await axios.get(`https://i5d206.p.ssafy.io:8443/poi/`, {
        headers: { Authorization: token },
      });
      result.value = res.data;
      console.log(res);
      initData();
    })();
    //
    console.log(result);
    //

    let tableSize = 8;
    let nowPage = ref(1);
    let nowPageData = ref(result.value);
    console.log(nowPageData);

    function initData() {
      console.log(1);
      sliceData();
    }

    function handleSizeChange(val) {
      console.log(2);
      tableSize = val;
      sliceData();
    }

    function handleCurrentChange(val) {
      console.log(3);
      nowPage.value = val;
      sliceData();
    }

    function sliceData() {
      console.log(4);
      nowPageData.value = result.value.slice(
        tableSize * nowPage.value - tableSize,
        tableSize * nowPage.value
      );
    }

    return {
      nowPage,
      result,
      handleSizeChange,
      handleCurrentChange,
      tableSize,
      nowPageData,
    };
  },
};
</script>

<style></style>
