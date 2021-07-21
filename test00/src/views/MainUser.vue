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
            <el-header>이력서관리</el-header>
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
                    <el-button size="small">PR영상 업로드</el-button>
                    <template #tip>
                      <div class="el-upload__tip">
                        20mb 이하 파일만 업로드 가능
                      </div>
                    </template>
                  </el-upload>
                </el-row>
                <el-row>
                  <div v-if="hasVideo">
                    <video
                      src="@/assets/samplevideo.mp4"
                      height="250"
                      width="300"
                      controls
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
                  <el-tab-pane label="Following Enterprise">
                    <TabFollowings />
                  </el-tab-pane>
                  <el-tab-pane label="Required Interview"
                    ><TabRequiredInterview
                  /></el-tab-pane>
                  <el-tab-pane label="Schedule">
                    <TabSchedule/>
                  </el-tab-pane>
                  <el-tab-pane label="Documents">
                    <TabDocuments/>
                  </el-tab-pane>
                </el-tabs>
              </el-main>
            </el-container>
          </el-container>
        </el-main>
      </el-container>
    </el-container>
  </div>
<!-- TabSchedule -->
</template>
<script>
import UserSidebar from "@/components/SideBarComponents/UserSidebar.vue";
import TabFollowings from "@/components/MainUser/TabFollowings.vue";
import TabRequiredInterview from "@/components/MainUser/TabRequiredInterview.vue";
import TabSchedule from "@/components/MainUser/TabSchedule.vue";
import TabDocuments from "@/components/MainUser/TabDocuments.vue";

export default {
  components: {
    UserSidebar,
    TabFollowings,
    TabRequiredInterview,
    TabSchedule,
    TabDocuments
  },
  data() {
    return {
      mainsearch:"",
      search: "",
      username: "싸피 김",
      hasVideo: true,
      hasDoc: true,
      videosrc: "@/assets/samplevideo.mp4",
      fileList: [],
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
