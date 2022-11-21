/**
 * 
 */


$(document).on("keyup", function(e){
	if($("#user_id").val() == ""){
		return false;
	}
	if($("#message").css("display") != "none" && e.keyCode == 13 && $("#message").val() != ""){
		webSocket.sendChat();
	}
})

$("#btnSend").on("click", function() {
	if($("#user_id").val() == "" || $("#message").val() == ""){
		return false;
	}
	webSocket.sendChat();
})

