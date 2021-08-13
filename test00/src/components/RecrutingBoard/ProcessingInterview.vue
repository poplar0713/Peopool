<template>
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
                <el-table-column prop="int_start" label="면접시간" sortable>
                </el-table-column>
                <el-table-column prop="name" label="성명"> </el-table-column>
                <el-table-column prop="p_part" label="직무"> </el-table-column>
                <el-table-column label="" prop="p_name">
                  <template #default="scope">
                    <el-button
                      size="mini"
                      @click="Cancel(scope.$index, scope.row, scope.row.p_name)"
                      >Cancel</el-button
                    >
                    <!-- {{scope.row.company}} -->
                    <el-button
                      size="mini"
                      type="danger"
                      @click="
                        GoToInteriewRoom(
                          scope.row.company_name,
                          scope.row.int_roomnumber
                        )
                      "
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
</template>

<script>
import InterviewCalender from "@/components/RecrutingBoard/InterviewCalender.vue";
import jwt_decode from "jwt-decode";
import axios from "axios";
export default {
  name: "ProcessingInterview",
  components: { InterviewCalender },
  computed: {
    getInterviewDays() {
      var InterviewDays = {};
      return InterviewDays;
    },
  },
  mounted() {
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    this.company_index = index;
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
  data() {
    return {
      now: new Date(),
      tabposition: "right",
      company: "로그인된기업",
      InterviewDays: [],
    };
  },
  methods: {
    // 인터뷰룸으로 이동
    GoToInteriewRoom(company, url) {
      this.$router.push({
        name: "InterviewRoom",
        params: { company: company, url: url },
      });
    },
  },
};
</script>

<style></style>
