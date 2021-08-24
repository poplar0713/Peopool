<template>
  <div
    style="overflow:hidden;  width: 80px;
    height: 80px; 
    border-radius: 70%;"
  >
    <img
      :src="this.ent_image_path"
      style=" object-fit: cover; height:100%; width:100%; "
    />
  </div>
  <!-- <el-avatar
    shape="square"
    :src="this.ent_image_path"
    :size="70"
    style="object-fit:cover"
  ></el-avatar> -->
</template>

<script>
import axios from "axios";

export default {
  props: ["companyindex"],
  data() {
    axios
      .get(`https://i5d206.p.ssafy.io:8443/poe/path/${this.companyindex}`, {
        headers: { Authorization: this.$store.state.usertoken },
      })
      .then((res) => {
        this.ent_image_path =
          "https://i5d206.p.ssafy.io/file/" +
          res.data[0].image_savefolder +
          "/" +
          res.data[0].image_savefile;
      });
    return {
      ent_image_path: "",
    };
  },
};
</script>

<style></style>
