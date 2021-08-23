<template>
  <el-container class="base">
    <el-header class="titleBox" style="margin-top:30px">
      <div class="title">
        <div>
          <span class="f">P</span>
          <span class="s">eo</span>
          <span class="s">P</span>
          <span class="f">ool</span>
        </div>
      </div>
    </el-header>
    <el-container>
      <el-container>
        <el-main>
          <div id="container">
            <div>
              <div id="join" class="animate join">
                <before-meeting
                  @nonuser="noncookieuser"
                  :noncookie="noncookie"
                ></before-meeting>
                <div style="text-align:center">
                  <el-button
                    type="warning"
                    v-if="companycheckindex"
                    class="go"
                    @click="copyurl"
                    >url복사</el-button
                  >
                  <el-button type="warning" class="go" @click="register"
                    >입장하기</el-button
                  >
                </div>
              </div>
              <div id="room" style="display: none;">
                <div>
                  <span id="room-header"></span>
                </div>
                <el-divider></el-divider>
                <div id="participants" class="wrapper"></div>
              </div>
            </div>
          </div>
        </el-main>
        <el-footer v-if="this.options" class="footer" style="left:0px">
          <!-- 바뀐버튼 -->
          <el-button-group>
            <el-button
              round
              v-if="this.audioOn"
              id="button-audio"
              v-on:click="AudioOnOff"
              value="Audio Off"
              ><i class="fas fa-microphone"></i>&nbsp;&nbsp;음소거</el-button
            >
            <el-button
              round
              type="danger"
              plain
              v-else
              id="button-audio"
              v-on:click="AudioOnOff"
              value="Audio On"
              ><i class="fas fa-microphone-alt-slash"></i>&nbsp;&nbsp;음소거
              해제</el-button
            >
            <el-button
              round
              v-if="this.videoOn"
              id="button-video"
              v-on:click="VideoOnOff"
              value="Video Off"
              ><i class="fas fa-video"></i>&nbsp;&nbsp;비디오 중지</el-button
            >
            <el-button
              round
              type="danger"
              plain
              v-else
              id="button-video"
              v-on:click="VideoOnOff"
              value="Video On"
              ><i class="fas fa-video-slash"></i>&nbsp;&nbsp;비디오
              시작</el-button
            >
            <!-- 실시간채팅버튼 -->
            <el-popover
              placement="top-start"
              title="실시간채팅"
              :width="300"
              trigger="click"
            >
              <template #reference>
                <el-button
                  type="success"
                  plain
                  style="position:relative"
                  @click="alaramcheck"
                  ><i class="far fa-comments"></i>&nbsp;&nbsp;실시간 채팅
                  <div
                    id="circle2"
                    style="position:absolute"
                    v-if="readchat"
                  ></div
                ></el-button>
              </template>
              <div class="scroll type1" id="chatdiv">
                <div
                  v-for="(item, index) in chatlist"
                  :key="index"
                  :class="[item.name == username ? 'itemright' : 'itemleft']"
                >
                  <p v-if="item.name == username" class="speech-bubble">
                    {{ item.text }}
                  </p>
                  <p v-else class="speech-bubble-left">
                    {{ item.name }}<br />
                    {{ item.text }}
                  </p>
                </div>
              </div>
              <div>
                <el-input
                  v-model="chattext"
                  @keyup.enter="sendchat"
                  placeholder="메시지를 입력해주세요"
                >
                </el-input>
              </div>
            </el-popover>
            <!-- <el-button
              round
              id="button-setting"
              @click="this.dialogVisible = true"
              value="Setting"
              >설정</el-button
            > -->

            <el-popconfirm
              confirmButtonText="OK"
              @confirm="leaveRoom"
              cancelButtonText="No, Thanks"
              icon="el-icon-info"
              iconColor="red"
              title="면접장을 나가시겠습니까?"
            >
              <!-- @click="exitDiaVisible = true" -->
              <template #reference>
                <el-button round type="danger" id="button-leave"> X</el-button>
              </template>
            </el-popconfirm>
          </el-button-group>
        </el-footer>
      </el-container>
    </el-container>
  </el-container>

  <!-- <el-dialog
    v-model="this.dialogVisible"
    style="width: 100%; height:100%"
    title="설정"
    :before-close="handleClose"
  >
    설정을 할 수 있는 곳이 될 것
  </el-dialog> -->

  <!-- <el-dialog v-model="exitDiaVisible" width="30%">
    <span>면접장에서 나가시겠습니까?</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="exitDiaVisible = false">아니요</el-button>
        <el-button type="danger" @click="leaveRoom">퇴장하기</el-button>
      </span>
    </template>
  </el-dialog> -->
  <input type="text" id="ShareUrl" style="display:none;" />
</template>
<script>
import BeforeMeeting from "./beforeMettingRoom.vue";
import kurentoUtils from "kurento-utils";
import adapter from "webrtc-adapter";
import jwt_decode from "jwt-decode";
// import wsocket from "@/components/utils/websocket.js";
//const PARTICIPANT_MAIN_CLASS = "participant main";
//const PARTICIPANT_CLASS = "participant";
var ws = null;
var participants = {};

export default {
  name: "InterviewRoom",
  data() {
    return {
      room: null,
      username: null,
      audioOn: true,
      videoOn: true,
      options: false,
      dialogVisible: false,
      chattext: "",
      chatlist: [],
      visible: false,
      exitDiaVisible: false,
      noncookie: null,
      noncookieusername: "",
      alaram: "",
      readchat: false,
      companycheckindex: null,
    };
  },
  watch: {
    chatlist: {
      deep: true,
      handler() {
        let popdiv = document.getElementsByClassName("el-popover")[0];
        let opencheck = popdiv.getAttribute("aria-hidden");
        if (opencheck == "false") {
          this.readchat = false;
        } else {
          this.readchat = true;
        }
      },
    },
  },
  components: {
    BeforeMeeting,
  },
  created() {
    if (localStorage.getItem("username") != null) {
      this.username = localStorage.getItem("username");
    }

    const token = this.$cookies.get("PID_AUTH");
    if (token == null || token == "") {
      this.noncookie = true;
    } else {
      const decoded = jwt_decode(token);
      const type = decoded.type;
      if (type == 1) {
        this.companycheckindex = true;
      }
    }
  },

  mounted: function() {
    console.log(adapter.browserDetails.browser);
    ws = new WebSocket("wss://i5d206.p.ssafy.io:8443/groupcall");
    this.username = localStorage.getItem("username");

    this.room = this.$route.params.url;
    ws.onmessage = (message) => {
      var parsedMessage = JSON.parse(message.data);
      console.info("Received message: " + message);
      switch (parsedMessage.id) {
        case "chatting":
          this.chatlist.push(parsedMessage);
          setTimeout(function() {
            var s = document.getElementById("chatdiv");
            s.scrollTop = s.scrollHeight;
          }, 100);
          break;
        case "existingParticipants":
          this.onExistingParticipants(parsedMessage);
          break;
        case "newParticipantArrived":
          this.onNewParticipant(parsedMessage);
          break;
        case "participantLeft":
          this.onParticipantLeft(parsedMessage);
          break;
        case "receiveVideoAnswer":
          this.receiveVideoResponse(parsedMessage);
          break;
        case "iceCandidate":
          participants[parsedMessage.name].rtcPeer.addIceCandidate(
            parsedMessage.candidate,
            function(error) {
              if (error) {
                console.error("Error adding candidate: " + error);
                return;
              }
            }
          );
          break;
        default:
          console.error("Unrecognized message", parsedMessage);
      }
    };

    ws.onopen = function() {
      console.log("interviesroom - Websocket is connected!");
    };
  },
  methods: {
    copyurl() {
      var dummy = document.createElement("input");
      var text = location.href;

      window.Kakao.Link.sendDefault({
        objectType: "feed",
        content: {
          title: `'${this.$route.params.company}' 면접에 초대해요.😁`,
          description:
            "피풀 비대면 면접서비스에 당신을 초대합니다. 우리 회사에 인재 빠르게 contact😃 peopool에서 경험하세요!",
          imageUrl: "https://i5d206.p.ssafy.io/file/urlshare.png",
          link: {
            mobileWebUrl: text,
            webUrl: text,
          },
        },
      });
      document.body.appendChild(dummy);
      dummy.value = text;
      dummy.select();
      document.execCommand("copy");
      document.body.removeChild(dummy);

      this.$message.success("URL도 복사되었어요!👌");
    },
    alaramcheck() {
      let popdiv = document.getElementsByClassName("el-popover")[0];

      let opencheck = popdiv.getAttribute("aria-hidden");
      if (opencheck == "true") {
        this.readchat = false;
      }
    },
    noncookieuser(name) {
      this.noncookieusername = name;
      this.username = this.noncookieusername;
      console.log(this.username);
    },
    visiblechat() {
      var s = document.getElementById("chatdiv");
      this.visible = !this.visible;
      if (this.visible) {
        // setTimeout(function() {
        s.scrollTop = s.scrollHeight;
        // }, 100);
      }
    },
    sendchat() {
      if (this.chattext == "" || this.chattext == null) return;
      let chat = {
        id: "chatting",
        name: this.username,
        room: this.room,
        text: this.chattext,
      };
      this.sendMessage(chat);
      this.chattext = "";
    },
    register() {
      var message;

      if (this.noncookie) {
        let user = this.noncookieusername.replace(" ", "");
        if (user == "" || this.noncookieusername == null) {
          this.$message.error("사용자 이름을 입력해주세요.");
          return;
        } else {
          document.getElementById("join").style.display = "none";
          document.getElementById("room").style.display = "block";
          this.options = true;
          message = {
            id: "joinRoom",
            name: this.noncookieusername,
            room: this.room,
          };
          this.sendMessage(message);
        }
      } else {
        this.username = localStorage.getItem("username");
        document.getElementById("join").style.display = "none";
        document.getElementById("room").style.display = "block";
        this.options = true;
        message = {
          id: "joinRoom",
          name: this.username,
          room: this.room,
        };
        this.sendMessage(message);
      }
    },

    onNewParticipant(request) {
      this.receiveVideo(request.name);
    },

    receiveVideoResponse(result) {
      participants[result.name].rtcPeer.processAnswer(
        result.sdpAnswer,
        function(error) {
          if (error) return console.error(error);
        }
      );
    },

    callResponse(message) {
      if (message.response != "accepted") {
        console.info("Call not accepted by peer. Closing call");
        stop();
      } else {
        //webRtcPeer.processAnswer(message.sdpAnswer, function(error) {
        //  if (error) return console.error(error);
        //});
      }
    },

    onExistingParticipants(msg) {
      var constraints = {
        audio: true,
        video: {
          mandatory: {
            maxWidth: 500,
            maxHeight: 500,
            maxFrameRate: 60,
            minFrameRate: 15,
          },
        },
      };
      console.log(this.username + " registered in room " + this.room);
      var participant = new this.Participant(this.username, this.sendMessage);
      participants[this.username] = participant;
      var video = participant.getVideoElement();

      var options = {
        localVideo: video,
        mediaConstraints: constraints,
        onicecandidate: participant.onIceCandidate.bind(participant),
      };
      participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(
        options,
        function(error) {
          if (error) {
            return console.error(error);
          }
          this.generateOffer(participant.offerToReceiveVideo.bind(participant));
        }
      );

      msg.data.forEach(this.receiveVideo);
    },

    leaveRoom() {
      this.sendMessage({
        id: "leaveRoom",
      });

      for (var key in participants) {
        participants[key].dispose();
      }

      // document.getElementById("join").style.display = "block";
      // document.getElementById("room").style.display = "none";
      // document.getElementById("chatdivtop").style.display = "none";
      this.exitDiaVisible = false;
      this.options = false;
      // 로그인, 비로그인에 따라 화면push
      if (this.$cookies.get("PID_AUTH")) {
        const token = this.$cookies.get("PID_AUTH");
        const decoded = jwt_decode(token);
        const type = decoded.type;
        if (type == 0) {
          const loading = this.$loading({
            lock: true,
            text: "Loading",
            spinner: "el-icon-loading",
            background: "rgba(0, 0, 0, 0.7)",
          });
          setTimeout(() => {
            loading.close();
            this.$router.push("/user");
          }, 1000);
        }
        if (type == 1) {
          const loading = this.$loading({
            lock: true,
            text: "Loading",
            spinner: "el-icon-loading",
            background: "rgba(0, 0, 0, 0.7)",
          });
          setTimeout(() => {
            loading.close();
            this.$router.push("/company");
          }, 1000);
        }
      } else {
        const loading = this.$loading({
          lock: true,
          text: "Loading",
          spinner: "el-icon-loading",
          background: "rgba(0, 0, 0, 0.7)",
        });
        setTimeout(() => {
          loading.close();
          this.$router.push("/");
        }, 1000);
      }
    },
    //
    receiveVideo(sender) {
      var participant = new this.Participant(sender, this.sendMessage);
      participants[sender] = participant;
      var video = participant.getVideoElement();

      var options = {
        remoteVideo: video,
        onicecandidate: participant.onIceCandidate.bind(participant),
      };

      participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(
        options,
        function(error) {
          if (error) {
            return console.error(error);
          }
          this.generateOffer(participant.offerToReceiveVideo.bind(participant));
        }
      );
    },

    onParticipantLeft(request) {
      console.log("Participant " + request.name + " left");
      var participant = participants[request.name];
      participant.dispose();
      delete participants[request.name];
    },

    sendMessage(message) {
      var jsonMessage = JSON.stringify(message);
      console.log("Sending message: " + jsonMessage);
      ws.send(jsonMessage);
    },

    Participant(name, sendMessage) {
      this.name = name;
      var container = document.createElement("div");
      //container.className = isPresentMainParticipant() ? PARTICIPANT_CLASS : PARTICIPANT_MAIN_CLASS;
      container.id = name;
      container.classList.add("video-block");
      var video = document.createElement("video");
      var rtcPeer;
      var usernameArea = document.createElement("div");
      usernameArea.innerText = this.name;

      container.appendChild(video);
      container.appendChild(usernameArea);

      //container.onclick = switchContainerClass;
      document.getElementById("participants").appendChild(container);
      container.classList.add("scale-in-center");
      // container.classList.add("animation-init");
      // setTimeout(function() {
      //   container.classList.add("animation-fade"); //
      // }, 30);

      video.id = "video-" + name;
      video.autoplay = true;
      video.controls = false;

      this.getElement = function() {
        return container;
      };

      this.getVideoElement = function() {
        return video;
      };

      // function switchContainerClass() {
      //   if (container.className === PARTICIPANT_CLASS) {
      //     var elements = Array.prototype.slice.call(
      //       document.getElementsByClassName(PARTICIPANT_MAIN_CLASS)
      //     );
      //     elements.forEach(function(item) {
      //       item.className = PARTICIPANT_CLASS;
      //     });

      //     container.className = PARTICIPANT_MAIN_CLASS;
      //   } else {
      //     container.className = PARTICIPANT_CLASS;
      //   }
      // }

      // function isPresentMainParticipant() {
      //   return document.getElementsByClassName(PARTICIPANT_MAIN_CLASS).length != 0;
      // }

      this.offerToReceiveVideo = function(error, offerSdp) {
        if (error) return console.error("sdp offer error");
        console.log("Invoking SDP offer callback function");
        var msg = { id: "receiveVideoFrom", sender: name, sdpOffer: offerSdp };
        sendMessage(msg);
      };

      this.onIceCandidate = function(candidate) {
        console.log("Local candidate" + JSON.stringify(candidate));

        var message = {
          id: "onIceCandidate",
          candidate: candidate,
          name: name,
        };
        sendMessage(message);
      };

      Object.defineProperty(this, "rtcPeer", { writable: true });

      this.dispose = function() {
        console.log("Disposing participant " + this.name);
        console.log(rtcPeer);
        this.rtcPeer.dispose();
        container.parentNode.removeChild(container);
      };
    },
    AudioOnOff() {
      if (this.audioOn) {
        participants[this.username].rtcPeer.audioEnabled = false;
        this.audioOn = false;
      } else {
        participants[this.username].rtcPeer.audioEnabled = true;
        this.audioOn = true;
      }
    },
    VideoOnOff() {
      if (this.videoOn) {
        participants[this.username].rtcPeer.videoEnabled = false;
        this.videoOn = false;
      } else {
        participants[this.username].rtcPeer.videoEnabled = true;
        this.videoOn = true;
      }
    },
    // handleClose(done) {
    //   this.$confirm("설정을 완료 하시겠습니까?")
    //     .then(() => {
    //       done();
    //       this.dialogVisible = false;
    //     })
    //     .catch((err) => {
    //
    //
    //       if (err.response == 401) {
    //         this.$message.error("로그인세션이 만료되었습니다");
    //         localStorage.clear();
    //         this.$router.push("/");
    //       }
    //     });
    // },
  },
};
</script>

<style>
.scroll {
  width: 300px;
  padding: 0px 13px 0px 13px;
  overflow-y: scroll;
  height: 400px;
  box-sizing: border-box;
  color: black;
  font-family: "Nanum Gothic";
  margin-right: 50px;
}

/* 스크롤바 설정*/
.type1::-webkit-scrollbar {
  width: 6px;
}

/* 스크롤바 막대 설정*/
.type1::-webkit-scrollbar-thumb {
  height: 17%;
  background-color: rgba(0, 0, 0, 0.5);
  /* 스크롤바 둥글게 설정    */
  border-radius: 10px;
}

/* 스크롤바 뒷 배경 설정*/
.type1::-webkit-scrollbar-track {
  background-color: rgba(0, 0, 0, 0);
}

.itemright {
  text-align: right;
  padding-right: 10px;
  justify-content: flex-end;
  display: flex;
}
.itemleft {
  text-align: left;
  padding-left: 10px;
}
.speech-bubble-left {
  position: relative;
  background: #f2f3f4;
  border-radius: 0.4em;
  /* width: 200px; */
  margin-right: 20px;
  word-break: break-all;
  padding: 5px;
}

.speech-bubble-left:after {
  content: "";
  position: absolute;
  left: 0;
  top: 50%;
  width: 0;
  height: 0;
  border: 10px solid transparent;
  border-right-color: #f2f3f4;
  border-left: 0;
  border-top: 0;
  margin-top: -5px;
  margin-left: -10px;
}

.speech-bubble {
  position: relative;
  background: #ffc000;
  border-radius: 0.4em;
  /* width: 200px; */
  margin-left: 20px;
  word-break: break-all;
  padding: 5px;
}

.speech-bubble:after {
  content: "";
  position: absolute;
  right: 0;
  top: 50%;
  width: 0;
  height: 0;
  border: 10px solid transparent;
  border-left-color: #ffc000;
  border-right: 0;
  border-top: 0;
  margin-top: -5px;
  margin-right: -10px;
}
.go {
  width: 200px;
  border-radius: 150px !important;
}
.footer {
  background-color: whitesmoke;
  position: fixed;
  bottom: 0;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.footer span {
  padding: 10px;
}
.wrapper {
  display: grid;
  grid: ". . .";
  gap: 1%;
  justify-content: center;
  align-items: center;
}
.video-block {
  transition: all 1s;
  justify-content: center;
  align-content: center;
  border-width: 3px;
  border-style: solid;
  border-color: whitesmoke;
  border-radius: 3% 3% 3% 3%;
  overflow: hidden;
  background-color: black;
}
.video-block:hover {
  box-shadow: 5px 5px 5px gray;
}
.video-block video {
  width: 36rem;
  height: 27rem;
  z-index: -2;
  position: relative;
}

.video-block div {
  position: absolute;
  background-color: black;
  color: white;
  z-index: 200;
  text-align: center;
  opacity: 0.75;
  bottom: 0;
  right: 0;
  padding: 0.3rem;
  padding-left: 1rem;
  padding-right: 2rem;
}
.titleBox {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  top: 10px;
}
.title span {
  font-family: "Work Sans", sans-serif;
  font-size: 60px;
}
.f {
  color: #ffc000;
}
.scale-in-center {
  animation: scale-in-center 0.5s cubic-bezier(0.25, 0.46, 0.45, 0.94) both;
}
.slit-out-vertical {
  animation: slit-out-vertical 0.5s ease-in both;
}
@keyframes scale-in-center {
  0% {
    transform: scale(0);
    opacity: 1;
  }
  100% {
    transform: scale(1);
    opacity: 1;
  }
}

@keyframes slit-out-vertical {
  0% {
    transform: translateZ(0) rotateY(0);
    opacity: 1;
  }
  54% {
    transform: translateZ(-160px) rotateY(87deg);
    opacity: 1;
  }
  100% {
    transform: translateZ(-800px) rotateY(90deg);
    opacity: 0;
  }
}
#circle2 {
  background-color: #ff0000;
  border: 1px solid #ff0000;
  width: 15px;
  height: 15px;
  border-radius: 75px;
  text-align: center;
  margin: -30px 0px 0px 110px;
  /* font-size: 12px;
  color: #fff; */

  /* vertical-align: middle; */
  /* line-height: 100px; */
}
</style>
