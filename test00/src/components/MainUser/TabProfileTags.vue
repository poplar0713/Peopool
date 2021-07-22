<template>
  <div>asdfsdf</div>
  <el-select
    v-model="value"
    multiple
    filterable
    allow-create
    style="width:100%"
    placeholder="Choose tags for your article"
  >
    <el-option
      v-for="item in options"
      :key="item.value"
      :label="item.label"
      :value="item.value"
    >
    </el-option>
  </el-select>
</template>

<script>
import options from "./TagOptions.js";
export default {
  data() {
    return {
      value: [],
      dialogVisibleTag: false,
      props: { multiple: true },
      options,
    };
  },
  methods: {
    handleClose(done) {
      this.$confirm("Are you sure to close this dialog?")
        .then(() => {
          done();
          this.dialogVisibleTag = false;
        })
        .catch(() => {});
    },
    tagok() {
      console.log("dddd");
      console.log(this.inputbox);
    },
  },
};
</script>

<style>
.el-select__popper {
}
</style>
