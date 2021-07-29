<template>
  <div style="float:right; margin-top:5px; width:50%;">
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
      this.$router.push({
        name: "searchuser",
        params: { keyword: this.keyword },
      });
    },
  },
};
</script>

<style></style>
