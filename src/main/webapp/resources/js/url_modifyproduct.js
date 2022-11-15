
$("#cp").minicolors();
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
const userId = $("#user_id").val();
const pno = $("#pno").val();


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



$("#modi_btn").on("click", function(){
	const pname = $("#pname").val();
	const price = $("#price").val();
	const quantity = $("#quantity").val();
	const content = $("#content").html();
	if(isNaN(price) || Number(price) <= 0){
		alert("가격은 0 이상의 숫자만 입력가능합니다.");
		return false;
	}
	if(isNaN(quantity) || Number(quantity) <= 0){
		alert("수량은 0 이상의 숫자만 입력가능합니다.");
		return false;
	}
	const pData = {
			url, 
			pname,
			price,
			quantity,
			content,
			pno
	}
	$.ajax({
		type:"put",
		url:"/modifyproduct",
		data:JSON.stringify(pData),
		contentType: "application/json; charset=utf-8",
		success: function() {
			$.getJSON("/searchthumbnail",{url,pno},function(){
				$.ajax({
					type:"put",
					url:"/modifythumbnail",
					data:JSON.stringify({fullpath:thumbPath, url,pno}),
					contentType: "application/json; charset=utf-8",
					success: function() {
						location.href = `/${url}/product`;
					}
				})
			})
			.fail(function() {
				$.ajax({
					type:"post",
					url:"/savethumbnail2",
					data:JSON.stringify({fullpath:thumbPath, url,pno}),
					contentType: "application/json; charset=utf-8",
					success: function() {
						location.href = `/${url}/product`;
					}
				})
			})
		}
	})
})

$("#insert_btn").on("click", function() {
	$("#insert_img").click();
})

$("#insert_img").on("change",function(){
	const formData = new FormData();
	const inputFile = $(`#insert_img`);
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
				if(u.checkI){
					const width = prompt(u.fileName+" 가로 길이 (px)");
					if(isNaN(width) || width < 0){
						alert("0 보다 큰 숫자만 입력하세요.");
						return false;
					}else if(width == 0){
						files += `<img src="/display?fileName=${fullPath}" class="imgs" style="display:block;margin:auto;"><br>`;
					}else{
						files += `<img src="/display?fileName=${fullPath}" class="imgs" style="display:block;margin:auto;width:${width}px;"><br>`;
					}
				}else{
					files += `<a href="/download?fileName=${fullPath}">${u.fileName}</a><br>`;
				}
			})
			$("#content").append(files);
		}
	})
})

$("#thumb_btn").on("click", function() {
	$("#thumb_file").click();
})
let thumbPath = $("#thumbpath").val();
$("#thumb_file").on("change", function() {
	const formData = new FormData();
	const inputFile = $(`#thumb_file`);
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
				thumbPath = fullPath;
				if(u.checkI){
					files += `<img src="/display?fileName=${fullPath}" class="imgs" style="display:block;margin:auto;"><br>`;
				}else{
					files += `<a href="/download?fileName=${fullPath}">${u.fileName}</a><br>`;
				}
			})
			$("#thumbnail").html(files);
		}
	})
})

$("#able_box").on("change", function(){
	const chk = $(this).prop("checked");
	if(chk){
		$("#content").prop("contenteditable", true);
	}else{
		$("#content").prop("contenteditable", false);
	}
})
let newRange = "";
$("#content").on("keyup", function (e) {
	// console.log($(this).html());
	newRange = window.getSelection().getRangeAt(0);
})

$("#content").on("click", function () {
	// console.log($(this).html());
	newRange = window.getSelection().getRangeAt(0);
})

let dateVal = "";
const atts = {
		b: false,
		i: false,
		u: false
}
$(".btns").on("click", function (e) {
	const att = $(this).data("att");
	const date = Date.now();
	dateVal = date;
	if (newRange == "") {
		$("#content").focus();
	}
	if (att == "b" || att == "i" || att == "u") {
		const selection = window.getSelection();
		const dispan = document.createElement("span");
		const span = document.createElement("span");
		if(selectAO == selectFO){
			span.innerHTML = `<${att}><span class="${att}">&nbsp;</span></${att}>`;
		}else{
			span.innerHTML = `<${att}><span class="${att}">${selected}</span></${att}>`;
		}
		span.id = `${date}`;
		if (att == "b") {
			dispan.innerHTML = `<span style="font-weight:400">&nbsp;</span>`;
		}else if(att == "u"){
			dispan.innerHTML = `<span style="text-decoration:none">&nbsp;</span>`;
		}else {
			dispan.innerHTML = `<span style="font-style:normal">&nbsp;</span>`;
		}
		dispan.id = `${date}`;
		selection.removeAllRanges();
		selection.addRange(newRange);
		newRange.deleteContents();

		if(selectAO == selectFO){
			if (atts[att]) {
				$(`div[data-att="${att}"]`).css("background-color", "white").css("color", "black");
				newRange.insertNode(dispan);
				atts[att] = false;
			} else {
				$(`div[data-att="${att}"]`).css("background-color", "black").css("color", "white");
				newRange.insertNode(span);
				atts[att] = true;
			}
		}else{
			newRange.insertNode(span);
		}
		window.getSelection().collapse($(`#${date}`)[0], 1);
	}else if(att == "a"){
		$("#link_div").css("display","flex").css("top",e.clientY).css("left",e.clientX);
		$("#href").val("");
		$("#href_text").val("");
	}
})
$(document).on("click", function(e){
	if(e.target.id != "link_div" && $(e.target).data("att") != "a" && e.target.className != "links"){
		$("#link_div").css("display","none");
	}
})

$("#link_btn").on("click", function(){
	if (newRange == "") {
		$("#content").focus();
	}
	const href = $("#href").val();
	const href_text = $("#href_text").val();
	if(href.replace(" ","") == "" || href_text.replace(" ","") == ""){
		alert("주소를 입력하세요.");
		return false;
	}
	const $a = document.createElement("a");
	$a.href = href;
	$a.innerHTML = href_text;
	$a.target = "_blank";
	const selection = window.getSelection();
	selection.removeAllRanges();
	selection.addRange(newRange);
	newRange.deleteContents();
	newRange.insertNode($a);
	$("#link_div").css("display","none");
})

$("#font_size").val("16");
$("#font_size_btn").on("click", function (e) {
	if($("#cp_div").css("display") == "flex"){
		$("#cp_div").css("display", "none");
	}
    if($("#font_size").css("display") == "none"){
    	$("#font_size").css("display", "flex").css("top", "40px").css("left", "510px");
    }else{
    	$("#font_size").css("display", "none");
    }
})
$("#font_size").on("change", function () {
    if (newRange == "") {
        $("#edita").focus();
    }
    const size = $(this).val();
    const date = Date.now();
    const span = document.createElement("span");
    span.id = date;
    if (selectAO == selectFO) {
        span.innerHTML = `<span class="font_size" style="font-size:${size}px">&nbsp;</span>`;
    } else {
        span.innerHTML = `<span class="font_size" style="font-size:${size}px">${selected}</span>`;
    }
    const selection = window.getSelection();
    selection.removeAllRanges();
    selection.addRange(newRange);
    newRange.deleteContents();
    newRange.insertNode(span);
    $(this).toggle();
})

$("#font_color_btn").on("click", function (e) {
	if($("#font_size").css("display") == "flex"){
		$("#font_size").css("display", "none");
	}
    if($("#cp_div").css("display") == "none"){
    	$("#cp_div").css("display", "flex").css("top", "40px").css("left", "550px");
    }else{
    	$("#cp_div").css("display", "none");
    }
    
})

$("#font_color_choice").on("click", function () {
    $("#cp_div").toggle();
    const color = $("#cp").val();
    const date = Date.now();
    const span = document.createElement("span");
    span.id = date;
    if (selectAO == selectFO) {
        span.innerHTML = `<span class="font_color" style="color:${color}">&nbsp;</span>`;
    } else {
        span.innerHTML = `<span class="font_color" style="color:${color}">${selected}</span>`;
    }
    const selection = window.getSelection();
    selection.removeAllRanges();
    selection.addRange(newRange);
    newRange.deleteContents();
    newRange.insertNode(span);
})

$(".sort").on("click", function(){
	const sort = $(this).data("sort");
	$("#content").css("text-align",sort);
	$(".sort").css("background-color","white").css("color","black");
	$(this).css("background-color","black").css("color","white");
})

let selectAO = 0;
let selectFO = 0;
let selected = "";
$(document).on("selectstart", function(e){
	selectAO = window.getSelection().anchorOffset;
	selectFO = window.getSelection().focusOffset;
	selected = window.getSelection().toString();
})