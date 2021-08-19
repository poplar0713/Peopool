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
  name: "headerSearchUser",
  data() {
    return {
      keyword: "",
      loading: true,
      fullscreenLoading: false,
    };
  },

  methods: {
    search() {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      this.$router.push({
        name: "SearchUser",
        query: { keyword: this.keyword },
      });
      setTimeout(() => {
        location.reload();
        loading.close();
      }, 1000);
    },
  },
};
</script>

<style></style>
