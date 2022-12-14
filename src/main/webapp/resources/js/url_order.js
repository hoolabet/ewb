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
				$(`#li_span_${target}_${ndnow}`).html("log out");
				$(`#li_span_${target}_${ndnow}_modi`).html("log out");
				$(`#li_a_${target}_${ndnow}_modi`).val(`/logout`);

				$(`#li_a_${target}_${ndnow}`).on("click", function(e) {
					e.preventDefault();
					$.getJSON("/logout",0,function(){
						
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
let pri = 0;
$(".prices").each(function(i,p) {
	pri += Number($(p).html())
	$("#f_price").html(pri)
})

$("#pay_btn").on("click", function() {
	const name = $("#name").val();
	const address = $("#address").val();
	const phone = $("#phone").val();
	const memo = $("#memo").val();
	if(name == "" || address == "" || phone == ""){
		alert("배송지 정보를 입력하세요.");
		return false;
	}
	const pData = {id,url,price:$("#f_price").html(),name,address,phone,memo}
	$.ajax({
		type:"post",
		url:"/payment",
		data:JSON.stringify(pData),
		contentType: "application/json; charset=utf-8",
		success: function() {
			$.getJSON("/searchordered",{url,id,doOrder:true},function(res){
				res.forEach(function(r) {
					$.ajax({
						type:"post",
						url:"/insertorder",
						data:JSON.stringify({id,pno:r.pno,b_quantity:r.b_quantity,url}),
						contentType: "application/json; charset=utf-8",
						success: function() {
							$.ajax({
								type:"delete",
								url:"/deletecart",
								data:JSON.stringify({id,url,pno:r.pno}),
								contentType: "application/json; charset=utf-8",
								success: function() {
									$.ajax({
										type:"put",
										url:"/updatequan",
										data:JSON.stringify({url,pno:r.pno,quantity:r.b_quantity}),
										contentType: "application/json; charset=utf-8",
										success: function() {

										}
									})
								}
							})
						}
					})
				})
			})
		}
	})
	.done(function() {
		alert("결제 완료");
		setTimeout(() => {
			location.href=`/${url}/orderlist`;
		}, 100);
	})
})

$.getJSON("/loaddes",{url,id}, function(res) {
	res.forEach(function(r) {
		const option = `<option value="${r.label}">${r.label}</option>`;
		$("#des_select").append(option);
	})
})
$("#des_select").on("change", function() {
	const label = $(this).val();
	if(label == "new"){
		$("#label").val("");
		$("#name").val("");
		$("#address").val("");
		$("#phone").val("");
		$("#memo").val("");
		return false;
	}
	$.getJSON("/loaddes1",{url,id,label:encodeURIComponent(label)}, function(res) {
		$("#label").val(res.label);
		$("#name").val(res.name);
		$("#address").val(res.address);
		$("#phone").val(res.phone);
		$("#memo").val(res.memo);
	})

})


$(window).on("beforeunload",function() {
	$.ajax({
		type:"put",
		url:"/orderselected",
		data:JSON.stringify({url,id,doOrder:false,b_quantity:0}),
		contentType: "application/json; charset=utf-8",
		success: function() {

		}
	})

})