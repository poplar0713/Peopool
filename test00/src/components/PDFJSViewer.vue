<template>
  <div>
    <iframe height="50%" width="50%" :src="path"></iframe>
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
