<template>
  <el-container>
    <el-header>
      <router-link to="/" style="text-decoration: none;">
        <div class="title">
          <div>
            <!-- object error -->
            <span class="fcolor">P</span>
            <span class="scolor">eo</span>
            <span class="scolor">P</span>
            <span class="fcolor">ool</span>
          </div>
        </div>
      </router-link>
    </el-header>
    <div class="sec">
      <div id="mainlightBox">
        <el-row>
          <el-col :span="16">
            <h2 id="lighth2">
              <span class="nonlight">스카웃 제의</span>
              <span class="nonlight">로망이셨나요?</span>
              <span class="light">PeoPool</span>
              <span class="nonlight">에서 이뤄드릴께요.</span>
            </h2>
            <Login />
          </el-col>
          <el-col :span="2">
            <img src="images/main1.png" style="width:500px" />
          </el-col>
        </el-row>
      </div>
    </div>
    <div class="sec">
      <el-row :gutter="20">
        <el-col :span="6" :offset="4">
          <img src="images/main3.png" style="width:500px" />
        </el-col>
        <el-col :span="6" :offset="5" id="font">
          <div id="mainBox2">
            <span id="maintitle2">함께 일할까요?</span>
            <span class="main2">나의</span>
            <span class="main2">기술, 역량</span>
            <span class="main2">기업에게</span>
            <span class="main2">마음껏 보여주세요.</span>
          </div>
        </el-col>
      </el-row>
    </div>
    <div>
      <NotLoginMain />
    </div>
    <div></div>
    <el-footer></el-footer>
  </el-container>
  <!-- <div style="text-align: center; align-items: center; margin-top: 10px;">
    <Login />
    <br />
    <NotLoginMain />
  </div> -->
</template>

<script>
import Login from "@/components/Account/Login.vue";
import NotLoginMain from "@/components/MainBasic/NotLoginMain.vue";
export default {
  name: "start",
  components: {
    Login,
    NotLoginMain,
  },
};
</script>

<style scoped>
.title {
  margin-top: 1%;
}
.title span {
  margin: 0;
}
.title span {
  font-family: "Work Sans", sans-serif;
  font-size: 60px;
}
.sec {
  margin-top: 2%;
}
#font p {
  font-size: 50px;
  color: #ffc000;
  margin: 0;
}
#mainBox2 {
  margin-top: 100px;
}
.main2 {
  display: block;
  font-size: 30px;
  color: #ffc000;
  font-family: "Nanum Gothic", sans-serif;
  font-weight: 800;
}

.fcolor {
  color: #ffc000;
}
.scolor {
  color: black;
}
/* .el-header {
  background-color: thistle;
}
.el-main {
  background-color: green;
}
.el-footer {
  background-color: gray;
} */
span {
  margin: 10px;
}
#maintitle2 {
  display: block;
  font-size: 40px;
  color: #5f2d9a;
  font-family: "Nanum Gothic", sans-serif;
  font-weight: bold;
  margin-bottom: 30px;
}
</style>
