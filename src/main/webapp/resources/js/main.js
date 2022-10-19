/**
 * 
 */
const id = $("#id").val();
if(id == ""){
	alert("로그인이 필요합니다.");
	location.href = "/";
}

$(document).on("click", function(e) {
	if(e.target.id != "create_new_page"){
		$("#new_page_option").css("display","none");
	}
	if(e.target.id != "edit_page"){
		$("#edit_page_option").css("display","none");
	}
})

$("#logout").on("click", function() {
	location.href = "/";
})

$("#create_new_page").on("click", function(e) {
	$("#new_page_option").css("display","flex").css("top",e.pageY+"px").css("left",e.pageX+"px");
})

$(".new_page_option").on("click", function() {
	const opt = $(this).prop("id");
	let url = prompt("url 주소이름을 적어주세요.");
	url = url.replace(" ","");
	if(url == null || url == ""){
		alert("url 주소를 입력하세요.");
		return false;
	}else if(url == "ewb"){
		alert("해당 url은 사용할 수 없습니다.");
		return false;
	}
	$.getJSON("/loadcontent",{url,type:"home_page"},function(){
		alert("이미 존재하는 url 입니다.");
	})
	.fail(function() {
		location.href = `/newpage?id=${id}&url=${url}&opt=${opt}`;
	})
})

$("#edit_page").on("click", function(e) {
	$("#edit_page_option").css("display","flex").css("top",e.pageY+"px").css("left",e.pageX+"px");
})

$(".edit_page").on("click", function() {
	const url = $(this).data("url");
	const opt = $(this).data("opt");
	location.href = `/loadpage?url=${url}&opt=${opt}`
})
