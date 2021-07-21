<template>
  <div>
    <el-container>
      <el-aside><UserSidebar /></el-aside>
      <el-container>
        <!-- 검색창 -->
        <el-header>
          <el-input
            placeholder="Please input"
            v-model="input3"
            class="input-with-select"
          >
            <template #append>
              <el-button icon="el-icon-search"></el-button>
            </template>
          </el-input>
        </el-header>
        <!-- 메인 -->
        <el-main> 
  <el-container>
    <el-header>이력서관리</el-header>
    <el-container>
      <el-aside width="30%">
        <h4>나의 영상</h4>

      </el-aside>
      <el-main>Main</el-main>
    </el-container>
  </el-container>



        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<style>
/* .main {
  border-radius: 20px 20px 20px 20px;
  margin-top: 2%;
  margin-left: 2%;
  margin-right: 2%;
  background-color: #f4f6f6;
  position: relative;
}
.videoNotFound {
  border-radius: 20px 20px 20px 20px;
  width: 440px;
  height: 330px;
  border-color: gray;
  border-width: 4px;
  border-style: dotted;
  text-align: center;
  background-color: white;
}
video {
  margin: 1%;
  height: auto;
  max-width: 1280px;
}
.section {
  border-radius: 20px 20px 20px 20px;
  width: auto;
  height: auto;
  margin: 1%;
  padding: 1%;
  background-color: white;
}
.panel-tabs {
  height: 500px;
}
.panel {
  overflow: scroll;
} */
</style>

<script>
import UserSidebar from "@/components/SideBarComponents/UserSidebar.vue";

export default {
  components: {
    UserSidebar,
  },
  data() {
    return {
      username: "싸피 김",
      hasVideo: true,
      hasDoc: true,
      videosrc: "@/assets/samplevideo.mp4",
      fileList: [],
      followList: [
        {
          company_id: 0,
          company_name: "삼성전자",
        },
        {
          company_id: 1,
          company_name: "삼성SDS",
        },
        {
          company_id: 2,
          company_name: "카카오",
        },
      ],
      InterviewReq: [
        {
          company_id: 6,
          company_name: "구미 2반 6팀",
        },
      ],
      events: [
        {
          time: "2021/07/21 09:00",
          title: "면접 1",
          content: "A사 면접",
        },
        {
          time: "2021/07/21 10:00",
          title: "면접 2",
          content: "B사 면접",
        },
        {
          time: "2021/07/21 13:00",
          title: "면접 3",
          content: "C사 면접",
        },
        {
          time: "2021/07/21 16:00",
          title: "면접 4",
          content: "D사 면접",
        },
      ],
    };
  },
  computed: {
    hasfollowing: function() {
      if (this.followList.length > 0) return true;
      else return false;
    },
    hasInterviewReq: function() {
      if (this.InterviewReq.length > 0) return true;
      else return false;
    },
  },
  methods: {
    uploadFile() {},
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file.size);
    },
    handleExceed() {
      this.$message.warning(`최대 1개의 파일만 업로드 가능합니다`);
    },
    beforeRemove(file) {
      return this.$confirm(`Cancel the transfert of ${file.name} ?`);
    },
  },
};
</script>
