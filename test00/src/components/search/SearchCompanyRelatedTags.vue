<template>
  <div>
    <span
      v-for="tag in relatedtaglist"
      :key="tag"
      style="margin:3px; cursor:pointer"
      ><el-tag type="success" @click="gototagcompany(tag.list_name)">{{
        tag.list_name
      }}</el-tag></span
    >
  </div>
  <div v-if="this.relatedtaglist.length == 0" style="text-align:center">
    태그에 해당하는 기업정보가 없습니다
  </div>
</template>

<script>
import axios from "axios";
export default {
  components: {},
  props: [],
  data() {
    axios
      .get("https://i5d206.p.ssafy.io:8443/classlist/name", {
        params: {
          name: this.$route.query.keyword,
        },
      })
      .then((res) => {
        console.log(res);
        this.relatedtaglist = res.data;
      });
    return {
      relatedtaglist: [],
    };
  },
  methods: {
    gototagcompany(tag) {
      const loading = this.$loading({
        lock: true,
        text: "Loading",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });
      setTimeout(() => {
        loading.close();
        this.$router.push({ path: "SearchCompany", query: { keyword: tag } });
      }, 2000);
      setTimeout(() => {
        location.reload();
      }, 2001);
    },
  },
};
</script>

<style></style>
