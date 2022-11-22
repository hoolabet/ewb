/**
 * 
 */

$("#chat_btn").on("click", function(e) {
	const dcd = $("#if").contents().find("#divChatData");
	
	if ($("#if").css("display") == "none") {
		$("#if").css("display", "flex").css("top", e.clientY-500).css("left", e.clientX-400);
		$(dcd).scrollTop($(dcd)[0].scrollHeight);
	} else {
		$("#if").css("display", "none");
	}
})

