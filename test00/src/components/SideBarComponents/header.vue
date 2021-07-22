<template>
  <el-row :gutter="10">
    <el-col :span="10" :offset="12">
      <div style="margin-top: 15px; width:100%">
        <el-input
          placeholder="Please input"
          v-model="keyword"
          class="input-with-select"
        >
          <template #append>
            <el-button @click="search" icon="el-icon-search"></el-button>
          </template>
        </el-input>
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      keyword: "",
    };
  },

  methods: {
    search() {
      console.log(this.keyword);
    },
  },
};
</script>

<style></style>
