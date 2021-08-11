<template>
  <el-button type="text" @click="dialogVisible = true" style="color:black"
    >interview 관리</el-button
  >

  <el-dialog
    title="면접관리"
    v-model="dialogVisible"
    width="70%"
    :before-close="handleClose"
  >
    <!--  -->
    <!-- <el-tabs :tab-position="tabPosition" style="height: 200px;">
    <el-tab-pane label="받은요청">User</el-tab-pane>
    <el-tab-pane label="수락한면접">Config</el-tab-pane>
    <el-tab-pane label="결과대기">Role</el-tab-pane>
    <el-tab-pane label="결과">Task</el-tab-pane>
  </el-tabs> -->
    <!--  -->
    <el-row :gutter="20">
      <el-col :span="6"
        ><div class="grid-content bg-purple" style="text-align:center">
          <div>
            <h3>면접대기</h3>
            <el-scrollbar height="400px">
              <draggable
                class="list-group"
                :list="list1"
                group="people"
                @change="log"
                itemKey="name"
              >
                <template #item="{ element, index }">
                  <div class="list-group-item">
                    {{ element.part }} | {{ element.name }} {{ index }}
                  </div>
                </template>
              </draggable>
            </el-scrollbar>
          </div>
        </div></el-col
      >

      <el-col :span="6"
        ><div class="grid-content bg-purple" style="text-align:center">
          <div>
            <h3>면접진행완료</h3>
            <el-scrollbar height="400px">
              <draggable
                class="list-group"
                :list="list2"
                group="people"
                @change="log"
                itemKey="name"
              >
                <template #item="{ element, index }">
                  <div class="list-group-item">
                    {{ element.name }} {{ index }}
                  </div>
                </template>
              </draggable>
            </el-scrollbar>
          </div>
        </div></el-col
      >
      <el-col :span="6"
        ><div class="grid-content bg-purple" style="text-align:center">
          <div>
            <h3>불합격</h3>
            <el-scrollbar height="400px">
              <draggable
                class="list-group"
                :list="list3"
                group="people"
                @change="log"
                itemKey="name"
              >
                <template #item="{ element, index }">
                  <div class="list-group-item">
                    {{ element.name }} {{ index }}
                    <button>불합격통보</button>
                  </div>
                </template>
              </draggable>
            </el-scrollbar>
          </div>
        </div></el-col
      >
      <el-col :span="6"
        ><div class="grid-content bg-purple" style="text-align:center">
          <div>
            <h3>합격</h3>
            <el-scrollbar height="400px">
              <draggable
                class="list-group"
                :list="list4"
                group="people"
                @change="log"
                itemKey="name"
              >
                <template #item="{ element, index }">
                  <div class="list-group-item">
                    {{ element.part }} | {{ element.name }} {{ index }}
                    <button>합격</button>
                  </div>
                </template>
              </draggable>
            </el-scrollbar>
          </div>
        </div></el-col
      >
    </el-row>
    <rawDisplayer class="col-3" :value="list1" title="List 1" />

    <rawDisplayer class="col-3" :value="list2" title="List 2" />

    <!--  -->
  </el-dialog>
</template>

<script>
import draggable from "vuedraggable";

export default {
  components: {
    draggable,
  },
  data() {
    return {
      dialogVisible: false,
      tabPosition: "left",
      list1: [
        { name: "John", id: 1, part: "service" },
        { name: "Joao", id: 2, part: "service" },
        { name: "Jean", id: 3, part: "service" },
        { name: "Gerard", id: 4, part: "service" },
      ],
      list2: [
        { name: "John1", id: 5, part: "service" },
        { name: "Joao1", id: 6, part: "service" },
        { name: "Jean1", id: 7, part: "service" },
        { name: "Gerard1", id: 8, part: "service" },
      ],
      list3: [
        { name: "John2", id: 9, part: "service" },
        { name: "Joao2", id: 10, part: "service" },
        { name: "Jean2", id: 11, part: "service" },
        { name: "Gerard2", id: 12, part: "service" },
      ],
      list4: [
        { name: "John3", id: 13, part: "service" },
        { name: "Joao3", id: 14, part: "service" },
        { name: "Jean3", id: 15, part: "service" },
        { name: "Gerard3", id: 16, part: "service" },
      ],
    };
  },
};
</script>

<style lang=""></style>
