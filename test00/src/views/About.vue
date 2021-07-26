<template>
  <div class="block">
    <span class="demonstration">When you have more than 7 pages</span>
    <el-pagination layout="prev, pager, next" :total="50"> </el-pagination>
    <div
      v-for="name in visibleNames"
      :key="name"
      visibleNames="visibleNames"
      currentpage="currentpage"
    >
      {{ name }}
    </div>
    >{{ name }}
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentpage: 0,
      pageSize: 3,
      visibleNames: [],
      dataSource: [
        { name: "hello1" },
        { name: "hello2" },
        { name: "hello3" },
        { name: "hello4" },
        { name: "hello5" },
        { name: "hello6" },
        { name: "hello7" },
        { name: "hello8" },
        { name: "hello9" },
        { name: "hello10" },
        { name: "hello11" },
        { name: "hello12" },
        { name: "hello13" },
        { name: "hello14" },
        { name: "hello15" },
        { name: "hello16" },
        { name: "hello17" },
      ],
    };
  },
  beforeMount: function() {
    this.updateVisibleNames();
  },
  methods: {
    updatePage(pageNumber) {
      this.currentpage = pageNumber;
      this.updateVisibleNames();
    },
    updateVisibleNames() {
      this.visibleNames = this.dataSource(
        this.currentpage * this.pageSize,
        this.currentpage * this.pageSize + this.pageSize
      );

      if (this.visibleNames.length == 0 && this.currentpage > 0) {
        this.updatePage(this.currentpage - 1);
      }
    },
  },
};
</script>

<style></style>
