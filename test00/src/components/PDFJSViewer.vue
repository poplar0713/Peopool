<template>
  <div>
    <iframe height="100%" width="100%" :src="path"></iframe>
  </div>
</template>

<script>
export default {
  name: "PDFJSViewer",
  props: {
    path: String,
  },
};
</script>
<style scoped>
div {
  width: 50%;
  height: 79vh;
  min-width: 400px;
}
</style>
