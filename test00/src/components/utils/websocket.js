// new WebSocket("wss://i5d206.p.ssafy.io:8443/groupcall");
import jwt_decode from "jwt-decode";
const token = getCookie("PID_AUTH");
const decoded = jwt_decode(token);
const index = decoded.index;
const ws = new WebSocket(`wss://i5d206.p.ssafy.io:8443/ws/${index}`);

export default ws;
function getCookie(name) {
  var value = document.cookie.match("(^|;) ?" + name + "=([^;]*)(;|$)");
  console.log(value);
  return value ? value[2] : null;
}
