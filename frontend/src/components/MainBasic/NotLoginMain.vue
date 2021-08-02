<template>
  <div id="maincard">
    <div>
      <p>금주의 HOT 기업</p>
      <span>다음 기업에서 당신을 기다리고 있어요</span>
    </div>
    <el-carousel :interval="4000" type="card" width="500px" height="200px">
      <el-carousel-item v-for="(item, i) in logo" :key="i">
        <img :src="item.img" style="width:200px" />
      </el-carousel-item>
    </el-carousel>
  </div>
  <!-- <br />
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
  </el-row> -->
</template>

<script>
export default {
  data() {
    return {
      logo: [
        {
          img: "logo/Samsung.png",
        },
        {
          img: "logo/samsungsds.jpg",
        },
        {
          img: "logo/cheil.png",
        },
        {
          img: "logo/ssafy.png",
        },
      ],
    };
  },
};
</script>

<style scoped>
#maincard {
  text-align: center;
  margin: 5%;
}
#maincard p {
  font-size: 30px;
  font-weight: bold;
  margin: 10px;
}
#maincard span {
  font-size: 20px;
}
#maincard div {
  margin: 30px;
}
</style>
