
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
					const height = prompt(u.fileName+" 세로 길이 (px)");
					if(isNaN(width) || isNaN(height) || width < 0 || height < 0){
						alert("0 보다 큰 숫자만 입력하세요.");
						return false;
					}else if(width == 0 && height == 0){
						files += `<img src="/display?fileName=${fullPath}" class="imgs" style="display:block;margin:auto;"><br>`;
					}else{
						files += `<img src="/display?fileName=${fullPath}" class="imgs" style="display:block;margin:auto;width:${width}px;height:${height}px"><br>`;
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
let thumbPath = "";
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