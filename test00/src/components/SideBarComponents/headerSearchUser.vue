<template>
  <div style="float:right; margin-top:5px; width:50%;">
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
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
        this.$router.push({
          name: "searchuser",
          params: { keyword: `${this.keyword}` },
        });
      }, 2000);
    },
  },
};
</script>

<style></style>
