<template>
  <div>
    <el-container>
      <el-aside><UserSidebar /></el-aside>
      <el-container>
        <!-- 검색창 -->
        <el-header style="text-align; padding-top:10px">
          <el-input
            placeholder="Please input"
            v-model="mainsearch"
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
            <el-header style="text-align:center;"><h2>{{$store.state.temp.UserName}}</h2></el-header>
            <el-container>
              <el-aside width="30%">
                <el-row>
                  <h4>나의 영상</h4>
                  <el-upload
                    limit="1"
                    action="https://jsonplaceholder.typicode.com/posts/"
                    :file-list="fileList"
                    accept="video/*"
                    style="margin-left:10%"
                  >
<<<<<<< HEAD
                    <el-button size="small">PR영상 업로드</el-button>
                    <template #tip>
                      <!-- <div class="el-upload__tip">
                      </div> -->
                    </template>
                  </el-upload>
                </el-row>
                <el-row>
                  <div v-if="hasVideo">
                    <video
                      src="@/assets/samplevideo.mp4"
                      height="360"
                      width="640"
                      controls
                      style="width:100%; height:100%"
                    ></video>
                  </div>
                  <div v-else class="videoNotFound">
                    <h4>PR 동영상을 아직 업로드 하지 않았습니다</h4>
                    <h4>오른쪽 상단의 버튼을 눌러 영상을 업로드 해보세요</h4>
                  </div>
                </el-row>
              </el-aside>
              <el-main>
                <el-tabs :tab-position="tabPosition" style="height: 250;">
                  <el-tab-pane label="Profile">
                    <TabProfile />
                  </el-tab-pane>
                  <el-tab-pane label="Following Enterprise">
                    <TabFollowings />
                  </el-tab-pane>
                  <el-tab-pane label="Required Interview"
                    ><TabRequiredInterview
                  /></el-tab-pane>
                  <el-tab-pane label="Schedule">
                    <TabSchedule />
                  </el-tab-pane>
                  <el-tab-pane label="Documents">
                    <TabDocuments />
                  </el-tab-pane>
                </el-tabs>
              </el-main>
            </el-container>
          </el-container>
        </el-main>
      </el-container>
=======
                    {{ company.company_name }}
                  </el-card>
                </div>
                <div v-else><h4>현재 팔로우 중인 기업이 없습니다</h4></div>
              </el-tab-pane>
              <el-tab-pane label="면접 요청">
                <div v-if="hasInterviewReq">
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
                <div>
                  <el-scrollbar height="400px">
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
                  </el-scrollbar>
                </div>
              </el-tab-pane>
            </el-tabs>
          </el-col>
        </el-row>

        <div class="section">
          <div class="section">
            <el-row>
              <el-col :span="3">
                <h2>이력서 관리</h2>
              </el-col>
              <el-col :span="2">
                <el-upload
                  limit="1"
                  action="https://jsonplaceholder.typicode.com/posts/"
                  :file-list="fileList"
                  accept=".pdf"
                >
                  <el-button size="small">이력서 업로드</el-button>
                </el-upload>
              </el-col>
            </el-row>
          </div>

          <div class="section">
            <WebViewer initialDoc="파이팅 프런트.docx" />
          </div>
        </div>
      </el-main>
>>>>>>> front
    </el-container>
  </div>
  <!-- TabSchedule -->
</template>
<<<<<<< HEAD
=======

<style>
.main {
  border-radius: 20px 20px 20px 20px;
  margin-top: 2%;
  margin-left: 5%;
  margin-right: 2%;
  background-color: #fef9e7;
  position: relative;
  z-index: -1;
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
/* .panel{
  overflow: scroll;
} */
</style>

>>>>>>> front
<script>
import UserSidebar from "@/components/SideBarComponents/UserSidebar.vue";
import TabFollowings from "@/components/MainUser/TabFollowings.vue";
import TabRequiredInterview from "@/components/MainUser/TabRequiredInterview.vue";
import TabSchedule from "@/components/MainUser/TabSchedule.vue";
import TabDocuments from "@/components/MainUser/TabDocuments.vue";
import TabProfile from "@/components/MainUser/TabProfile.vue";

export default {
  components: {
    UserSidebar,
<<<<<<< HEAD
    TabFollowings,
    TabRequiredInterview,
    TabSchedule,
    TabDocuments,
    TabProfile,
=======
    WebViewer,
>>>>>>> front
  },
  data() {
    return {
      mainsearch: "",
      search: "",
      username: this.$store.state.temp.UserName,
      hasVideo: true,
      hasDoc: true,
      videosrc: "@/assets/samplevideo.mp4",
      fileList: [],
<<<<<<< HEAD
=======
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
      ],
>>>>>>> front
    };
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
