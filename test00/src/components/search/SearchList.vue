<template>
  <div class="keywordBox">
    <div id="keyword">
      {{ $route.params.keyword }}
    </div>
    <p>해당 태그에 다음 피풀인이 기업의 많은 관심을 받고 있어요!</p>
  </div>
  <hr />
  <div id="hotpeople">
    <el-carousel :interval="4000" type="card" height="300" style="width: 80%">
      <el-carousel-item v-for="item in followData" :key="item">
        <div>
          <el-card class="hotcard" shadow="always">
            <el-row>
              <el-col :span="12">
                <img :src="item.img" style="width:200px" />
              </el-col>
              <el-col :span="12">
                <el-row>
                  <el-col :span="6" :offset="1"> {{ item.name }}</el-col>
                  <el-col :span="5" :offset="12"
                    ><i class="fas fa-heart" style="color:#ffc000;"></i>
                    {{ item.following }}</el-col
                  >
                </el-row>

                <div class="tagBox">
                  <div
                    class="tags"
                    v-for="(tag, i) in item.tag.slice(0, 6)"
                    :key="i"
                  >
                    {{ tag }}
                  </div>
                </div>
              </el-col>
            </el-row>
          </el-card>
        </div>
      </el-carousel-item>
    </el-carousel>
  </div>

  <p>#{{ $route.params.keyword }}의 결과</p>
  <div id="resultBox">
    <el-card
      id="result"
      class="box-card"
      style="width: 250px; height: 400px"
      v-for="fd in followData"
      :key="fd"
    >
      <template #header>
        <div class="card-header">
          <img :src="fd.img" style="width: 150px" />
          <br />
        </div>
      </template>

      <UserDetail :user="fd" />

      <div v-for="tag in fd.tag.slice(0, 3)" :key="tag" class="text item tags">
        {{ tag }}
      </div>
    </el-card>
  </div>
</template>

<script>
import UserDetail from "@/components/UserDetail.vue";
export default {
  components: {
    UserDetail,
  },
  data() {
    return {};
  },
  props: {
    followData: Object,
  },
};
</script>

<style scoped>
img {
  display: block;
  margin: 0px auto;
}
#resultBox {
  width: 100%;
}
#result {
  display: inline-block;
  margin: 10px;
}
.hotcard {
  width: 500px;
}
hr {
  margin-top: 30px;
  margin-bottom: 50px;
}
p {
  font-size: 20px;
}
.tagBox {
  width: 250px;
  margin: 10px;
}
.tags {
  /* display: inline-block; */
  float: left;
  background-color: #eaecee;
  color: black;
  border-radius: 100px;
  padding: 5px 15px;
  margin: 5px;
}
.keywordBox {
  width: 100%;
  text-align: center;
}
#keyword {
  font-size: 50px;
  display: inline-block;
}
#hotpeople {
  display: flex;
  justify-content: center;
  margin-bottom: 30px;
}
</style>
