if (adapter.browserDetails.browser == "firefox") {
  adapter.browserShim.shimGetDisplayMedia(window, "screen");
}

function handleSuccess(stream) {
  startButton.disabled = true;
  const video = document.querySelector("video");
  video.srcObject = stream;

  // demonstrates how to detect that the user has stopped
  // sharing the screen via the browser UI.
  stream.getVideoTracks()[0].addEventListener("ended", () => {
    errorMsg("The user has ended sharing the screen");
    startButton.disabled = false;
  });
}

function handleError(error) {
  errorMsg(`getDisplayMedia error: ${error.name}`, error);
}

function errorMsg(msg, error) {
  const errorElement = document.querySelector("#errorMsg");
  errorElement.innerHTML += `<p>${msg}</p>`;
  if (typeof error !== "undefined") {
    console.error(error);
  }
}

const startButton = document.getElementById("startButton");
startButton.addEventListener("click", () => {
  alert("sss");
  navigator.mediaDevices
    .getDisplayMedia({ video: true })
    .then(handleSuccess, handleError);

  // var participant = new Participant(sender);
  // participants[sender] = participant;
  var participant = participants[name];
  var video = participant.getVideoElement();

  var options = {
    remoteVideo: video,
    onicecandidate: participant.onIceCandidate.bind(participant),
  };

  participant.rtcPeer = new kurentoUtils.WebRtcPeer.WebRtcPeerSendonly(
    options,
    function (error) {
      if (error) {
        return console.error(error);
      }
      this.generateOffer(participant.offerToReceiveVideo.bind(participant));
    }
  );
});

if (navigator.mediaDevices && "getDisplayMedia" in navigator.mediaDevices) {
  startButton.disabled = false;
} else {
  errorMsg("getDisplayMedia is not supported");
}

const videoButton = document.getElementById("videoButton");
videoButton.addEventListener("click", () => {
  alert("Click VideoButton");
  console.log(participants[name]);
  console.log(participants[name].rtcPeer.videoEnabled);
  if (videoButton.value == "false") {
    participants[name].rtcPeer.videoEnabled = true;
    videoButton.value = true;
    videoButton.innerText = "Video Off";
  }
  else {
    
    participants[name].rtcPeer.videoEnabled = false;
    videoButton.value = false;
    videoButton.innerText = "Video On";
  }
});

const audioButton = document.getElementById("audioButton");
audioButton.addEventListener("click", () => {
  alert("Click AudioButton");
  console.log(participants[name]);
  console.log(participants[name].rtcPeer.audioEnabled);
  if (audioButton.value == "false") {
    participants[name].rtcPeer.audioEnabled = true;
    audioButton.value = true;
    audioButton.innerText = "Audio Off";
  }
  else {
    
    participants[name].rtcPeer.audioEnabled = false;
    audioButton.value = false;
    audioButton.innerText = "Audio On";
  }
});