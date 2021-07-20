<template>
  <div>
    <p>금주의 핫한 기업</p>
    <el-carousel :interval="4000" type="card" height="200px">
      <el-carousel-item v-for="item in 5" :key="item">
        <h3 class="medium">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
  </div>
  <br />
  <el-row>
    <el-col :span="12"
      ><div class="grid-content bg-purple">
        <p>가장 최근에 올라온 기업</p>
        <el-carousel :interval="4000" type="card" height="200px">
          <el-carousel-item v-for="item in 20" :key="item">
            <h3 class="medium">{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
      </div></el-col
    >
    <el-col :span="12"
      ><div class="grid-content bg-purple-light">
        <p>채용이 활발한 기업</p>
        <el-carousel :interval="4000" type="card" height="200px">
          <el-carousel-item v-for="item in 20" :key="item">
            <h3 class="medium">{{ item }}</h3>
          </el-carousel-item>
        </el-carousel>
      </div></el-col
    >
  </el-row>
</template>

<script>
export default {};
</script>

<style></style>
