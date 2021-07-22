<template>
  <div class="paginition">
    <div v-if="pageSetting.list.length" class="page-list">
      <el-button
        type="warning"
        icon="el-icon-arrow-left"
        circle
        v-if="pageSetting.first !== null"
        @click="pageSetting.first !== null ? sendPage(pageSetting.first) : ''"
      ></el-button>

      <span v-for="page in pageSetting.list" :key="page">
        <span v-if="page === pageSetting.currentPage"
          ><el-button @click="sendPage(page)" type="primary">{{ page }}</el-button></span
        >
        <span v-else>
          <el-button @click="sendPage(page)">{{ page }}</el-button></span
        >
      </span>

      <el-button
        type="warning"
        icon="el-icon-arrow-right"
        circle
        v-if="pageSetting.end !== null"
        @click="pageSetting.end !== null ? sendPage(pageSetting.end) : ''"
      ></el-button>
    </div>
  </div>
</template>

<style>
.paginition {
  text-align: center;
  align-content: center;
}
span {
  margin: 0.3%;
}
</style>

<script>
export default {
  props: ["pageSetting"],
  methods: {
    sendPage(page) {
      this.$emit("paging", page);
    },
  },
};
</script>
