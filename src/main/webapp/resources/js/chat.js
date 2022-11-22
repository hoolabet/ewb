/**
 * 
 */

$(document).on("keyup", function(e){
	if($("#user_id").val() == ""){
		return false;
	}
	if($("#message").css("display") != "none" && e.keyCode == 13 && $("#message").val() != ""){
		const cData = {
				id:$("#user_id").val(),
				url:$("#url").val(),
				content:$("#message").val()
		}
		$.ajax({
			type:"post",
			url:"/insertchat",
			data:JSON.stringify(cData),
			contentType:"application/json; charset=utf-8",
			success: function() {
				webSocket.sendChat();
			}
		})
	}
})

$("#btnSend").on("click", function() {
	if($("#user_id").val() == "" || $("#message").val() == ""){
		return false;
	}
	const cData = {
			id:$("#user_id").val(),
			url:$("#url").val(),
			content:$("#message").val()
	}
	$.ajax({
		type:"post",
		url:"/insertchat",
		data:JSON.stringify(cData),
		contentType:"application/json; charset=utf-8",
		success: function() {
			webSocket.sendChat();
		}
	})
})


