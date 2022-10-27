/**
 * 
 */

const url = $("#url").val();
const pno = $("#product_pno").val();
const id = $("#user_id").val();
const admin = $("#admin").val();

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
		.css("margin-right", $("#save_margin").data("marginr") + "px");
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
		}else{
			$(".log").each(function(i,g) {
				const target = $(this).data("target");
				const ndnow = $(this).data("ndnow");

				$(`#li_a_${target}_${ndnow}`).attr("href",`/${url}/login`);
				$(`#li_span_${target}_${ndnow}`).html("로그인");
				$(`#li_span_${target}_${ndnow}_modi`).html("로그인");
				$(`#li_a_${target}_${ndnow}_modi`).val(`/${url}/login`);
			})
		}
	})
	$.getJSON("/loadcontent",{type:"footer",url},function(res){
		$("#footer").html(res.content);
	})
}

getHF();

$("#multi_price").html($("#quan").val()*$("#quan").data("price"));

$(".quan").on("click", function() {
	if($(this).prop("id") == "quan_down"){
		if($("#quan").val() == "1"){
			return false;
		}else{
			$("#quan").val($("#quan").val() - 1);
		}
	}else if($(this).prop("id") == "quan_up"){
		if($("#product_quantity").val() == $("#quan").val()){
			return false;
		}else{
			$("#quan").val(Number($("#quan").val()) + 1);
		}
	}
	$("#multi_price").html($("#quan").val()*$("#quan").data("price"));
})

$("#cart_btn").on("click", function() {
	if(id == ""){
		alert("로그인이 필요합니다.");
		return false;
	}
	const b_quantity = $("#quan").val();
	const cData = {id,pno,b_quantity,url}
	$.getJSON("/searchcart",cData,function(resC){
		const quantity = resC.b_quantity;
		$.getJSON("/productdetail",{url,pno},function(resP){
			const entryQ = resP.quantity;
			if(confirm("이미 장바구니에 담긴 상품입니다. 수량을 추가하시겠습니까?")){
				if(entryQ >= ( Number(b_quantity) + Number(quantity) )){
					$.ajax({
						type:"put",
						url:"/updatecart",
						data:JSON.stringify(cData),
						contentType: "application/json; charset=utf-8",
						success: function() {
							if(confirm("추가되었습니다. 장바구니로 이동하시겠습니까?")){
								location.href= `/${url}/cart`;
							}
						}
					})
				}else{
					alert("재고가 모자랍니다.");
				}
			}
		})

	})
	.fail(function() {
		$.ajax({
			type:"post",
			url:"/insertcart",
			data:JSON.stringify(cData),
			contentType: "application/json; charset=utf-8",
			success: function() {
				if(confirm("추가되었습니다. 장바구니로 이동하시겠습니까?")){
					location.href= `/${url}/cart`;
				}
			}
		})
	})
})

$("#remove").on("click", function() {
	$.ajax({
		type:"delete",
		url:"/deleteproduct",
		data:JSON.stringify({url,pno,reg_date:admin}),
		contentType: "application/json; charset=utf-8",
		success: function() {
			location.href = `/${url}/product`;
		}
	})
})