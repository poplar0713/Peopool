<template>
  <div v-if="this.result.length > 0">
    <el-row :gutter="24">
      <el-col :span="4" v-for="item in nowPageData" :key="item">
        <SearchCompanyCard :item="item.ent_index" />
      </el-col>
    </el-row>
    <div style="text-align:center">
      <el-pagination
        layout="prev, pager, next"
        :total="result.length"
        :page-size="18"
        :page-sizes="[18, 36, 54, 72]"
        v-model:currentPage="nowPage"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      >
      </el-pagination>
    </div>
  </div>
  <div v-if="this.result.length == 0" style="text-align:center">
    검색 키워드에 해당하는 기업정보가 없습니다
  </div>
</template>

<script>
import { ref } from "vue";
import axios from "axios";
import { useRoute } from "vue-router";
import SearchCompanyCard from "@/components/search/SearchCompanyCard.vue";

export default {
  name: "SearchCompany",
  components: {
    SearchCompanyCard,
  },
  setup() {
    // 토큰가져오기
    // const token = this.$cookies.get("PID_AUTH");
    const route = useRoute();
    const keyword = route.query.keyword;

    // 데이터저장 비동기방식
    const result = ref([]);
    (async () => {
      const res = await axios.get(
        `https://i5d206.p.ssafy.io:8443/poe/name/${keyword}`
        // { headers: { Authorization: token } }
      );
      result.value = res.data;
      console.log(res);
      initData();
    })();
    //
    console.log(result);
    //

    let tableSize = 18;
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
