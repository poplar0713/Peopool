<template>
  <el-container>
    <h2>'{{ $route.params.keyword }}' 검색결과</h2>
  </el-container>
  <el-row :gutter="24">
    <el-col :span="6" v-for="item in nowPageData" :key="item">
      <CompanyCard :item="item"/>
    </el-col>
  </el-row>
  <div style="text-align:center">
    <el-pagination
      layout="prev, pager, next"
      :total="result.length"
      :page-size="8"
      :page-sizes="[8, 16, 24, 32]"
      v-model:currentPage="nowPage"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    >
    </el-pagination>
  </div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import CompanyCard from "@/components/search/CompanyCard.vue";

export default {
  name: "SearchCompany",
  components: {
    CompanyCard,
  },
  setup() {
    const route = useRoute();
    const keyword = route.params.keyword;

    // 데이터저장 비동기방식
    const result = ref([]);
    (async () => {
      const res = await axios.get(
        `https://i5d206.p.ssafy.io:8443/poe/name/${keyword}`
      );
      result.value = res.data;
      console.log(res);
      initData();
    })();
    //
    console.log(result);
    //

    let tableSize = 8;
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

    return {
      nowPage,
      result,
      handleSizeChange,
      handleCurrentChange,
      tableSize,
      nowPageData,
    };
  },
};
</script>

<style></style>
