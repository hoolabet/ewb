/**
 * 
 */

$("#chat_btn").on("click", function(e) {
	if ($("#if").css("display") == "none") {
		$("#if").css("display", "flex").css("top", e.clientY-500).css("left", e.clientX-400);
	} else {
		$("#if").css("display", "none");
	}
})