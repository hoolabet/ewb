/**
 * 
 */
const id = $("#id").val();
$(document).on("click", function(e) {
	if(e.target.id != "create_new_page"){
		$("#new_page_option").css("display","none");
	}
})

$("#create_new_page").on("click", function(e) {
	$("#new_page_option").css("display","flex").css("top",e.pageY+"px").css("left",e.pageX+"px");
})

$(".new_page_option").on("click", function() {
	const url = prompt("url 주소이름을 적어주세요.");
	location.href = `/newpage?id=${id}&url=${url}&option=${$(this).prop("id")}`;
})
