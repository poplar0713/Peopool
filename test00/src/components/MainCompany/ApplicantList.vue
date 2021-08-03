<template>
  <div>
    <div>
      <el-row :gutter="24">
        <el-col :span="6">
          <h1><i class="el-icon-s-grid"></i> {{ title }}</h1></el-col
        >
        <el-col :span="6" :offset="12"><follow-appc /></el-col>
      </el-row>
    </div>
    <el-main>
      <el-space>
        <el-card
          class="box-card"
          style="width: 250px"
          v-for="fd in followData"
          :key="fd"
        >
          <template #header>
            <div class="card-header">
              <img src="a.jpg" style="width: 150px" />
              <br />
            </div>
          </template>

          <UserDetail :user="fd" />

          <div v-for="tag in fd.tag" :key="tag" class="text item">
            {{ tag }}
          </div>
        </el-card>
      </el-space>
    </el-main>
  </div>
</template>

<script>
import UserDetail from "@/components/UserDetail.vue";
import FollowAppc from "@/components/MainCompany/FollowAppc.vue";

export default {
  components: {
    UserDetail,
    FollowAppc,
  },
  name: "HelloWorld",
  props: {
    msg: String,
    title: String,
    followData: Array,
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
img {
  display: block;
  margin: 0px auto;
}
/* h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
} */
</style>
