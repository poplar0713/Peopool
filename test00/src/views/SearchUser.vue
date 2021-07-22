<template>
  <el-container class="mainLayout">
    <el-aside width="250px"><company-sidebar-hwa /></el-aside>

    <el-container>
      <el-header><Header /></el-header>
      <el-main>
        <SearchList />
        <SearchList />
        <SearchList />
        <SearchList />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import CompanySidebarHwa from "@/components/SideBarComponents/CompanySidebarHwa.vue";
// // import ApplicantList from "@/components/MainCompany/ApplicantList.vue";
// // import CheckAnnc from "@/components/MainCompany/CheckAnnc.vue";
// import WebViewer from "@/components/MainCompany/WebViewer.vue";
import Header from "@/components/SideBarComponents/header.vue";
import SearchList from "@/components/search/SearchList.vue";
export default {
  components: {
    CompanySidebarHwa,
    SearchList,
    //   WebViewer,
    Header,
  },
};
</script>

<style scoped>
.mainLayout {
  position: relative;
  height: 100%;
  width: 100%;
}
.el-container {
  background-color: cornsilk;
}
.el-aside {
  background-color: yellow;
  height: 100%;

  position: sticky;
  z-index: 1;
  top: 0;
  left: 0;
  overflow-x: hidden;
}
.el-main {
  background-color: yellowgreen;
}
.el-header {
  background-color: tomato;
}
</style>
