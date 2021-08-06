<template>
  <!-- <join-form></join-form> -->
  <div>
    asldkfja;lksdjfa;kldj
    <input v-model="name" placeholder="nameadfasd" />
    <input v-model="room" placeholder="roomadasdf" />
    <button @click="register">join</button>
  </div>
  <div id="room" style="display: none;">
    <h2 id="room-header"></h2>
    <div id="participants"></div>
    <input
      type="button"
      id="button-leave"
      onmouseup="leaveRoom();"
      value="Leave room"
    />
  </div>
</template>

<script>
// import kurentoUtils from "kurento-utils";
// import adapter from "webrtc-adapter";
import wsk from "../components/utils/websoc.js";
var participants = {};
// const PARTICIPANT_MAIN_CLASS = "participant main";
// const PARTICIPANT_CLASS = "participant";
// var ws = new WebSocket("wss://i5d206.p.ssafy.io:8443/groupcall");
wsk.onmessage = (message) => {
  var parsedMessage = JSON.parse(message.data);
  console.info("Received message: " + message.data);

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
export default {
  components: {
    // JoinForm,
  },

  mounted() {},
  data() {
    return {
      ws: wsk,
      name: "",
      room: "",
    };
  },
  methods: {
    // this.ws.onmessage = (message) => {
    // var parsedMessage = JSON.parse(message.data);
    // console.info("Received message: " + message.data);
    // switch (parsedMessage.id) {
    //   case "existingParticipants":
    //     this.onExistingParticipants(parsedMessage);
    //     break;
    //   case "newParticipantArrived":
    //     this.onNewParticipant(parsedMessage);
    //     break;
    //   case "participantLeft":
    //     this.onParticipantLeft(parsedMessage);
    //     break;
    //   case "receiveVideoAnswer":
    //     this.receiveVideoResponse(parsedMessage);
    //     break;
    //   case "iceCandidate":
    //     participants[parsedMessage.name].rtcPeer.addIceCandidate(
    //       parsedMessage.candidate,
    //       function(error) {
    //         if (error) {
    //           console.error("Error adding candidate: " + error);
    //           return;
    //         }
    //       }
    //     );
    //     break;
    //   default:
    //     console.error("Unrecognized message", parsedMessage);
    // };
    // sendMessage=(message)=>{
    //   var jsonMessage = JSON.stringify(message);
    //   console.log("Sending message: " + jsonMessage);
    //   ws.send(jsonMessage);
    // };
    // register=()=> {
    //   name = document.getElementById("name").value;
    //   var room = document.getElementById("roomName").value;
    //   document.getElementById("room-header").innerText = "ROOM " + room;
    //   document.getElementById("join").style.display = "none";
    //   document.getElementById("room").style.display = "block";
    //   var message = {
    //     id: "joinRoom",
    //     name: name,
    //     room: room,
    //   };
    //   sendMessage(message);
    // };
    // onNewParticipant=(request)=>{
    //   this.receiveVideo(request.name);
    // };
    // receiveVideoResponse=(result)=>{
    //   participants[result.name].rtcPeer.processAnswer(result.sdpAnswer, function(
    //     error
    //   ) {
    //     if (error) return console.error(error);
    //   });
    // };
    // callResponse=(message)=>{
    //   if (message.response != "accepted") {
    //     console.info("Call not accepted by peer. Closing call");
    //     stop();
    //   } else {
    //     adapter.webRtcPeer.processAnswer(message.sdpAnswer, function(error) {
    //       if (error) return console.error(error);
    //     });
    //   }
    // };
    // onExistingParticipants=(msg)=>{
    //   var constraints = {
    //     audio: true,
    //     video: {
    //       mandatory: {
    //         maxWidth: 320,
    //         maxFrameRate: 15,
    //         minFrameRate: 15,
    //       },
    //     },
    //   };
    //   // console.log(name + " registered in room " + room);
    //   var participant = new Participant(name);
    //   participants[name] = participant;
    //   var video = participant.getVideoElement();
    //   var options = {
    //     localVideo: video,
    //     mediaConstraints: constraints,
    //     onicecandidate: participant.onIceCandidate.bind(participant),
    //   };
    //   participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(
    //     options,
    //     function(error) {
    //       if (error) {
    //         return console.error(error);
    //       }
    //       this.generateOffer(participant.offerToReceiveVideo.bind(participant));
    //     }
    //   );
    //   msg.data.forEach(this.receiveVideo);
    // };
    // leaveRoom=()=>{
    //   this.sendMessage({
    //     id: "leaveRoom",
    //   });
    //   for (var key in participants) {
    //     participants[key].dispose();
    //   }
    //   document.getElementById("join").style.display = "block";
    //   document.getElementById("room").style.display = "none";
    //   ws.close();
    // }
    // receiveVideo=(sender)=>{
    //   var participant = new Participant(sender);
    //   participants[sender] = participant;
    //   var video = participant.getVideoElement();
    //   var options = {
    //     remoteVideo: video,
    //     onicecandidate: participant.onIceCandidate.bind(participant),
    //   };
    //   participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(
    //     options,
    //     function(error) {
    //       if (error) {
    //         return console.error(error);
    //       }
    //       this.generateOffer(participant.offerToReceiveVideo.bind(participant));
    //     }
    //   );
    // },
    // onParticipantLeft=(request)=>{
    //   console.log("Participant " + request.name + " left");
    //   var participant = participants[request.name];
    //   participant.dispose();
    //   delete participants[request.name];
    // },
    // register() {
    //   // this.name = document.getElementById("name").value;
    //   // var room = document.getElementById("roomName").value;
    //   document.getElementById("room-header").innerText = "ROOM " + this.room;
    //   document.getElementById("join").style.display = "none";
    //   document.getElementById("room").style.display = "block";
    //   var message = {
    //     id: "joinRoom",
    //     name: this.name,
    //     room: this.room,
    //   };
    //   this.sendMessage(message);
    // },
    // onNewParticipant(request) {
    //   this.receiveVideo(request.name);
    // },
    // receiveVideoResponse(result) {
    //   participants[result.name].rtcPeer.processAnswer(
    //     result.sdpAnswer,
    //     function(error) {
    //       if (error) return console.error(error);
    //     }
    //   );
    // },
    // callResponse(message) {
    //   if (message.response != "accepted") {
    //     console.info("Call not accepted by peer. Closing call");
    //     stop();
    //   } else {
    //     adapter.webRtcPeer.processAnswer(message.sdpAnswer, function(error) {
    //       if (error) return console.error(error);
    //     });
    //   }
    // },
    // onExistingParticipants(msg) {
    //   var constraints = {
    //     audio: true,
    //     video: {
    //       mandatory: {
    //         maxWidth: 320,
    //         maxFrameRate: 15,
    //         minFrameRate: 15,
    //       },
    //     },
    //   };
    //   // console.log(name + " registered in room " + room);
    //   var participant = new this.Participant(this.name);
    //   participants[this.name] = participant;
    //   var video = participant.getVideoElement();
    //   var options = {
    //     localVideo: video,
    //     mediaConstraints: constraints,
    //     onicecandidate: participant.onIceCandidate.bind(participant),
    //   };
    //   participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(
    //     options,
    //     function(error) {
    //       if (error) {
    //         return console.error(error);
    //       }
    //       this.generateOffer(participant.offerToReceiveVideo.bind(participant));
    //     }
    //   );
    //   msg.data.forEach(this.receiveVideo);
    // },
    // leaveRoom() {
    //   this.sendMessage({
    //     id: "leaveRoom",
    //   });
    //   for (var key in participants) {
    //     participants[key].dispose();
    //   }
    //   document.getElementById("join").style.display = "block";
    //   document.getElementById("room").style.display = "none";
    //   ws.close();
    // },
    // receiveVideo(sender) {
    //   var participant = new this.Participant(sender);
    //   participants[sender] = participant;
    //   var video = participant.getVideoElement();
    //   var options = {
    //     remoteVideo: video,
    //     onicecandidate: participant.onIceCandidate.bind(participant),
    //   };
    //   participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerRecvonly(
    //     options,
    //     function(error) {
    //       if (error) {
    //         return console.error(error);
    //       }
    //       this.generateOffer(participant.offerToReceiveVideo.bind(participant));
    //     }
    //   );
    // },
    // onParticipantLeft(request) {
    //   console.log("Participant " + request.name + " left");
    //   var participant = participants[request.name];
    //   participant.dispose();
    //   delete participants[request.name];
    // },
    // sendMessage(message) {
    //   var jsonMessage = JSON.stringify(message);
    //   console.log("Sending message: " + jsonMessage);
    //   ws.send(jsonMessage);
    // },
    // Participant(name) {
    //   this.name = name;
    //   var container = document.createElement("div");
    //   container.className = isPresentMainParticipant()
    //     ? PARTICIPANT_CLASS
    //     : PARTICIPANT_MAIN_CLASS;
    //   container.id = name;
    //   var span = document.createElement("span");
    //   var video = document.createElement("video");
    //   // var rtcPeer;
    //   container.appendChild(video);
    //   container.appendChild(span);
    //   container.onclick = switchContainerClass;
    //   document.getElementById("participants").appendChild(container);
    //   span.appendChild(document.createTextNode(name));
    //   video.id = "video-" + name;
    //   video.autoplay = true;
    //   video.controls = false;
    //   this.getElement = function() {
    //     return container;
    //   };
    //   this.getVideoElement = function() {
    //     return video;
    //   };
    //   function switchContainerClass() {
    //     if (container.className === PARTICIPANT_CLASS) {
    //       var elements = Array.prototype.slice.call(
    //         document.getElementsByClassName(PARTICIPANT_MAIN_CLASS)
    //       );
    //       elements.forEach(function(item) {
    //         item.className = PARTICIPANT_CLASS;
    //       });
    //       container.className = PARTICIPANT_MAIN_CLASS;
    //     } else {
    //       container.className = PARTICIPANT_CLASS;
    //     }
    //   }
    //   function isPresentMainParticipant() {
    //     return (
    //       document.getElementsByClassName(PARTICIPANT_MAIN_CLASS).length != 0
    //     );
    //   }
    //   this.offerToReceiveVideo = function(error, offerSdp) {
    //     if (error) return console.error("sdp offer error");
    //     console.log("Invoking SDP offer callback function");
    //     var msg = { id: "receiveVideoFrom", sender: name, sdpOffer: offerSdp };
    //     this.sendMessage(msg);
    //   };
    //   this.onIceCandidate = function(candidate) {
    //     console.log("Local candidate" + JSON.stringify(candidate));
    //     var message = {
    //       id: "onIceCandidate",
    //       candidate: candidate,
    //       name: name,
    //     };
    //     this.sendMessage(message);
    //   };
    //   Object.defineProperty(this, "rtcPeer", { writable: true });
    //   this.dispose = function() {
    //     console.log("Disposing participant " + this.name);
    //     this.rtcPeer.dispose();
    //     container.parentNode.removeChild(container);
    //   };
    // },
  },
};
</script>

<style></style>
