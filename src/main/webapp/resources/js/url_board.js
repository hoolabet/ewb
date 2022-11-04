/**
 * 
 */
let url = $("#url").val();
if(url == ""){
	url = location.href.split("/")[3]
}
function getHF() {
	$.getJSON("/loadcontent",{type:"header",url},function(res){
		$("#header").html(res.content);
		$("#header").attr("style", $("#header_style").val());
		$("#footer").attr("style", $("#footer_style").val());
	})
	.done(function() {
		$("body")
		.css("margin-top", $("#save_margin").data("margint") + "px")
		.css("margin-bottom", $("#save_margin").data("marginb") + "px")
		.css("margin-left", $("#save_margin").data("marginl") + "px")
		.css("margin-right", $("#save_margin").data("marginr") + "px")
		.css("font-family",$("#save_font").data("font"))
		.css("font-size",$("#save_font").data("fontsize")+"px")
		;
		$("input[type='button']")
		.css("font-size", $("#save_button").data("ftsize") + "px")
		.css("padding", $("#save_button").data("padding") + "px")
		.css("background-color", $("#save_button").data("bgcolor"))
		.css("color", $("#save_button").data("ftcolor"))
		.css("border-width", $("#save_button").data("bdwidth"))
		.css("border-color", $("#save_button").data("bdcolor"));
		$("input[type='text']")
		.css("font-size", $("#save_text").data("ftsize") + "px")
		.css("padding", $("#save_text").data("padding") + "px")
		.css("background-color", $("#save_text").data("bgcolor"))
		.css("color", $("#save_text").data("ftcolor"))
		.css("border-width", $("#save_text").data("bdwidth"))
		.css("border-color", $("#save_text").data("bdcolor"));
		
	})
	$.getJSON("/loadcontent",{type:"footer",url},function(res){
		$("#footer").html(res.content);
	})
}

getHF();