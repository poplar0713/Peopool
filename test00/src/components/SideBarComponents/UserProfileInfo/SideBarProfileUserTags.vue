<template>
  <div v-if="this.value.length > 0">
    <el-tag
      v-for="item in value"
      :key="item.taglist_index"
      :type="warning"
      effect="plain"
    >
      {{ item }}
    </el-tag>
  </div>
  <div v-else>
    선택된 태그가 없습니다
  </div>
  <div>
    <el-select v-model="value" multiple placeholder="Select" style="width:100%">
      <el-option
        v-for="item in options"
        :key="item.taglist_index"
        :label="item.taglist_name"
        :value="item.taglist_name"
      >
      </el-option>
    </el-select>
  </div>
</template>

<script>
import axios from "axios";
export default {
  data() {
    // 태그목록 불러오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/taglist/")
      .then((res) => {
        this.options = res.data;
      })
      .catch((err) => {
        console.log(err);
      });
    return {
      // 불러온 태그들
      options: [],
      // 선택한 태그들
      value: [],
    };
  },
};
</script>

<style></style>
