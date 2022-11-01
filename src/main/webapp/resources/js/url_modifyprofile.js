/**
 * 
 */

const url = $("#url").val();
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

$.getJSON("/loaduserinfo",{url,id},function(res){
	$("#name").val(res.name);
	const fp = res.phone.slice(0,3);
	const bp = res.phone.slice(3);
	$("#f_phone").val(fp);
	$("#phone").val(bp);

	const fe = res.email.split("@");
	$("#email").val(fe[0]);
	$("#e_address").val(fe[1]);
	$("#e_select").val(fe[1]);
	$("#birth").val(res.birth);
})

function modifyProfile(target,value) {
	$.ajax({
		type:"put",
		url:"/modifyprofile",
		data:JSON.stringify({url,id,name:target,sign_date:value}),
		contentType:"application/json; charset=utf-8",
		success: function() {
			alert("수정되었습니다.");
			location.reload();
		}
	})
}

$("#pw_btn").on("click", function() {
	if($("#pw").val() != $("#pwc").val()){
		alert("비밀번호를 다시 확인하세요.");
		return false;
	}
	modifyProfile("pw",$("#pw").val());
})

$("#name_btn").on("click", function() {
	modifyProfile("name",$("#name").val());
})

$("#phone_btn").on("click", function() {
	const p = $("#f_phone").val()+$("#phone").val();
	modifyProfile("phone",p);
})

$("#e_select").on("change", function() {
	$("#e_address").val($(this).val());
})

$("#email_btn").on("click", function() {
	const e = $("#email").val()+"@"+$("#e_address").val();
	modifyProfile("email",e);
})

$("#birth_btn").on("click", function() {
	modifyProfile("birth",$("#birth").val());
})