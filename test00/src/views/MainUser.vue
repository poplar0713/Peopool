<template>
  <el-container>
    <el-aside width="200px"><UserSidebar /></el-aside>

    <el-container class="main">
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
        <h2>{{ username }}님의 이력관리</h2>
        <el-row>
          <el-col :span="12" class="section">
            <el-row>
              <el-col :span="12">
                <h2>나의 PR 영상</h2>
              </el-col>
              <el-col :span="12">
                <el-upload
                  limit="1"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  :file-list="fileList"
                  accept="video/*"
                >
                  <el-button size="small">PR영상 업로드</el-button>
                  <template #tip>
                    <div class="el-upload__tip">
                      20mb 이하 파일만 업로드 가능
                    </div>
                  </template>
                </el-upload>
              </el-col>
            </el-row>
            <el-row>
              <el-col :span="5">
                <div v-if="hasVideo">
                  <video src="@/assets/samplevideo.mp4" controls>
                    동영상을 재생할 수 없습니다
                  </video>
                </div>
                <div v-else class="videoNotFound">
                  <h4>PR 동영상을 아직 업로드 하지 않았습니다</h4>
                  <h4>오른쪽 상단의 버튼을 눌러 영상을 업로드 해보세요</h4>
                </div>
              </el-col>
            </el-row>
          </el-col>

          <el-col :span="11" class="section">
            <el-tabs type="border-card" class="panel-tabs">
              <el-tab-pane label="팔로우 중인 기업">
                <div v-if="hasfollowing">
                  <el-card
                    shadow="hover"
                    v-for="(company, i) in followList"
                    v-bind:key="i"
                    align="center"
                  >
                    {{ company.company_name }}
                  </el-card>
                </div>
                <div v-else><h4>현재 팔로우 중인 기업이 없습니다</h4></div>
              </el-tab-pane>
              <el-tab-pane label="면접 요청">
                <div v-if="hasInterviewReq" class="panel">
                  <el-card
                    shadow="hover"
                    v-for="(req, i) in InterviewReq"
                    v-bind:key="i"
                    align="center"
                    >{{ req.company_name }}
                  </el-card>
                </div>
                <div v-else>면접을 요청한 기업이 없습니다.</div>
              </el-tab-pane>
              <el-tab-pane label="일정" class="panel">
                <el-timeline>
                  <el-timeline-item
                    v-for="(event, i) in events"
                    v-bind:key="i"
                    :timestamp="event.time"
                  >
                    <el-card>
                      <h4>{{ event.title }}</h4>
                      <p>{{ event.content }}</p>
                    </el-card>
                  </el-timeline-item>
                </el-timeline>
              </el-tab-pane>
            </el-tabs>
          </el-col>
        </el-row>

        <el-row class="section">
          <el-row>
            <el-col :span="18">
              <h2>이력서 관리</h2>
            </el-col>
            <el-col :span="4">
              <el-upload
                limit="1"
                action="https://jsonplaceholder.typicode.com/posts/"
                :file-list="fileList"
                accept=".pdf"
              >
                <el-button size="small">이력서 업로드</el-button>
                <template #tip>
                  <div class="el-upload__tip">20mb 이하 파일만 업로드 가능</div>
                </template>
              </el-upload>
            </el-col>
          </el-row>
        </el-row>
      </el-main>
    </el-container>
  </el-container>
</template>

<style>
.main {
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
video{
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
.panel-tabs{
  height: 500px;
}
.panel{
  overflow: scroll;
}
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
