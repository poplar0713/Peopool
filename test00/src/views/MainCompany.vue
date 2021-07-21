<template>
  <el-container>
    <CompanySidebarHwa />
    <el-container>
      <el-header>
        <div style="margin-top: 15px; width:100%">
          <el-input
            placeholder="Please input"
            v-model="input3"
            class="input-with-select"
          >
            <template #append>
              <el-button icon="el-icon-search"></el-button>
            </template>
          </el-input>
        </div>
      </el-header>
      <el-main>
        <check-annc />
        <applicant-list
          title="Today 우리회사 관심 피풀인"
          :followData="followData"
        />
        <applicant-list
          title="Today 눈여겨보는 관심 피풀인"
          :followData="followData"
        />
        <Webviewer initialDoc="파이팅 프런트.docx" />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import CompanySidebarHwa from "@/components/SideBarComponents/CompanySidebarHwa.vue";
import ApplicantList from "@/components/MainCompany/ApplicantList.vue";
import CheckAnnc from "@/components/MainCompany/CheckAnnc.vue";
import Webviewer from "@/components/MainCompany/Webviewer.vue";

export default {
  components: {
    CompanySidebarHwa,
    CheckAnnc,
    ApplicantList,
    Webviewer,
  },

  data() {
    return {
      tableData: [
        {
          state: true,
          date: "2016-05-03",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 0,
        },
        {
          state: true,
          date: "2016-05-02",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 0,
        },
        {
          state: true,
          date: "2016-05-04",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 2,
        },
        {
          state: false,
          date: "2016-05-01",
          title: "안드로이드 개발 및 ios 개발",
          url: "localhost:8080/company",
          applicant: 5,
          noofrect: 1,
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
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style></style>
