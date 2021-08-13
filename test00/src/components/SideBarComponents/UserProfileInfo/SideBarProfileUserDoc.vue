<template>
  <div>
    <form v-on:submit.prevent enctype="multipart/form-data">
      <input multiple="multiple" type="file" name="file" id="file" ref="file" />
      <button @click="upload">
        Upload
      </button>
    </form>
    <!-- <div v-if="!docfilepath">이력서가 없습니다.</div> -->
    <div>
      <web-viewer initialDoc="/docx_pdf/test.pdf"></web-viewer>
    </div>
  </div>
</template>

<script>
import jwt_decode from "jwt-decode";
import axios from "axios";
import WebViewer from "@/components/MainCompany/webviewer.vue";
export default {
  name: "SideBarProfileUserDoc",
  props: {
    docfilepath: String,
  },
  component: {
    WebViewer,
  },
  data() {
    return {};
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
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
};
</script>

<style></style>
