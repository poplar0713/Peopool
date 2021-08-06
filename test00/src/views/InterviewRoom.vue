<template>
  <el-container>
    <el-aside width="200px"><SideBarUser /></el-aside>
    <el-container>
      <el-header><headerSearchCompany /></el-header>
      <el-main>
        <div id="container">
          <div id="wrapper">
            <div id="join" class="animate join">
              <before-meeting></before-meeting>
              <div style="text-align:center">
                <el-button type="warning" id="go" @click="register"
                  >입장하기</el-button
                >
              </div>
            </div>
            <div id="room" style="display: none;">
              <h2 id="room-header"></h2>
              <div id="participants"></div>
              <input
                type="button"
                id="button-leave"
                v-on:click="leaveRoom"
                value="Leave room"
              />
              <input
                type="button"
                id="button-audio"
                v-on:click="AudioOnOff"
                value="Audio Off"
              />
              <input
                type="button"
                id="button-video"
                v-on:click="VideoOnOff"
                value="Video Off"
              />
            </div>
          </div>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
import BeforeMeeting from "./beforeMettingRoom.vue";
import SideBarUser from "@/components/SideBarComponents/SideBarUser.vue";
import headerSearchCompany from "@/components/SideBarComponents/headerSearchCompany.vue";
import kurentoUtils from "kurento-utils";
import adapter from "webrtc-adapter";
const PARTICIPANT_MAIN_CLASS = "participant main";
const PARTICIPANT_CLASS = "participant";
var ws = null;
var participants = {};

export default {
  data() {
    return {
      room: null,
      username: null,
    };
  },
  components: {
    SideBarUser,
    headerSearchCompany,
    BeforeMeeting,
  },
  name: "InterviewRoom",
  mounted: function() {
    console.log(adapter.browserDetails.browser);
    ws = new WebSocket("wss://i5d206.p.ssafy.io:8443/groupcall");
    this.username = localStorage.getItem("username");
    this.room = this.$route.params.url;
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
      document.getElementById("room-header").innerText = "ROOM " + this.room;
      document.getElementById("join").style.display = "none";
      document.getElementById("room").style.display = "block";
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
            maxWidth: 320,
            maxFrameRate: 15,
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

      document.getElementById("join").style.display = "block";
      document.getElementById("room").style.display = "none";

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
      container.className = isPresentMainParticipant()
        ? PARTICIPANT_CLASS
        : PARTICIPANT_MAIN_CLASS;
      container.id = name;
      var span = document.createElement("span");
      var video = document.createElement("video");
      // var rtcPeer;

      container.appendChild(video);
      container.appendChild(span);
      container.onclick = switchContainerClass;
      document.getElementById("participants").appendChild(container);

      span.appendChild(document.createTextNode(name));

      video.id = "video-" + name;
      video.autoplay = true;
      video.controls = false;

      this.getElement = function() {
        return container;
      };

      this.getVideoElement = function() {
        return video;
      };

      function switchContainerClass() {
        if (container.className === PARTICIPANT_CLASS) {
          var elements = Array.prototype.slice.call(
            document.getElementsByClassName(PARTICIPANT_MAIN_CLASS)
          );
          elements.forEach(function(item) {
            item.className = PARTICIPANT_CLASS;
          });

          container.className = PARTICIPANT_MAIN_CLASS;
        } else {
          container.className = PARTICIPANT_CLASS;
        }
      }

      function isPresentMainParticipant() {
        return (
          document.getElementsByClassName(PARTICIPANT_MAIN_CLASS).length != 0
        );
      }

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
        this.rtcPeer.dispose();
        container.parentNode.removeChild(container);
      };
    },
    AudioOnOff() {
      var audiobutton = document.getElementById("button-audio");
      if (audiobutton.value == "Audio Off") {
        participants[this.username].rtcPeer.audioEnabled = false;
        audiobutton.value = "Audio On";
      } else {
        participants[this.username].rtcPeer.audioEnabled = true;
        audiobutton.value = "Audio Off";
      }
    },
    VideoOnOff() {
      var videobutton = document.getElementById("button-video");
      if (videobutton.value == "Video Off") {
        participants[this.username].rtcPeer.videoEnabled = false;
        videobutton.value = "Video On";
      } else {
        participants[this.username].rtcPeer.videoEnabled = true;
        videobutton.value = "Video Off";
      }
    },
  },
};
</script>

<style>
#go {
  width: 200px;
  border-radius: 100px;
}
</style>
