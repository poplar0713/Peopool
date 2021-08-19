<template>
  <el-avatar :src="this.userdata.photofilepath" :size="70"></el-avatar>
</template>

<script>
import axios from "axios";

export default {
  props: ["userindex"],
  data() {
    axios
      .get(`https://i5d206.p.ssafy.io:8443/poi/${this.userindex}`, {
        headers: { Authorization: this.$store.state.usertoken },
      })
      .then((res) => {
        this.userdata.photofilepath =
          "https://i5d206.p.ssafy.io/file/" +
          res.data.photo_savefolder +
          "/" +
          res.data.photo_savefile;
      });
    return {
      userdata: [{ photofilepath: "" }, { photo_index: "" }],
    };
  },
};
</script>

<style></style>
