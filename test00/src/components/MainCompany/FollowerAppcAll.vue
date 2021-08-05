<template>
  <p @click="dialogVisible = true">
    <i class="el-icon-d-arrow-right"></i> 전체보기
  </p>
  <div>
    <el-dialog title="관심 피플인" v-model="dialogVisible" width="50%">
      <totalList :userdata="follower" />
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
      followData: [],
    };
  },
  props: {
    follower: Object,
  },
  methods: {},
};
</script>

<style></style>
