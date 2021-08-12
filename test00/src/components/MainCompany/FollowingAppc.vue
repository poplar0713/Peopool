<template>
  <!-- <FollowingAppAll :userdata="result" /> -->
  <el-table :data="nowPageData" style="width: 100%">
    <el-table-column prop="ind_photo" label="" width="50%"
      ><img src="images/a.jpg" alt="" height="20"
    /></el-table-column>
    <el-table-column prop="ind_index" label="이름" width="100%">
      <template #default="scope">
        <UserDetail :userindex="scope.row.ind_index" />
      </template>
    </el-table-column>
    <el-table-column prop="ind_gender" label="성별" sortable width="70%">
    </el-table-column>
    <el-table-column prop="ind_introduce" label="자기소개"> </el-table-column>
  </el-table>

  <el-pagination
    style="text-align:center"
    layout="prev, pager, next"
    :total="result.length"
    :page-size="5"
    :page-sizes="[5, 10, 15, 20]"
    v-model:currentPage="nowPage"
    @size-change="handleSizeChange"
    @current-change="handleCurrentChange"
  >
  </el-pagination>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import UserDetail from "@/components/UserDetail.vue";
// import FollowingAppAll from "@/components/MainCompany/FollowingAppAll.vue";

export default {
  components: { UserDetail },
  setup() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");

    // 데이터저장 비동기방식
    const result = ref([]);
    (async () => {
      const res = await axios.get(`https://i5d206.p.ssafy.io:8443/poi`, {
        headers: { Authorization: token },
      });
      result.value = res.data;
      console.log(res);
      initData();
    })();
    //
    console.log(result);
    //

    let tableSize = 5;
    let nowPage = ref(1);
    let nowPageData = ref(result.value);
    console.log(nowPageData);

    function initData() {
      console.log(1);
      sliceData();
    }

    function handleSizeChange(val) {
      console.log(2);
      tableSize = val;
      sliceData();
    }

    function handleCurrentChange(val) {
      console.log(3);
      nowPage.value = val;
      sliceData();
    }

    function sliceData() {
      console.log(4);
      nowPageData.value = result.value.slice(
        tableSize * nowPage.value - tableSize,
        tableSize * nowPage.value
      );
    }
    function userdetail() {}

    return {
      nowPage,
      result,
      handleSizeChange,
      handleCurrentChange,
      userdetail,
      tableSize,
      nowPageData,
    };
  },
};
</script>

<style></style>
