<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>웹소켓 채팅</title>
<link rel="stylesheet" href="../resources/css/chat.css">
</head>
<body>
	<input type="hidden" id="user_id" value="${userId}">
	<input type="hidden" id="url" value="${url}">
	<div id="chat_box">
		<div id="divChatData">
			<div id="count_div">
				현재 접속자 수 : <span id="cu"></span>
			</div>
		</div>
		<div id="chat_ctrl">
			<input type="text" id="message" /> <input type="button" id="btnSend"
				value="채팅 전송" />
		</div>
	</div>
	<!-- <div id="chat_btn">💬</div> -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/sockjs-client/1.4.0/sockjs.js"></script>
	<script>
	$(window).on('load', function () {
		webSocket.init({ url: '<c:url value="/chat" />' });	
	});
	
	const webSocket = {
			init: function(param) {
				this._url = param.url;
				this._initSocket();
			},
			sendChat: function() {
				this._sendMessage('${param.chat_url}', 'CMD_MSG_SEND', $("#user_id").val(), $('#message').val().replace("<","&lt;").replace(">","&gt;"));
				$('#message').val('');
			},
			sendEnter: function() {
				this._sendMessage('${param.chat_url}', 'CMD_ENTER', $("#user_id").val(), $('#message').val().replace("<","&lt;").replace(">","&gt;"));
				$('#message').val('');
			},
			receiveMessage: function(msgData) {

				// 정의된 CMD 코드에 따라서 분기 처리
				if(msgData.cmd == 'CMD_MSG_SEND') {
					const a = new Date();
					$('#divChatData').append(`<div class="chats"><div class="ids" data-id=`+msgData.id+`>`+msgData.id+`<span class="dates">`+a.getFullYear()+`-`+(a.getMonth()+1)+`-`+a.getDate()+`  `+a.getHours()+`:`+a.getMinutes()+`:`+a.getSeconds()+`</span></div> <div class="msgs">`+msgData.msg+`</div></div>`);
					$("#divChatData").scrollTop($("#divChatData")[0].scrollHeight);
					$(".ids").off("click").on("click", function () {
						console.log($(this).data("id"));
					})
				}
				// 입장
				else if(msgData.cmd == 'CMD_ENTER') {
					/* $('#divChatData').append('<div>' + msgData.msg + '</div>'); */
					$("#cu").html(msgData.count);
				}
				// 퇴장
				else if(msgData.cmd == 'CMD_EXIT') {					
					/* $('#divChatData').append('<div>' + msgData.msg + '</div>'); */
					$("#cu").html(Number($("#cu").html()) - 1);
				}
			},
			closeMessage: function(str) {
				$('#divChatData').append('<div>' + '연결 끊김 : ' + str + '</div>');
			},
			disconnect: function() {
				this._socket.close();
			},
			_initSocket: function() {
				this._socket = new SockJS(this._url);
				this._socket.onopen = function() {
					webSocket.sendEnter();
				};
				this._socket.onmessage = function(e) {
					webSocket.receiveMessage(JSON.parse(e.data));
				};
				this._socket.onclose = function(e) {
					webSocket.closeMessage(JSON.parse(e.data));
				}
			},
			_sendMessage: function(chat_url, cmd, id, msg) {
				const msgData = {
						chat_url,
						cmd,
						id,
						msg
				};
				const jsonData = JSON.stringify(msgData);
				this._socket.send(jsonData);
			}
	};
	</script>
	<script src="../resources/js/chat.js"></script>
</body>
</html>