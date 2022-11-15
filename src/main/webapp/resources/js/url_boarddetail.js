/**
 * 
 */
const bno = $("#bno").val();
let url = $("#url").val();
if(url == ""){
	url = location.href.split("/")[3]
}

const userId = $("#user_id").val();
const bId = $("#b_id").val();
const status = $("#status").val();

$.getJSON("/loaduserinfo",{url,id:userId}, function(res) {
	if(res.status != bno){
		$.ajax({
			type:"put",
			url:"/countboard",
			data:JSON.stringify({bno,url}),
			contentType:"application/json; charset=utf-8",
			success: function() {
				$.ajax({
					type:"put",
					url:"/updatestatus",
					data:JSON.stringify({url,status:bno,id:userId}),
					contentType:"application/json; charset=utf-8",
					success: function() {
						
					}
				})
			}
		})
	}
})

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
		}
	})
	$.getJSON("/loadcontent",{type:"footer",url},function(res){
		$("#footer").html(res.content);
		$("#footer").attr("style", $("#footer_style").val());
	})
}

getHF();

$("#reply_btn").on("click", function() {
	if(userId == ""){
		alert("로그인이 필요합니다.");
		return false;
	}
	const content = $("#reply_content").val();
	const rData = {
			url,
			id:userId,
			bno,
			content
	}
	
	$.ajax({
		type:"post",
		url:"/writereply",
		data:JSON.stringify(rData),
		contentType:"application/json; charset=utf-8",
		success:function(){
			location.reload();
		}
	})
})



$("#modi_btn").on("click", function() {
	if(bId != userId){
		alert("권한이 없습니다.");
		return false;
	}
	location.href = `/${url}/modifyboard?bno=${bno}`;
})

$("#remo_btn").on("click", function() {
	if(bId != userId){
		alert("권한이 없습니다.");
		return false;
	}
	
	if(confirm("게시물을 삭제하시겠습니까?")){
		$.ajax({
			type:"delete",
			url:"/deleteboard",
			data:JSON.stringify({url,bno}),
			contentType:"application/json; charset=utf-8",
			success: function() {
				alert("삭제되었습니다.");
				location.href = `/${url}/board`;
			}
		})
	}
	
})

let dataList;
let totalData; //총 데이터 수
let dataPerPage = 20; //한 페이지에 나타낼 글 수
let pageCount = 10; //페이징에 나타낼 페이지 수
let globalCurrentPage = 1; //현재 페이지

$.getJSON("/loadreply",{url,bno},function(res){
	dataList = res;
	totalData = res.length;
	console.log(dataList)
	
	res.forEach(function(r) {
		const tr = `
			<tr>
				<td>${r.id}</td>
				<td>${r.content}</td>
				<td>${r.reply_date}</td>
				<td><span class="remove_reply" data-rno="${r.rno}" data-id="${r.id}">❌</span></td>
			</tr>
		`;
		$("#reply_table").append(tr);
	})
})
.done(function() {
	displayData(1, dataPerPage);
	paging(totalData, dataPerPage, pageCount, 1);
	
	$(".remove_reply").on("click", function() {
		const id = $(this).data("id");
		const rno = $(this).data("rno");
		if(userId != id){
			alert("권한이 없습니다.");
			return false;
		}
		const rData = {
				url,
				rno
		}
		$.ajax({
			type:"delete",
			url:"/deletereply",
			data:JSON.stringify(rData),
			contentType:"application/json; charset=utf-8",
			success: function() {
				location.reload();
			}
		})
	})
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
		$('html').animate({scrollTop : $("#reply").offset().top-100}, 400);
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
			<td>
			${dataList[i].content}
			</td>
			<td>
			${dataList[i].reply_date}
			</td>
			<td>
			<div class="remove_reply" data-id="${dataList[i].id}" data-rno="${dataList[i].rno}" style="cursor:pointer">❌</div>
			</td>
			</tr>
			`;
	}
	$("#reply_table").html(chartHtml);
	$(".remove_reply").on("click", function() {
		const id = $(this).data("id");
		const rno = $(this).data("rno");
		if(userId != id){
			alert("권한이 없습니다.");
			return false;
		}
		const rData = {
				url,
				rno
		}
		$.ajax({
			type:"delete",
			url:"/deletereply",
			data:JSON.stringify(rData),
			contentType:"application/json; charset=utf-8",
			success: function() {
				location.reload();
			}
		})
	})
}
let like = false;
$.getJSON("/checklike",{url,bno,id:userId}, function(res) {
	like = true;
	$("#like_btn").css("background-color", "blue").css("color", "white");
})

$("#like_btn").on("click", function() {
	let type_ = "post";
	let url_ = "/llike";
	let bname = "up";
	if(like){
		type_ = "delete";
		url_ = "/unlike";
		bname = "down";
	}
	
	const lData = {
			url,bno,id:userId,bname
	}
	
	$.ajax({
		type: type_,
		url: url_,
		data:JSON.stringify(lData),
		contentType:"application/json; charset=utf-8",
		success: function() {
			$.ajax({
				type:"put",
				url:"/countlike",
				data:JSON.stringify(lData),
				contentType:"application/json; charset=utf-8",
				success: function() {
					location.reload();
				}
			})
		}
	})
})
