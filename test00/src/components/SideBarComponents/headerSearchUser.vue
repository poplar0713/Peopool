<template>
  <div style="float:right; margin-top:15px; width:50%;">
    <el-input
      placeholder="Please input"
      v-model="keyword"
      class="input-with-select"
      @keypress.enter="search"
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
      loading: true,
      fullscreenLoading: false,
    };
  },

  methods: {
    search() {
      // console.log(this.keyword);
      console.log(this.keyword);
      this.$router.push({
        name: "SearchUser",
        query: { keyword: this.keyword },
      });
      setTimeout(() => {
        location.reload();
      }, 1);
    },
  },
};
</script>

<style></style>
