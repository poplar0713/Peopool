<template>
<<<<<<< HEAD
  <div id="formdiv">
=======
  <div>
>>>>>>> front_cho
    <form v-on:submit.prevent enctype="multipart/form-data">
      <input multiple="multiple" type="file" ref="file" />
      <button @click="upload">
        Upload
      </button>
    </form>
  </div>
<<<<<<< HEAD
  <!-- <webviewer :initialDoc="this.resumefilepath" /> -->
=======
>>>>>>> front_cho
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
// import webviewer from "@/components/MainCompany/webviewer.vue";
export default {
  name: "SideBarProfileUserDoc",
  props: {
    // resumefilePath:String
  },

  components: {
    // webviewer
  },
  data() {
    return {
      fileList: "",
    };
  },
  methods: {
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },

    upload() {
      const token = this.$cookies.get("PID_AUTH");
      const decoded = jwt_decode(token);
      const index = decoded.index;

      var frm = new FormData();
      var Filedata = this.$refs.file.files[0];
      frm.append("upfile", Filedata);

      axios
        .post(`https://i5d206.p.ssafy.io:8443/poi/resume/${index}`, frm, {
          headers: { Authorization: token },
        })
        .then((response) => {
          console.log(response.status);
          if (response.status == 200) {
            alert("업로드 되었습니다!");
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style scoped>
html,
body {
  height: 100%;
  width: 100%;
}
#formdiv {
  margin: 20px;
}
</style>
