<template>
  <el-container class="mainLayout">
    <el-aside width="250px"><SideBarCompany /></el-aside>
    <el-container>
      <!-- 검색 -->
      <el-header><headerSearchUser /></el-header>
      <!--  -->
      <el-header><h2>채용 프로세스 관리</h2></el-header>
      <el-main>
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
              <el-tabs :tab-position="tabposition">
                <el-tab-pane label="면접 대기자 관리">
                  <el-scrollbar height="720px">
                    <el-timeline>
                      <el-timeline-item
                        placement="top"
                        v-for="(days, index) in InterviewDays"
                        :key="index"
                        :timestamp="days.date"
                      >
                        <el-card style="width: 80%; align-content: center;">
                          <el-table
                            :data="days.interviewers"
                            :default-sort="{ prop: 'time' }"
                          >
                            <el-table-column
                              prop="int_start"
                              label="면접시간"
                              sortable
                            >
                            </el-table-column>
                            <el-table-column prop="name" label="성명">
                            </el-table-column>
                            <el-table-column prop="p_part" label="직무">
                            </el-table-column>
                            <el-table-column label="" prop="p_name">
                              <template #default="scope">
                                <el-button
                                  size="mini"
                                  @click="
                                    Cancel(
                                      scope.$index,
                                      scope.row,
                                      scope.row.p_name
                                    )
                                  "
                                  >Cancel</el-button
                                >
                                <!-- {{scope.row.company}} -->
                                <el-button
                                  size="mini"
                                  type="danger"
                                  @click="GoToInteriewRoom(scope.row.name)"
                                  >Interview Room</el-button
                                >
                                <!-- {{scope.row.url}} -->
                              </template>
                            </el-table-column>
                          </el-table></el-card
                        >
                      </el-timeline-item>
                    </el-timeline>
                  </el-scrollbar>
                </el-tab-pane>
                <el-tab-pane label="일정 📅">
                  <InterviewCalender />
                </el-tab-pane>
              </el-tabs>
            </el-tab-pane>
            <!-- 심사중  -->
            <el-tab-pane label="심사" name="third">
              <h3>심사 중인 면접자 : {{ getExaiminingLength }}명</h3>
              <!--  -->
              <el-scrollbar height="720px">
                <el-row :gutter="24">
                  <el-col :span="8" v-for="(item, i) in exaimining" :key="i">
                    <ExamineCard :item="item" />
                  </el-col>
                </el-row>
              </el-scrollbar>
            </el-tab-pane>
            <!--  -->
            <el-tab-pane label="입사 제안 중" name="fourth"> </el-tab-pane>
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
import InterviewCalender from "../components/RecrutingBoard/InterviewCalender.vue";
import RecruitingBoardOfferTab from "../components/RecrutingBoard/RecruitingBoardOfferTab.vue";
import RecruitingBoardRejectOfferTab from "../components/RecrutingBoard/RecruitingBoardRejectOfferTab.vue";
import jwt_decode from "jwt-decode";
import axios from "axios";
export default {
  name: "Recruiting",
  components: {
    SideBarCompany,
    headerSearchUser,
    ExamineCard,
    InterviewCalender,
    RecruitingBoardOfferTab,
    RecruitingBoardRejectOfferTab,
  },
  computed: {
    getExaiminingLength() {
      return this.exaimining.length;
    },

    getInterviewDays() {
      var InterviewDays = {};

      return InterviewDays;
    },
  },
  data() {
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    // 면접일정조회
    axios
      .get(`https://i5d206.p.ssafy.io:8443/int/ent/${index}`, {
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
      company: "로그인된기업",
      waitinglist: [],
      InterviewDays: [],
      exaimining: [],
      followData: [],

      exaiminingtotal: this.getExaiminingLength,
    };
  },
  mounted() {
    const token = localStorage.getItem("token");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.company_index = index;

    axios
      .get(`https://i5d206.p.ssafy.io:8443/sug/ent/${this.company_index}`, {
        // 면접 수락 대기
        headers: { Authroization: token },
      })
      .then((res) => {
        console.log(res.data);
        this.waitinglist = res.data;
      })
      .catch((err) => {
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });

    axios
      .get(
        `https://i5d206.p.ssafy.io:8443/int/ent/iday/${this.company_index}`,
        {
          // 면접 대기자
          headers: { Authroization: token },
        }
      )
      .then((res) => {
        console.log("interview >> ");
        console.log(res.data);
        this.InterviewDays = res.data;
      })
      .catch((err) => {
        if (err.response.data.status == 401) {
          this.$message.error("로그인세션이 만료되었습니다");
          localStorage.clear();
          this.$router.push("/");
        }
      });
  },
  methods: {
    // 인터뷰룸으로 이동
    GoToInteriewRoom(row) {
      // console.log(p_ind);
      this.$router.push({
        name: "InterviewRoom",
        params: { company: this.company, user: row },
      });
    },
  },
};
</script>
