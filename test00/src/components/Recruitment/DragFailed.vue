<template>
  <el-button type="text" @click="dialogVisible = true"
    >interview 관리</el-button
  >

  <el-dialog
    title="채용현황"
    v-model="dialogVisible"
    width="50%"
    :before-close="handleClose"
  >
    <div>
      <el-row>
        <el-col :span="7">
          <div class="col-3">
            <h3>Draggable 1</h3>
            <draggable
              class="list-group"
              :list="list1"
              group="people"
              @change="log"
              itemKey="name"
            >
              <el-scrollbar height="400px">
                <template #item="{ element }">
                  <div class="list-group-item">
                    {{ element.name }} {{ element.id }}
                  </div>
                </template>
              </el-scrollbar>
            </draggable>
          </div>
        </el-col>
        <el-col :span="7">
          <div class="col-3">
            <h3>Draggable 2</h3>
            <draggable
              class="list-group"
              :list="list2"
              group="people"
              @change="log"
              itemKey="name"
            >
              <template #item="{ element }">
                <div class="list-group-item">
                  {{ element.name }} {{ element.id }}
                </div>
              </template>
            </draggable>
          </div>
        </el-col>
        <el-col :span="7">
          <div class="col-3">
            <h3>Draggable 3</h3>
            <draggable
              class="list-group"
              :list="list3"
              group="people"
              @change="log"
              itemKey="name"
            >
              <template #item="{ element }">
                <div class="list-group-item">
                  {{ element.name }} {{ element.id }}
                </div>
              </template>
            </draggable>
          </div>
        </el-col>
      </el-row>

      <rawDisplayer class="col-3" :value="list1" title="List 1" />
      <rawDisplayer class="col-3" :value="list2" title="List 2" />
      <rawDisplayer class="col-3" :value="list3" title="List 3" />
    </div>

    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">Cancel</el-button>
        <el-button type="primary" @click="dialogVisible = false"
          >Confirm</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>
<script>
import draggable from "vuedraggable";
export default {
  name: "two-lists",
  display: "Two Lists",
  order: 1,
  components: {
    draggable,
  },
  data() {
    return {
      dialogVisible: false,
      list1: [
        { name: "John", id: 1 },
        { name: "Joao", id: 2 },
        { name: "Jean", id: 3 },
        { name: "Gerard", id: 4 },
      ],
      list2: [
        { name: "Juan", id: 5 },
        { name: "Edgard", id: 6 },
        { name: "Johnson", id: 7 },
      ],
      list3: [
        { name: "뚱이", id: 8 },
        { name: "똥이", id: 9 },
        { name: "뚱땡이", id: 10 },
      ],
    };
  },
  methods: {
    add: function() {
      this.list.push({ name: "Juan" });
    },
    replace: function() {
      this.list = [{ name: "Edgard" }];
    },
    clone: function(el) {
      return {
        name: el.name + " cloned",
      };
    },
    log: function(evt) {
      window.console.log(evt);
    },
    handleClose(done) {
      this.$confirm("창을 닫으시겠습니까?")
        .then(() => {
          done();
          this.dialogVisible = false;
        })
        .catch(() => {});
    },
  },
};
</script>
