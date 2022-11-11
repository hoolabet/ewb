/**
 * 
 */

let url = $("#url").val();
if(url == ""){
	url = location.href.split("/")[3]
}
const ewbId = $("#ewb_id").val();
function getHF() {
	$.getJSON("/loadcontent",{type:"header",url},function(res){
		$("#header").html(res.content);
		$("#header").attr("style", $("#header_style").val());
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
		$("#footer").attr("style", $("#footer_style").val());
	})
}

function pageLoad(){
	$.getJSON("/loadcontent",{type:"login_page",url},function(res){
		$("#login_content").html(res.content);
		$("#login_content").attr("style", $("#login_style").val());
		loadFunc();
	})
	.fail(function() {
		$.ajax({
			type:"post",
			url:"/savecontent",
			data:JSON.stringify({url,content:$("#login_content").html(),type:"login_page"}),
			contentType: "application/json; charset=utf-8",
			success:function(){

			}
		})
	})
}

getHF();
pageLoad();
loadFunc();

$("#save").on("click", function() {
	$("#login_style").val($("#login_content").attr("style"));
	$.ajax({
		type:"put",
		url:"/modifycontent",
		data:JSON.stringify({url,content:$("#login_content").html(),type:"login_page"}),
		contentType: "application/json; charset=utf-8",
		success: function() {
			alert("저장되었습니다.");
		}
	})

})

$("#load").on("click", function() {
	pageLoad();
	loadFunc();
})

$('.cp').minicolors();

$(document).on("contextmenu", function (e) {
	e.preventDefault();
	if(ewbId == ""){
		return false;
	}
	
	if (e.target.id == "login_box" || e.target.id == "login_content") {
		$("#controller").data("target", e.target.id);
		$("#target_name").html(e.target.id);
		$("#controller").css("display", "none").css("display", "flex");
		menuClose();
		$("#controller").css("display", "flex");
		if(e.target.id == "login_content"){
			$("#color").css("display","none");
			$("#font_size").css("display","none");
		}else{
			$("#color").css("display","flex");
			$("#font_size").css("display","flex");
		}
	}
})



$(".close_btn").on("click", function () {
	$(this).parent().css("display", "none");
	if ($(this).parent().prop("id") == "controller") {
		menuClose();
	}
})

function menuClose() {
	$("#color_picker").css("display", "none");
	$("#border_menu").css("display", "none");
	$("#size_menu").css("display", "none");
	$("#font_size_menu").css("display", "none");
}

$(".colors").on("click", function (e) {
	menuClose();
	$("#color_change").data("css", $(this).prop("id"));
	$("#color_picker").css("display", "flex").css("top", e.clientY).css("left", e.clientX);
})

$("#color_change").on("click", function () {
	const css = $(this).data("css");
	const color = $("#cp").val();
	const target = $("#controller").data("target");

	$(`#${target}`).css(css, color);

})

$("#font_size").on("click", function(e){
	menuClose();
	const target = $("#controller").data("target");
	$("#font_size_input").val($(`#${target}`).css("font-size").replace("px",""));
	$("#font_size_menu").css("display","flex").css("top",e.clientY).css("left",e.clientX);
})

$("#font_size_apply").on("click", function(){
	const target = $("#controller").data("target");
	$(`#${target}`).css("font-size",$("#font_size_input").val()+"px");
	$(".input").css("font-size",$("#font_size_input").val()+"px");
})

$("#border").on("click", function(e){
	menuClose();
	const target = $("#controller").data("target");
	$("#border_menu").css("display","flex").css("top",e.clientY).css("left",e.clientX);
	$("#border_top").val($(`#${target}`).css("border-top-width").replace("px",""));
	$("#border_bottom").val($(`#${target}`).css("border-bottom-width").replace("px",""));
	$("#border_left").val($(`#${target}`).css("border-left-width").replace("px",""));
	$("#border_right").val($(`#${target}`).css("border-right-width").replace("px",""));
	$("#border_color").val($(`#${target}`).css("border-color"));
	$("#border_radius").val($(`#${target}`).css("border-radius").replace("px",""));
})

$("#border_apply").on("click", function(){
	const target = $("#controller").data("target");
	$(`#${target}`)
	.css("border","solid")
	.css("box-sizing","border-box")
	.css("border-top-width", $("#border_top").val()+"px")
	.css("border-bottom-width", $("#border_bottom").val()+"px")
	.css("border-left-width", $("#border_left").val()+"px")
	.css("border-right-width", $("#border_right").val()+"px")
	.css("border-color", $("#border_color").val())
	.css("border-radius", $("#border_radius").val()+"px")
	;
})

$("#size").on("click", function(e){
	menuClose();
	const target = $("#controller").data("target");
	$("#size_menu").css("display","flex").css("top",e.clientY).css("left",e.clientX);
	$("#size_width").val($(`#${target}`).css("width").replace("px",""));
	$("#size_height").val($(`#${target}`).css("height").replace("px",""));
})

$("#size_change").on("click", function(){
	const target = $("#controller").data("target");
	const width = $("#size_width").val();
	const height = $("#size_height").val();
	$(`#${target}`).css("width", width).css("height", height);
	$("#size_menu").css("display","none");
})

function loadFunc() {
	$(".modi_span").on("dblclick", function () {
		if(ewbId == ""){
			return false;
		}
		if (!$(this).attr("contenteditable")) {
			$(this).attr("contenteditable", true);
		} else {
			$(this).attr("contenteditable", false);
		}
	})
	
	$("#login_btn").on("click", function() {
		$.getJSON("/login",{url,id:$("#input_id").val(),pw:$("#input_pw").val()},function(){
			location.href = `/${url}/home`;
		})
		.fail(function() {
			$("#input_id").val("");
			$("#input_pw").val("");
		})
	})
	
	$("#signup_btn").on("click", function() {
		location.href = `/${url}/signup`;
	})
	
}