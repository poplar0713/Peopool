<template>
  <el-container class="mainLayout">
    <el-aside width="200px"><SideBarCompany /></el-aside>
    <el-container>
      <el-header><headerSearchUser /></el-header>
      <el-main>
        <SearchUserList />
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import SideBarCompany from "@/components/SideBarComponents/SideBarCompany.vue";
import headerSearchUser from "@/components/SideBarComponents/headerSearchUser.vue";
import SearchUserList from "@/components/search/SearchUserList.vue";

export default {
  mounted(){
  },
  name: "SearchCompany",
  components: {
    SideBarCompany,
    headerSearchUser,
    SearchUserList,
  },
};
</script>

<style></style>
