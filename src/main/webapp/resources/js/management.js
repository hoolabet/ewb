/**
 * 
 */
const url = $("#url").val();

$("#to_main").on("click", function() {
	location.href="/main";
})

$("#delete").on("click", function() {
	if(prompt(`삭제하시겠습니까?
		삭제하시려면 "삭제한다/${url}" 을 입력하세요.`) == `삭제한다/${url}`){
		$.ajax({
			type:"delete",
			url:"/deletecontent",
			data:JSON.stringify({url,type:"home_page"}),
			contentType: "application/json; charset=utf-8",
			success: function() {
				alert("삭제되었습니다.");
				location.href = "/main";
			}
		})
	}
})

$(".status").on("click", function() {
	const href = $(this).data("href");
	location.href = `/${url}/${href}`;
})