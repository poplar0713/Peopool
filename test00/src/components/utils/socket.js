var ws = new WebSocket("wss://i5d206.p.ssafy.io:8443/groupcall");
var participants = {};
var name;
import kurentoUtils from "kurentoUtils";
import Participant from "./participant.js";
import adapter from "webrtc-adapter";

export { ws, participants, name };
export default {
  sendMessage(message) {
    var jsonMessage = JSON.stringify(message);
    console.log("Sending message: " + jsonMessage);
    ws.send(jsonMessage);
  },
  register() {
    name = document.getElementById("name").value;
    var room = document.getElementById("roomName").value;

    document.getElementById("room-header").innerText = "ROOM " + room;
    document.getElementById("join").style.display = "none";
    document.getElementById("room").style.display = "block";

    var message = {
      id: "joinRoom",
      name: name,
      room: room,
    };
    this.sendMessage(message);
  },

  onNewParticipant(request) {
    this.receiveVideo(request.name);
  },

  receiveVideoResponse(result) {
    participants[result.name].rtcPeer.processAnswer(result.sdpAnswer, function(
      error
    ) {
      if (error) return console.error(error);
    });
  },

  callResponse(message) {
    if (message.response != "accepted") {
      console.info("Call not accepted by peer. Closing call");
      stop();
    } else {
      adapter.webRtcPeer.processAnswer(message.sdpAnswer, function(error) {
        if (error) return console.error(error);
      });
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
    // console.log(name + " registered in room " + room);
    var participant = new Participant(name);
    participants[name] = participant;
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
    var participant = new Participant(sender);
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
};
