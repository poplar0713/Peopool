<template>
  <div>
    {{ this.matchinglist }}
  </div>
</template>

<script>
import axios from "axios";
import jwt_decode from "jwt-decode";

export default {
  name: "SearchCompanyMultiList",
  props: ["selected_tags"],
  data() {
    // 토큰가져오기
    const token = this.$cookies.get("PID_AUTH");
    const decoded = jwt_decode(token);
    const index = decoded.index;
    console.log(index);
    // 교집합조건 리스트 가져오기
    axios
      .get("https://i5d206.p.ssafy.io:8443/cla/case", {
        headers: { Authorization: token },
        list: this.selected_tags,
      })
      .then((res) => {
        this.matchinglist = res.data;
      })
      .catch((err) => {
        console.log(err);
        console.log("못가져오는중..");
      });
    for (var tagindex of this.selected_tags) {
      this.matchinglist.push(tagindex.list_index);
    }

    return {
      matchinglist: [],
    };
  },
  methods: {},
};
</script>

<style></style>
