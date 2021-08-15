<template>
  <el-container class="mainLayout">
    <el-aside width="250px"><SideBarCompany /></el-aside>
    <el-container>
      <!-- 검색 -->
      <el-header><headerSearchUser /></el-header>
      <!--  -->
      <el-header><h2>채용 프로세스 관리</h2></el-header>
      <el-main style="margin:0 auto; width:105%">
        <div class="mainBoard">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="미응답" name="offerTab">
              <RecruitingBoardOfferTab />
            </el-tab-pane>
            <!--  -->
            <el-tab-pane label="거절된 면접" name="rejectedoffer">
              <RecruitingBoardRejectOfferTab />
            </el-tab-pane>
            <!--  -->
            <el-tab-pane label="면접 진행" name="interviewTab">
              <ProcessingInterview />
            </el-tab-pane>
            <!-- 심사중  -->
            <el-tab-pane label="심사" name="third">
              <!--  -->
              <div>
                <el-row :gutter="24">
                  <el-col :span="6" v-for="(item, i) in exaimining" :key="i">
                    <ExamineCard :item="item" v-if="item.int_done == null" />
                  </el-col>
                </el-row>
              </div>
            </el-tab-pane>
            <!--  -->
            <el-tab-pane label="입사 제안 중" name="fourth">
              <PassUser />
            </el-tab-pane>
          </el-tabs>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
<style>
.mainBoard {
  margin-right: 10%;
}
</style>
<script>
import SideBarCompany from "../components/SideBarComponents/SideBarCompany.vue";
import headerSearchUser from "../components/SideBarComponents/headerSearchUser.vue";
import ExamineCard from "../components/RecrutingBoard/ExamineCard.vue";
import RecruitingBoardOfferTab from "../components/RecrutingBoard/RecruitingBoardOfferTab.vue";
import RecruitingBoardRejectOfferTab from "../components/RecrutingBoard/RecruitingBoardRejectOfferTab.vue";
import ProcessingInterview from "../components/RecrutingBoard/ProcessingInterview.vue";
import PassUser from "../components/RecrutingBoard/PassUser.vue";
import jwt_decode from "jwt-decode";
import axios from "axios";
export default {
  name: "Recruiting",
  components: {
    SideBarCompany,
    headerSearchUser,
    ExamineCard,
    RecruitingBoardOfferTab,
    RecruitingBoardRejectOfferTab,
    ProcessingInterview,
    PassUser,
  },
  data() {
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 지난면접일정조회
    axios
      .get(`https://i5d206.p.ssafy.io:8443/int/ent/last/${index}`, {
        headers: { Authorization: token },
      })
      .then((res) => {
        console.log(res);
        this.exaimining = res.data;
      })
      .catch((err) => {
        console.log("token error");
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
    return {
      activeName: "offerTab",
      now: new Date(),
      tabposition: "right",
      exaimining: [],
    };
  },
};
</script>
