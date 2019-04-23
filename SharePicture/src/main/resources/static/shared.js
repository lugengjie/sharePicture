
var socket = new WebSocket("ws://localhost:8080/websocket");
onconnect = function(e) {
  var port = e.ports[0];
  port.onmessage = function(e) {
    if(e.data=='start'){

    	
    }
  }
}