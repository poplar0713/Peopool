<template>
  <el-container class="base">
    <el-header class="titleBox">
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
      <el-main>
        <div id="container">
          <div>
            <div id="join" class="animate join">
              <h1>Join a Room</h1>
              <el-form v-on:submit.prevent="onSubmit" accept-charset="UTF-8">
                <p>
                  <input
                    type="text"
                    name="name"
                    v-model="username"
                    id="name"
                    placeholder="Username"
                    required
                  />
                </p>
                <p>
                  <input
                    type="text"
                    name="room"
                    v-model="room"
                    id="roomName"
                    placeholder="Room"
                    required
                  />
                </p>
                <p class="submit">
                  <input type="submit" name="commit" value="Join!" v-on:click="register" />
                </p>
              </el-form>
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
    </el-container>
    <el-footer v-if="this.options" class="footer">
      <span>
        <el-button
          round
          v-if="this.audioOn"
          type="warning"
          id="button-audio"
          v-on:click="AudioOnOff"
          value="Audio Off"
          >음소거</el-button
        >
        <el-button
          round
          v-else
          type="success"
          id="button-audio"
          v-on:click="AudioOnOff"
          value="Audio On"
          >음소거 해제</el-button
        ></span
      >
      <span
        ><el-button
          round
          v-if="this.videoOn"
          type="warning"
          id="button-video"
          v-on:click="VideoOnOff"
          value="Video Off"
          >비디오 Off</el-button
        >
        <el-button
          round
          v-else
          type="success"
          id="button-video"
          v-on:click="VideoOnOff"
          value="Video On"
          >비디오 On</el-button
        ></span
      ><span>
        <el-button
          round
          type="success"
          id="button-setting"
          @click="this.dialogVisible = true"
          value="Setting"
          >설정</el-button
        ></span
      >
      <span
        ><el-button round type="danger" id="button-leave" @click="exitDiaVisible = true">
          X</el-button
        ></span
      >
    </el-footer>
  </el-container>

  <el-dialog
    v-model="this.dialogVisible"
    style="width: 100%; height:100%"
    title="설정"
    :before-close="handleClose"
  >
    설정을 할 수 있는 곳이 될 것
  </el-dialog>

  <el-dialog v-model="exitDiaVisible" width="30%">
    <span>면접장에서 나가시겠습니까?</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="exitDiaVisible = false">아니요</el-button>
        <el-button type="danger" @click="leaveRoom">퇴장하기</el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script>
import kurentoUtils from "kurento-utils";
import adapter from "webrtc-adapter";
//const PARTICIPANT_MAIN_CLASS = "participant main";
//const PARTICIPANT_CLASS = "participant";
var ws = null;
var participants = {};

export default {
  data() {
    return {
      room: null,
      username: null,
      audioOn: true,
      videoOn: true,
      options: false,
      dialogVisible: false,
      exitDiaVisible: false,
    };
  },
  name: "InterviewRoom",
  mounted: function() {
    console.log(adapter.browserDetails.browser);
    ws = new WebSocket("wss://i5d206.p.ssafy.io:8443/groupcall");

    ws.onmessage = (message) => {
      var parsedMessage = JSON.parse(message.data);
      console.info("Received message: " + message);

      switch (parsedMessage.id) {
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
      console.log("Websocket is connected!");
    };
  },
  methods: {
    register() {
      document.getElementById("room-header").innerText = this.room;
      document.getElementById("join").style.display = "none";
      document.getElementById("room").style.display = "block";
      this.options = true;
      var message = {
        id: "joinRoom",
        name: this.username,
        room: this.room,
      };

      this.sendMessage(message);
    },

    onNewParticipant(request) {
      this.receiveVideo(request.name);
    },

    receiveVideoResponse(result) {
      participants[result.name].rtcPeer.processAnswer(result.sdpAnswer, function(error) {
        if (error) return console.error(error);
      });
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
            maxWidth: 650,
            maxHeight: 650,
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
      participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(options, function(
        error
      ) {
        if (error) {
          return console.error(error);
        }
        this.generateOffer(participant.offerToReceiveVideo.bind(participant));
      });

      msg.data.forEach(this.receiveVideo);
    },

    leaveRoom() {
      this.sendMessage({
        id: "leaveRoom",
      });

      for (var key in participants) {
        participants[key].dispose();
      }

      document.getElementById("join").style.display = "block";
      document.getElementById("room").style.display = "none";
      this.exitDiaVisible = false;
      this.options = false;

      ws.close();
    },

    receiveVideo(sender) {
      var participant = new this.Participant(sender, this.sendMessage);
      participants[sender] = participant;
      var video = participant.getVideoElement();

      var options = {
        remoteVideo: video,
        onicecandidate: participant.onIceCandidate.bind(participant),
      };

      participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(options, function(
        error
      ) {
        if (error) {
          return console.error(error);
        }
        this.generateOffer(participant.offerToReceiveVideo.bind(participant));
      });
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
      container.className = "participant";
      //container.className = isPresentMainParticipant() ? PARTICIPANT_CLASS : PARTICIPANT_MAIN_CLASS;
      container.id = name;
      container.className = "videoBlock";
      var video = document.createElement("video");
      var rtcPeer;
      container.appendChild(video);
      //container.onclick = switchContainerClass;
      document.getElementById("participants").appendChild(container);

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
      var audiobutton = document.getElementById("button-audio");
      if (audiobutton.value == "Audio Off") {
        participants[this.username].rtcPeer.audioEnabled = false;
        this.audioOn = false;
      } else {
        participants[this.username].rtcPeer.audioEnabled = true;
        this.audioOn = true;
      }
    },
    VideoOnOff() {
      var videobutton = document.getElementById("button-video");
      if (videobutton.value == "Video Off") {
        participants[this.username].rtcPeer.videoEnabled = false;
        this.videoOn = false;
      } else {
        participants[this.username].rtcPeer.videoEnabled = true;
        this.videoOn = true;
      }
    },
    handleClose(done) {
      this.$confirm("설정을 완료 하시겠습니까?")
        .then(() => {
          done();
          this.dialogVisible = false;
        })
        .catch(() => {});
    },
  },
};
</script>

<style>
.footer {
  background-color: whitesmoke;
  position: fixed;
  bottom: 0;
  width: 100%;
  display: flex;
  justify-content: center;
  align-items: center;
}
.wrapper {
  display: grid;
  grid: ". . .";
  gap: 2%;
  justify-content: center;
  align-items: center;
}
.videoBlock {
  justify-content: center;
  align-items: center;
  border-width: 3px;
  border-style: solid;
  border-color: whitesmoke;
  margin: 1%;
  border-radius: 3% 3% 3% 3%;
  overflow: hidden;
}
.videoBlock:hover {
  box-shadow: 5px 5px 5px gray;
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
</style>
