/**
 * 
 */
let url = $("#url").val();
if(url == ""){
	url = location.href.split("/")[3]
}
const userId = $("#user_id").val();


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

		if(userId != ""){
			$(".log").each(function(i,g) {
				const target = $(this).data("target");
				const ndnow = $(this).data("ndnow");

				$(`#li_a_${target}_${ndnow}`).attr("href",`/logout`);
				$(`#li_span_${target}_${ndnow}`).html("log out");
				$(`#li_span_${target}_${ndnow}_modi`).html("log out");
				$(`#li_a_${target}_${ndnow}_modi`).val(`/logout`);

				$(`#li_a_${target}_${ndnow}`).on("click", function(e) {
					e.preventDefault();
					$.getJSON("/logout",0,function(){
						console.log("haha");
						$(`#li_a_${target}_${ndnow}`).attr("href",`/${url}/login`);
						$(`#li_span_${target}_${ndnow}`).html("log in");
						$(`#li_span_${target}_${ndnow}_modi`).html("log in");
						$(`#li_a_${target}_${ndnow}_modi`).val(`/${url}/login`);
					})
					return location.reload();

				})
			})
			$(".login_table").css("display","none");
			$(".login_success").css("display","block");
			const ls = `
				<div><a href="/${url}/mypage">${userId}</a></div>
				`;
			$(".login_success").html(ls);
		}else{
			$(".log").each(function(i,g) {
				const target = $(this).data("target");
				const ndnow = $(this).data("ndnow");

				$(`#li_a_${target}_${ndnow}`).attr("href",`/${url}/login`);
				$(`#li_span_${target}_${ndnow}`).html("log in");
				$(`#li_span_${target}_${ndnow}_modi`).html("log in");
				$(`#li_a_${target}_${ndnow}_modi`).val(`/${url}/login`);
			})
			$(".login_btn").on("click", function() {
				const target = $(this).data("target");
				const id = $(`#login_id_${target}`).val();
				const pw = $(`#login_pw_${target}`).val();
				$.getJSON("/login",{id,pw,url},function(res){
					location.reload();
				})
				.fail(function() {
					alert("아이디와 비밀번호를 다시 확인하세요.");
				})
			})
			$(".signup_btn").on("click", function() {
				location.href = `/${url}/signup`;
			})
		}
	})
	$.getJSON("/loadcontent",{type:"footer",url},function(res){
		$("#footer").html(res.content);
		$("#footer").attr("style", $("#footer_style").val());
	})
}

getHF();

$("#dataPerPage").on("change", function() {
	$("input[name='amount']").val($(this).val());
	$("input[value='찾기']").click();
})

$("#dataPerPage").val($("input[name='amount']").val());

$("#add_product").on("click", function() {
	location.href = `/${url}/productwrite`;
})
