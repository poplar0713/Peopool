<template>
  <el-container style="text-align">
    <el-aside width="200px"><SideBarUser /></el-aside>
    <el-container>
      <el-header><headerSearchCompany /></el-header>
      <el-main>
        <div>
          <el-divider content-position="left"><h1>연관태그</h1></el-divider>
          <SearchCompanyRelatedTags />
        </div>
        <div>
          <el-divider content-position="left"
            ><h1>기업명 '{{ $route.query.keyword }}' 검색결과</h1></el-divider
          >
          <SearchCompanyList />
        </div>
        <div>
          <el-divider content-position="left"
            ><h1>#{{ $route.query.keyword }} 태그에 해당하는 기업</h1></el-divider
          >
          <SearchCompanyTagList />
        </div>
        <div>
          <el-divider content-position="left"
            ><h1>'{{ $route.query.keyword }}'' 키워드를 포함하고 있는 기업</h1></el-divider
          >
          <SearchCompanyRelatedInfo />
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
import SearchCompanyRelatedTags from "@/components/search/SearchCompanyRelatedTags.vue";
import SearchCompanyRelatedInfo from "@/components/search/SearchCompanyRelatedInfo.vue";


export default {
  name: "SearchCompany",
  components: {
    SideBarUser,
    headerSearchCompany,
    SearchCompanyList,
    SearchCompanyTagList,
    SearchCompanyRelatedTags,
    SearchCompanyRelatedInfo,
  },
};
</script>

<style></style>
