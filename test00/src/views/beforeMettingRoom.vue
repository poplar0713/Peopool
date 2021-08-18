<template>
  <el-container>
    <el-aside width="200px">
      <div class="header name" v-if="!noncookie">{{ name }}님!</div>
      <div class="header company">{{ company }}</div>
      <div class="header">면접장입니다.</div>
      <div class="header">준비되셨나요?</div>
    </el-aside>
    <el-main>
      <div>
        <p class="manner">매너 있는 면접수칙 먼저 알아볼까요?</p>
        <p class="check">
          <i class="fas fa-check"></i>
          단정한 복장을 갖춰주세요.
        </p>
        <p class="check">
          <i class="fas fa-check"></i>
          안정적인 네트워크를 유지해주세요.
        </p>
        <p class="check">
          <i class="fas fa-check"></i>
          마이크와 카메라 확인!
        </p>
        <p class="check">
          <i class="fas fa-check"></i>
          NoShow는 절대 안돼요!
        </p>
        <div v-if="noncookie">
          <p class="check">
            <i class="fas fa-check"></i>
            면접장에 사용될 이름을 설정해 주세요.
            <br />
            <el-input
              type="text"
              placeholder="Please input"
              v-model="nonuserneme"
              maxlength="10"
              show-word-limit
              style="width:200px; margin:10px 30px"
              @keyup="usernamereq"
            >
            </el-input>
          </p>
        </div>
      </div>

      <div id="testzone">
        <div id="webcam">
          <video autoplay="true" id="videotag"></video>
        </div>

        <el-button plain id="camerabtn" @click="cameraCheck"
          ><i class="fas fa-video"></i>카메라 확인</el-button
        >
        <el-button plain id="micbtn" @click="micCheck"
          ><i class="fas fa-microphone"></i>마이크 확인</el-button
        >
        <div class="block" id="micdiv">
          <span class="demonstration">마이크 감도</span>
          <el-slider v-model="decibals" max="50"></el-slider>
        </div>
      </div>
    </el-main>
  </el-container>
</template>

<script>
import DecibelMeter from "decibel-meter";
export default {
  props: {
    noncookie: Boolean,
  },
  name: "beforeMettngRoom",
  data() {
    return {
      company: this.$route.params.company,
      name: localStorage.getItem("username"),
      decibals: 0,
      color1: "black",
      color2: "#5f2d9a",
      username: "",
      nonuserneme: "",
    };
  },
  methods: {
    usernamereq() {
      this.$emit("nonuser", this.nonuserneme);
    },
    micCheck() {
      const meter = new DecibelMeter("mictest");
      meter.sources.then((sources) => {
        meter.connect(sources[0]);
        meter.listenTo(
          0,
          (percent) => (this.decibals = Number(`${percent}`) + 100)
        );
      });
    },
    cameraCheck() {
      var video = document.querySelector("#videotag");
      if (navigator.mediaDevices.getUserMedia) {
        navigator.mediaDevices
          .getUserMedia({ video: true })
          .then(function(stream) {
            video.srcObject = stream;
          })
          .catch((err) => {
            <el-alert
              title="카메라를 확인해주세요"
              type="warning"
              show-icon
            ></el-alert>;
            if (err.response == 401) {
              this.$message.error("로그인세션이 만료되었습니다");
              this.$cookies.remove("PID_AUTH");
              localStorage.clear();
              this.$router.push("/");
            }
          });
      }
    },
  },
};
</script>

<style scoped>
#micdiv {
  width: 300px;
}

.demonstration {
  widows: 300px;
}
#testzone {
  margin-top: 40px;
}
#webcam {
  text-align: center;
}
#videotag {
  width: 450px;
}
.manner {
  font-size: 20px;
}
i {
  margin-right: 10px;
}
.title {
  font-size: 20px;
}
.header {
  font-size: 25px;
}
.el-aside {
  margin: 20px;
}
.check:hover {
  color: #ffc000;
}
</style>
