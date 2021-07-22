<template>
  <div class="keywordBox">#{{ testinput }}</div>
  <div>
    <p>해당 태그에 다음 피풀인이 기업의 많은 관심을 받고 있어요!</p>
    <el-carousel :interval="4000" type="card" height="200px" style="width: 80%">
      <el-carousel-item v-for="item in 5" :key="item">
        <h3 class="medium">{{ item }}</h3>
      </el-carousel-item>
    </el-carousel>
  </div>
  <div>
    <el-card shadow="always">
      <el-row>
        <el-col :span="12">
          <img src="a.jpg" style="width:200px" />
        </el-col>
        <el-col :span="6">
          문영화<br />
          <div>
            <div class="tag">
              #java
            </div>
            <div class="tag">
              #javascript
            </div>
            <div class="tag">
              #mysql
            </div>
          </div>
        </el-col>
      </el-row>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      testinput: "java",
    };
  },
  props: {
    inputdata: String,
  },
};
</script>

<style>
.el-card {
  width: 500px;
}

.tag {
  display: inline-block;
  float: left;
  background-color: #eaecee;
  color: black;
  border-radius: 100px;
  padding: 5px 15px;
  margin: 5px;
}
.keywordBox {
  width: 100%;
}
</style>
