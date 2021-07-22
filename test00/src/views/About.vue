<template>
  <el-container>
    <el-aside width="200px"><SideBarCompany /></el-aside>
    <el-main>
      <div>
        <p>#노예</p>
        <el-carousel :interval="4000" type="card" height="200px">
          <el-carousel-item v-for="item in 20" :key="item">
            <h3 class="medium">{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
      </div>
      <el-row>
        <el-col :span="12">
          <div class="grid-content bg-purple">
            <div>
              <p>#Java</p>
              <el-carousel :interval="4000" type="card" height="200px">
                <el-carousel-item v-for="item in 20" :key="item">
                  <h3 class="medium">{{ item }}</h3>
                </el-carousel-item>
              </el-carousel>
            </div>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="grid-content bg-purple">
            <div>
              <p>#Python</p>
              <el-carousel :interval="4000" type="card" height="200px">
                <el-carousel-item v-for="item in 20" :key="item">
                  <h3 class="medium">{{ item }}</h3>
                </el-carousel-item>
              </el-carousel>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-main>
  </el-container>
</template>

<script>
import SideBarCompany from "@/components/SideBarComponents/SideBarCompany.vue";

export default {
  components: {
    SideBarCompany,
  },
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
  },
};
</script>

<style></style>
