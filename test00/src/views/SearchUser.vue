<template>
  <el-container>
    <CompanySidebarHwa />
    <el-container>
      <el-header><Header /></el-header>
      <el-container>
        <el-main>
          <!-- 메인페이지 -->
          <search-list />
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
import SearchList from "@/components/search/SearchList.vue";

export default {
  components: {
    SearchList,
  },
};
</script>

<style></style>
