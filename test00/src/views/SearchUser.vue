<template>
  <el-container class="mainLayout">
    <el-aside width="250px"><SideBarCompany /></el-aside>

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
import SideBarCompany from "@/components/SideBarComponents/SideBarCompany.vue";
// // import ApplicantList from "@/components/MainCompany/ApplicantList.vue";
// // import CheckAnnc from "@/components/MainCompany/CheckAnnc.vue";
// import WebViewer from "@/components/MainCompany/WebViewer.vue";
import Header from "@/components/SideBarComponents/header.vue";
import SearchList from "@/components/search/SearchList.vue";
export default {
  components: {
    SideBarCompany,
    SearchList,
    //   WebViewer,
    Header,
  },
};
</script>

<style></style>
