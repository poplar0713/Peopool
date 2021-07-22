<template>
  <el-button type="text" @click="dialogVisibleTag = true" style="color:#939597"
    >TagBar</el-button
  >
  <el-dialog
    title="Tags"
    v-model="dialogVisibleTag"
    width="30%"
    height="10%"
    :before-close="handleClose"
  >
    <div class="block">
      <el-cascader
        :v-model="inputbox"
        :options="options"
        :props="props"
        clearable
        style="width:100%"
      ></el-cascader>
    </div>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisibleTag = false">Cancel</el-button>
        <el-button type="primary" @click="(dialogVisibleTag = false), tagok()"
          >Confirm</el-button
        >
      </span>
    </template>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
<<<<<<< HEAD
      inputbox: [],
      dialogVisibleTag: false,
=======
      dialogVisible: false,
>>>>>>> front
      props: { multiple: true },
      options: [
        {
          value: 1,
          label: "Asia",
          children: [
            {
              value: 2,
              label: "China",
              children: [
                { value: 3, label: "Beijing" },
                { value: 4, label: "Shanghai" },
                { value: 5, label: "Hangzhou" },
              ],
            },
            {
              value: 6,
              label: "Japan",
              children: [
                { value: 7, label: "Tokyo" },
                { value: 8, label: "Osaka" },
                { value: 9, label: "Kyoto" },
              ],
            },
            {
              value: 10,
              label: "Korea",
              children: [
                { value: 11, label: "Seoul" },
                { value: 12, label: "Busan" },
                { value: 13, label: "Taegu" },
              ],
            },
          ],
        },
        {
          value: 14,
          label: "Europe",
          children: [
            {
              value: 15,
              label: "France",
              children: [
                { value: 16, label: "Paris" },
                { value: 17, label: "Marseille" },
                { value: 18, label: "Lyon" },
              ],
            },
            {
              value: 19,
              label: "UK",
              children: [
                { value: 20, label: "London" },
                { value: 21, label: "Birmingham" },
                { value: 22, label: "Manchester" },
              ],
            },
          ],
        },
        {
          value: 23,
          label: "North America",
          children: [
            {
              value: 24,
              label: "US",
              children: [
                { value: 25, label: "New York" },
                { value: 26, label: "Los Angeles" },
                { value: 27, label: "Washington" },
              ],
            },
            {
              value: 28,
              label: "Canada",
              children: [
                { value: 29, label: "Toronto" },
                { value: 30, label: "Montreal" },
                { value: 31, label: "Ottawa" },
              ],
            },
          ],
        },
      ],
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

<style></style>
