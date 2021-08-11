<template>
  <el-container class="mainLayout">
    <el-aside width="200px"><SideBarUser /></el-aside>
    <el-container>
      <el-header><headerSearchCompany /></el-header>
      <el-main>
        <div>
          <el-divider content-position="left"
            ><h1>'{{ $route.params.keyword }}' 검색결과</h1></el-divider
          >
          <SearchCompanyList />
        </div>
        <div>
          <el-divider content-position="left"
            ><h1>
              # {{ $route.params.keyword }} 태그에 해당하는 기업
            </h1></el-divider
          >
          <SearchCompanyTagList />
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
import headerSearchCompany from "@/components/SideBarComponents/headerSearchCompany.vue";
import SearchCompanyList from "@/components/search/SearchCompanyList.vue";
import SearchCompanyTagList from "@/components/search/SearchCompanyTagList.vue";

export default {
  name: "SearchCompany",
  components: {
    SideBarUser,
    headerSearchCompany,
    SearchCompanyList,
    SearchCompanyTagList,
  },
};
</script>

<style></style>
