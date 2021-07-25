<template>
  <el-container class="mainLayout">
    <el-aside width="250px"><SideBarCompany /></el-aside>
    <el-container>
      <el-header><Header /></el-header>
      <el-header><h2>채용 프로세스 관리</h2></el-header>
      <el-main>
        <div class="mainBoard">
          <el-tabs v-model="activeName" @tab-click="handleClick">
            <el-tab-pane label="면접 제안 중" name="offerTab"
              ><h3>면접 수락 대기</h3>
              <el-table :data="waitinglist" :default-sort="{ prop: 'p_name' }">
                <el-table-column prop="p_name" label="성명" fixed> </el-table-column>
                <el-table-column prop="p_part" label="직무" fixed> </el-table-column>
                <el-table-column prop="date1" label="면접 제안 시간"> </el-table-column>
                <el-table-column prop="date2" label=""> </el-table-column>
                <el-table-column prop="date3" label=""> </el-table-column>
              </el-table>
            </el-tab-pane>
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
                          <el-table :data="days.Interviewer" :default-sort="{ prop: 'time' }">
                            <el-table-column prop="time" label="면접시간" sortable>
                            </el-table-column>
                            <el-table-column prop="p_name" label="성명"> </el-table-column>
                            <el-table-column prop="p_part" label="직무"> </el-table-column>
                            <el-table-column label="" prop="p_ind">
                              <el-button @click="enterInterViewRoom(p_ind)" type="warning"
                                >면접장 입장</el-button
                              >
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
            <el-tab-pane label="심사" name="third">
              <h3>심사 중인 면접자 : {{ getExaiminingLength }}명</h3>
              <el-scrollbar height="720px">
                <span v-for="(item, i) in exaimining" :key="i">
                  <ExamineCard
                    :p_name="item.p_name"
                    :part="item.p_part"
                    :p_img="item.p_img"
                    :app_url="item.p_appurl"
                    :interviewTime="item.interviewTime"
                  />
                </span>
              </el-scrollbar>
            </el-tab-pane>
            <el-tab-pane label="입사 제안 중" name="fourth"> </el-tab-pane>
          </el-tabs>
        </div>
      </el-main>
      <el-main>
        <applicant-list title="현재 팔로우 중인 지원자" :followData="followData" />
      </el-main>
    </el-container>
  </el-container>
</template>
<style>
.followList {
  height: 20%;
}
.mainBoard {
  margin-right: 10%;
}
</style>
<script>
import SideBarCompany from "../components/SideBarComponents/SideBarCompany.vue";
import Header from "../components/SideBarComponents/header.vue";
import ApplicantList from "@/components/MainCompany/ApplicantList.vue";
import ExamineCard from "../components/RecrutingBoard/ExamineCard.vue";
import InterviewCalender from "../components/RecrutingBoard/InterviewCalender.vue";

export default {
  components: {
    SideBarCompany,
    Header,
    ApplicantList,
    ExamineCard,
    InterviewCalender,
  },
  data() {
    return {
      now: new Date(),
      tabposition: "right",
      waitinglist: [
        {
          p_name: "천서진",
          p_part: "광고",
          date1: "2021-08-09 11:00",
          date2: "2021-08-10 11:00",
          date3: "2021-08-11 11:00",
        },
        {
          p_name: "주단태",
          p_part: "영업",
          date1: "2021-08-09 11:00",
          date2: "2021-08-10 11:00",
          date3: "2021-08-11 11:00",
        },
        {
          p_name: "심수련",
          p_part: "영업",
          date1: "2021-08-09 13:00",
          date2: "2021-08-10 13:00",
          date3: "2021-08-11 13:00",
        },
        {
          p_name: "주석경",
          p_part: "영업",
          date1: "2021-08-09 14:00",
          date2: "2021-08-10 14:00",
          date3: "2021-08-11 14:00",
        },
        {
          p_name: "배로나",
          p_part: "영업",
          date1: "2021-08-09 15:00",
          date2: "2021-08-10 15:00",
          date3: "2021-08-11 15:00",
        },
      ],
      InterviewDays: [
        {
          date: "2021-08-01",
          Interviewer: [
            {
              time: "13:00",
              p_ind: 0,
              p_name: "문영화",
              p_part: "FrontEnd",
            },
            {
              time: "14:00",
              p_ind: 1,
              p_name: "조영우",
              p_part: "FrontEnd",
            },
            {
              time: "15:00",
              p_ind: 0,
              p_name: "채승협",
              p_part: "BackEnd",
            },
          ],
        },
        {
          date: "2021-08-02",
          Interviewer: [
            {
              time: "13:00",
              p_ind: 0,
              p_name: "여정동",
              p_part: "FrontEnd",
            },
            {
              time: "14:00",
              p_ind: 0,
              p_name: "허창환",
              p_part: "BackEnd",
            },
          ],
        },
        {
          date: "2021-08-03",
          Interviewer: [
            {
              time: "13:00",
              p_ind: 0,
              p_name: "허창환",
              p_part: "BackEnd",
            },
            {
              time: "13:00",
              p_ind: 0,
              p_name: "채승협",
              p_part: "BackEnd",
            },
          ],
        },
      ],
      exaimining: [
        {
          p_ind: 0,
          p_name: "이주빈",
          p_part: "법무",
          p_state: 0,
          p_img: "../assets/jubin.jpg",
          p_appurl: "../assets/sample.pdf",
          interviewTime: "2021-07-30 13:00",
        },
        {
          p_ind: 1,
          p_name: "서주현",
          p_part: "마케팅",
          p_state: -1,
          p_img: "../assets/seohyeon.jpg",
          p_appurl: "../assets/sample.pdf",
          interviewTime: "2021-07-30 14:00",
        },
        {
          p_ind: 2,
          p_name: "이제훈",
          p_part: "개발",
          p_state: -1,
          p_img: "../assets/jeahoon.jpg",
          p_appurl: "../assets/sample.pdf",
          interviewTime: "2021-07-30 15:00",
        },
        {
          p_ind: 3,
          p_name: "김제니",
          p_part: "마케팅",
          p_state: 0,
          p_img: "../assets/jennie.jpg",
          p_appurl: "../assets/sample.pdf",
          interviewTime: "2021-07-30 16:00",
        },
      ],
      followData: [
        {
          img: "",
          title: "항상 열정있는 자세",
          name: "문영화",
          tag: ["#java", "#javascript"],
        },
        {
          img: "",
          title: "항상 열정있는 자세",
          name: "여정동",
          tag: ["#java", "#javascript"],
        },
        {
          img: "",
          title: "항상 열정있는 자세",
          name: "조영우",
          tag: ["#vue.js", "#javascript"],
        },
      ],
      exaiminingtotal: this.getExaiminingLength,
    };
  },
  computed: {
    getExaiminingLength() {
      return this.exaimining.length;
    },
  },
  methods: {
    enterInterViewRoom(p_ind) {
      console.log(p_ind);
    },
  },
};
</script>
