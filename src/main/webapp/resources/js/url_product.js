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

//let totalData; //총 데이터 수
//let dataPerPage; //한 페이지에 나타낼 글 수
//let pageCount = 10; //페이징에 나타낼 페이지 수
//let globalCurrentPage = 1; //현재 페이지
//let dataList = [];
//$(document).ready(function () {
////dataPerPage 선택값 가져오기
//dataPerPage = $("#dataPerPage").val();
//$.getJSON("/productlist",{url},function(res){
//dataList = res;
//totalData = res.length;
//})
//.done(function() {
////글 목록 표시 호출 (테이블 생성)
//displayData(1, dataPerPage);

////페이징 표시 호출
//paging(totalData, dataPerPage, pageCount, 1);
//})
//});

//function paging(totalData, dataPerPage, pageCount, currentPage) {
//console.log("currentPage : " + currentPage);

//totalPage = Math.ceil(totalData / dataPerPage); //총 페이지 수

//if (totalPage < pageCount) {
//pageCount = totalPage;
//}

//let pageGroup = Math.ceil(currentPage / pageCount); // 페이지 그룹
//let last = pageGroup * pageCount; //화면에 보여질 마지막 페이지 번호

//if (last > totalPage) {
//last = totalPage;
//}

//let first = last - (pageCount - 1); //화면에 보여질 첫번째 페이지 번호
//let next = last + 1;
//let prev = first - 1;

//let pageHtml = "";

//if (prev > 0) {
//pageHtml += "<li><a href='#' id='prev'> 이전 </a></li>";
//}

////페이징 번호 표시 
//for (var i = first; i <= last; i++) {
//if (currentPage == i) {
//pageHtml +=
//"<li class='on'><a href='#' id='" + i + "'>" + i + "</a></li>";
//} else {
//pageHtml += "<li><a href='#' id='" + i + "'>" + i + "</a></li>";
//}
//}

//if (last < totalPage) {
//pageHtml += "<li><a href='#' id='next'> 다음 </a></li>";
//}

//$("#pagingul").html(pageHtml);
//let displayCount = "";
//displayCount = "현재 1 - " + totalPage + " 페이지 / " + totalData + "건";
//$("#displayCount").text(displayCount);


////페이징 번호 클릭 이벤트 
//$("#pagingul li a").click(function () {
//let $id = $(this).attr("id");
//selectedPage = $(this).text();

//if ($id == "next") selectedPage = next;
//if ($id == "prev") selectedPage = prev;

////전역변수에 선택한 페이지 번호를 담는다...
//globalCurrentPage = selectedPage;
////페이징 표시 재호출
//paging(totalData, dataPerPage, pageCount, selectedPage);
////글 목록 표시 재호출
//displayData(selectedPage, dataPerPage);
//});
//}

////현재 페이지(currentPage)와 페이지당 글 개수(dataPerPage) 반영
//function displayData(currentPage, dataPerPage) {

//let chartHtml = "";

////Number로 변환하지 않으면 아래에서 +를 할 경우 스트링 결합이 되어버림.. 
//currentPage = Number(currentPage);
//dataPerPage = Number(dataPerPage);

//for (
//var i = (currentPage - 1) * dataPerPage;
//i < ((currentPage - 1) * dataPerPage + dataPerPage >  dataList.length ? dataList.length : (currentPage - 1) * dataPerPage + dataPerPage);
//i++
//) {
//chartHtml +=
//`<tr>
//<td>
//<a href="detail?pno=${dataList[i].pno}">${dataList[i].pname}</a>
//</td>
//<td>${dataList[i].price}</td>
//</tr>`;
//} //dataList는 임의의 데이터임.. 각 소스에 맞게 변수를 넣어주면 됨...
//$("#dataTableBody").html(chartHtml);
//}

//$("#dataPerPage").change(function () {
//dataPerPage = $("#dataPerPage").val();
////전역 변수에 담긴 globalCurrent 값을 이용하여 페이지 이동없이 글 표시개수 변경 
//paging(totalData, dataPerPage, pageCount, globalCurrentPage);
//displayData(globalCurrentPage, dataPerPage);
//});

$("#dataPerPage").on("change", function() {
	$("input[name='amount']").val($(this).val());
	$("input[value='찾기']").click();
})

$("#dataPerPage").val($("input[name='amount']").val());

$("#add_product").on("click", function() {
	location.href = `/${url}/productwrite`;
})
