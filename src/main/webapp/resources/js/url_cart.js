/**
 * 
 */

let url = $("#url").val();
if(url == ""){
	url = location.href.split("/")[3]
}
const id = $("#user_id").val();
const userId = id;
if(id == ""){
	alert("로그인이 필요합니다.");
	location.href=`/${url}/login`;
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

		if(id != ""){
			$(".log").each(function(i,g) {
				const target = $(this).data("target");
				const ndnow = $(this).data("ndnow");

				$(`#li_a_${target}_${ndnow}`).attr("href",`/logout`);
				$(`#li_span_${target}_${ndnow}`).html("로그아웃");
				$(`#li_span_${target}_${ndnow}_modi`).html("로그아웃");
				$(`#li_a_${target}_${ndnow}_modi`).val(`/logout`);

				$(`#li_a_${target}_${ndnow}`).on("click", function(e) {
					e.preventDefault();
					$.getJSON("/logout",0,function(){
						console.log("haha");
						$(`#li_a_${target}_${ndnow}`).attr("href",`/${url}/login`);
						$(`#li_span_${target}_${ndnow}`).html("로그인");
						$(`#li_span_${target}_${ndnow}_modi`).html("로그인");
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
				$(`#li_span_${target}_${ndnow}`).html("로그인");
				$(`#li_span_${target}_${ndnow}_modi`).html("로그인");
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
	})
}

getHF();

$(".quan").on("click", function() {
	const pno = $(this).data("pno");
	const val = $(this).data("val");
	const pri = $(`#quan_${pno}`).data("price");
	const qua = $(`#quan_${pno}`).data("quantity");
	if(val == "down"){
		if($(`#quan_${pno}`).val() == "1"){
			return false;
		}else{
			$(`#quan_${pno}`).val($(`#quan_${pno}`).val() - 1)
		}
	}else if(val == "up"){
		if($(`#quan_${pno}`).val() == qua){
			return false;
		}else{
			$(`#quan_${pno}`).val(Number($(`#quan_${pno}`).val()) + 1)
		}
	}
	$(`#price_${pno}`).html(pri * $(`#quan_${pno}`).val());
	let fPrice = 0;
	$(".prices").each(function(i,p) {
		fPrice += Number($(p).html());
		$("#f_price").html(fPrice)
	})
})
let fPrice = 0;
$(".prices").each(function(i,p) {
	fPrice += Number($(p).html());
	$("#f_price").html(fPrice)
})

$("#order_selected_btn").on("click", function() {
	if($(".cart_check").length == 0){
		alert("주문할 상품이 없습니다.");
		return false;
	}
	$(".cart_check").each(function(i,c) {
		if($(c).prop("checked")){
			const pno = $(c).data("pno");
			const b_quantity = $(`#quan_${pno}`).val();
			const oData = {id,url,pno,doOrder:true, b_quantity}
			$.ajax({
				type:"put",
				url:"/orderselected",
				data:JSON.stringify(oData),
				contentType: "application/json; charset=utf-8",
				success: function() {

				}
			})
		}
	})
	setTimeout(() => {
		location.href= `/${url}/order`
	}, 500);
})

$("#order_all_btn").on("click", function() {
	$(".cart_check").prop("checked", true);
	$("#order_selected_btn").click();
})

$(".remove_btn").on("click", function() {
	const pno = $(this).data("pno");
	const rData = {url, id, pno}

	$.ajax({
		type:"delete",
		url:"/deletecart",
		data:JSON.stringify(rData),
		contentType: "application/json; charset=utf-8",
		success: function() {
			location.reload();
		}
	})
})