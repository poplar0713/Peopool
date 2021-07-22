<template>
  <el-tabs :tab-position="tabPosition" style="height: 200px;">
    <el-tab-pane label="Basic Info"><TabProfile1 /></el-tab-pane>
    <el-tab-pane label="Level of Education"><TabProfile2 /></el-tab-pane>
    <el-tab-pane label="Introduction"><TabProfile3 /></el-tab-pane>
    <el-tab-pane label="Task">Task</el-tab-pane>
  </el-tabs>
</template>

<script>
import TabProfile1 from "./TabProfile1.vue";
import TabProfile2 from "./TabProfile2.vue";
import TabProfile3 from "./TabProfile3.vue";
export default {
  components: {
    TabProfile1,
    TabProfile2,
    TabProfile3,
  },
  data() {
    return {
      tabPosition: "left",
    };
  },
};
</script>

<style></style>
