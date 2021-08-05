<template>
  <p @click="dialogVisible = true">
    <i class="el-icon-d-arrow-right"></i> 전체보기
  </p>
  <div>
    <el-dialog title="관심 피플인" v-model="dialogVisible" width="50%">
      <totalList :followData="followData" />
    </el-dialog>
  </div>
</template>

<script>
import totalList from "@/components/totalList.vue";

export default {
  components: {
    totalList,
  },
  data() {
    return {
      dialogVisible: false,

      followData: [
        {
          img: "",
          title: "항상 열정있는 자세",
          name: "문영화",
          tag: ["#java", "#javascript"],
        },
        {
          img: "",
          title: "항상 열정있는 자세",
          name: "여정동",
          tag: ["#java", "#javascript"],
        },
        {
          img: "",
          title: "항상 열정있는 자세",
          name: "조영우",
          tag: ["#vue.js", "#javascript"],
        },
        {
          img: "",
          title: "항상 열정있는 자세",
          name: "여정동",
          tag: ["#java", "#javascript"],
        },
        {
          img: "",
          title: "항상 열정있는 자세",
          name: "여정동",
          tag: ["#java", "#javascript"],
        },
      ],
    };
  },
  methods: {},
};
</script>

<style></style>
