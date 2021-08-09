<template>
  <el-container class="mainLayout">
    <el-aside width="200px"><SideBarCompany /></el-aside>
    <el-container>
      <el-header><headerSearchUser /></el-header>
      <el-main>
        <div>
          <el-divider content-position="left"
            ><h1>
              # {{ $route.params.keyword }} 태그에 해당하는 피풀인
            </h1></el-divider
          >
          <SearchUserTagList />
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBarCompany from "@/components/SideBarComponents/SideBarCompany.vue";
import headerSearchUser from "@/components/SideBarComponents/headerSearchUser.vue";
import SearchUserTagList from "@/components/search/SearchUserTagList.vue";

export default {
  mounted() {},
  name: "SearchCompany",
  components: {
    SideBarCompany,
    headerSearchUser,
    SearchUserTagList,
  },
};
</script>

<style></style>
