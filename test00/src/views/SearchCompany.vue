<template>
  <el-container class="mainLayout">
    <el-aside width="200px"><SideBarUser /></el-aside>
    <el-container>
      <el-header><headerSearchCompany /></el-header>
      <el-main>
        <SearchCompanyList />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
import headerSearchCompany from "@/components/SideBarComponents/headerSearchCompany.vue";
import SearchCompanyList from "@/components/search/SearchCompanyList.vue";

export default {
  name: "SearchCompany",
  components: {
    SideBarUser,
    headerSearchCompany,
    SearchCompanyList,
  },
};
</script>

<style></style>
