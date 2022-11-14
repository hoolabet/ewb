/**
 * 
 */

const reg = new RegExp("(.*?)\.(exe|zip|alz)$");
const maxSize = 5242880;

function checkExtension(fileName, size){
	if(size > maxSize){
		alert("파일 용량 초과");
		return false;
	}

	if(reg.test(fileName)){
		alert("해당 확장자 파일은 업로드 할 수 없음");
		return false;
	}

	return true;
}

let url = $("#url").val();
if(url == ""){
	url = location.href.split("/")[3]
}

const pno = $("#product_pno").val();
const id = $("#user_id").val();
const userId = id;
const admin = $("#admin").val();

if($("#product_quantity").val() == 0 && !admin){
	alert("해당 상품은 품절되었습니다.")
//	location.href = `/${url}/product`;
	history.back();
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
		$("#footer").attr("style", $("#footer_style").val());
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

$("#order_btn").on("click", function() {
	if(id == ""){
		alert("로그인이 필요합니다.");
		return false;
	}
	const b_quantity = $("#quan").val();
	const cData = {id,pno,b_quantity,url}
	$.getJSON("/searchcart",cData, function(resC) {
		const quantity = resC.b_quantity;
		$.getJSON("/productdetail",{url,pno},function(resP){
			const entryQ = resP.quantity;
			if(confirm("이미 장바구니에 담긴 상품입니다. 바로 주문하시겠습니까?")){
				if(entryQ >= ( Number(b_quantity) + Number(quantity) )){
					$.ajax({
						type:"put",
						url:"/directorder1",
						data:JSON.stringify({id,pno,url,b_quantity:Number(b_quantity) + Number(quantity),doOrder:true}),
						contentType: "application/json; charset=utf-8",
						success: function() {
							location.href= `/${url}/order`;
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
			url:"/directorder2",
			data:JSON.stringify({id,pno,url,b_quantity,doOrder:true}),
			contentType:"application/json; charset=utf-8",
			success: function() {
				location.href= `/${url}/order`;
			}
		})
	})
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

$("#modify").on("click", function() {
	location.href = `/${url}/modifyproduct?pno=${pno}`;
})

$("#review_btn").on("click", function(){
	if(id==""){
		alert("로그인이 필요합니다.");
		return false;
	}
	const content = $("#review_text").val().replace("<","&lt;").replace(">","&gt;");
	if(content.replace(" ","") == ""){
		alert("내용을 적어주세요.");
		return false;
	}

	const rData = {
			url, 
			id,
			pno,
			content
	}
	$.ajax({
		type:"post",
		url:"/writereview",
		data:JSON.stringify(rData),
		contentType: "application/json; charset=utf-8",
		success: function() {
			$.ajax({
				type:"post",
				url:"/savereviewimg",
				data:JSON.stringify({fullpath:reviewPath, url,pno}),
				contentType: "application/json; charset=utf-8",
				success: function() {
					location.reload();
				}
			})
		}
	})
})

$("#review_img_btn").on("click", function() {
	$("#review_img_file").click();
})
let reviewPath = "";
$("#review_img_file").on("change", function() {
	const formData = new FormData();
	const inputFile = $(`#review_img_file`);
	const files = inputFile[0].files;

	for(j = 0; j < files.length; j++){
		console.log(files[j]);
		if(!checkExtension(files[j].fileName, files[j].size)){
			return false;
		} 
		formData.append("uploadFile", files[j]);
	}
	$.ajax({
		type: "post",
		url: "/uploadAjaxAction",
		data: formData,
		contentType: false,
		processData: false,
		dataType: "json",
		success: function(r){
			let files = "";
			r.forEach((u)=>{
				const fullPath = encodeURIComponent(`${u.path}/${u.uuid}_${u.fileName}`);
				reviewPath = fullPath;
				if(u.checkI){
					files += `<img src="/display?fileName=${fullPath}" class="imgs" style="display:block;margin:auto;width:50px"><br>`;
				}else{
					files += `<a href="/download?fileName=${fullPath}">${u.fileName}</a><br>`;
				}
			})
			$("#review_img_area").html(files);
		}
	})
})
let dataList;
let totalData; //총 데이터 수
let dataPerPage = 10; //한 페이지에 나타낼 글 수
let pageCount = 10; //페이징에 나타낼 페이지 수
let globalCurrentPage = 1; //현재 페이지
$.getJSON("/loadreview",{pno,url},function(res){
	dataList = res;
	totalData = res.length;
	console.log(dataList)
})
.done(function() {

	displayData(1, dataPerPage);
	paging(totalData, 10, pageCount, 1);
})

function paging(totalData, dataPerPage, pageCount, currentPage) {
	console.log("currentPage : " + currentPage);

	totalPage = Math.ceil(totalData / dataPerPage); 

	if (totalPage < pageCount) {
		pageCount = totalPage;
	}

	let pageGroup = Math.ceil(currentPage / pageCount);
	let last = pageGroup * pageCount;

	if (last > totalPage) {
		last = totalPage;
	}

	let first = last - (pageCount - 1);
	let next = last + 1;
	let prev = first - 1;

	let pageHtml = "";

	if (prev > 0) {
		pageHtml += "<li><a href='#' id='prev'> 이전 </a></li>";
	}

	for (var i = first; i <= last; i++) {
		if (currentPage == i) {
			pageHtml +=
				"<li class='on'><a href='#' id='" + i + "'>" + i + "</a></li>";
		} else {
			pageHtml += "<li><a href='#' id='" + i + "'>" + i + "</a></li>";
		}
	}

	if (last < totalPage) {
		pageHtml += "<li><a href='#' id='next'> 다음 </a></li>";
	}

	$("#pagingul").html(pageHtml);
	let displayCount = "";
	displayCount = "현재 1 - " + totalPage + " 페이지 / " + totalData + "건";
	$("#displayCount").text(displayCount);


	$("#pagingul li a").click(function () {
		console.log($("#review_div").offset().top)
		$('html').animate({scrollTop : $("#review_div").offset().top-100}, 400);
		let $id = $(this).attr("id");
		selectedPage = $(this).text();

		if ($id == "next") selectedPage = next;
		if ($id == "prev") selectedPage = prev;

		globalCurrentPage = selectedPage;
		paging(totalData, dataPerPage, pageCount, selectedPage);
		displayData(selectedPage, dataPerPage);
	});
}

function displayData(currentPage, dataPerPage) {

	let chartHtml = "";


	currentPage = Number(currentPage);
	dataPerPage = Number(dataPerPage);

	for (
			var i = (currentPage - 1) * dataPerPage;
			i < ((currentPage - 1) * dataPerPage + dataPerPage >  dataList.length ? dataList.length : (currentPage - 1) * dataPerPage + dataPerPage);
			i++
	) {
		chartHtml += `
			<tr>
			<td>
			${dataList[i].id}
			</td>
			`+(dataList[i].tvo.fullpath != "" ?
					`
					<td>
					<img src="/display?fileName=${dataList[i].tvo.fullpath}" width="200px">
					</td>
					`: `<td></td>`
			)
			+
			`
			<td>
			${dataList[i].content}
			</td>
			<td>
			${dataList[i].review_date}
			</td>
			<td>
			<div class="remove_review" data-id="${dataList[i].id}" data-rno="${dataList[i].rno}" style="cursor:pointer">❌</div>
			</td>
			</tr>
			`;
	}
	$("#review_table").html(chartHtml);
	$(".remove_review").on("click", function() {
		const thisId = $(this).data("id");
		if(thisId != id){
			alert("본인이 작성한 리뷰만 삭제가능합니다.");
			return false;
		}
		const rno = $(this).data("rno");
		$.ajax({
			type:"delete",
			url:"/deletereview",
			data:JSON.stringify({rno,url}),
			contentType:"application/json; charset=utf-8",
			success:function() {
				location.reload();
			}
		})
	})
}
