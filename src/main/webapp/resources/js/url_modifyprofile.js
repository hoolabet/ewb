/**
 * 
 */

let url = $("#url").val();
if(url == ""){
	url = location.href.split("/")[3]
}
const userId = $("#user_id").val();
if(userId == ""){
	alert("로그인이 필요합니다.");
	location.href=`/${url}/login`;
}
const admin = $("#admin").val();

$.getJSON("/loadreg",{url}, function(res) {
	$("#reg_info").html(res.content);
	$("#pw").attr("data-str",$("#reg_pw").data("str"))
	.attr("data-num",$("#reg_pw").data("num"))
	.attr("data-spe",$("#reg_pw").data("spe"))
	.attr("data-lenMax",$("#reg_pw").data("lenMax"))
	.attr("data-lenMin",$("#reg_pw").data("lenMin"))
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

$.getJSON("/loaduserinfo",{url,id:userId},function(res){
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
		data:JSON.stringify({url,id:userId,name:target,sign_date:value}),
		contentType:"application/json; charset=utf-8",
		success: function() {
			alert("수정되었습니다.");
			location.reload();
		}
	})
}
let pwCheck = false;
$("#pw_btn").on("click", function() {
	if(!pwCheck){
		alert("비밀번호를 다시 확인하세요.");
		return false;
	}
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
	if($(this).val() == "dir"){
		$("#e_address").val("");
		$("#e_address").removeAttr("readonly");
	}else{
		$("#e_address").val($(this).val());
		$("#e_address").attr("readonly",true);
	}
})

$("#email_btn").on("click", function() {
	const e = $("#email").val()+"@"+$("#e_address").val();
	modifyProfile("email",e);
})

$("#birth_btn").on("click", function() {
	modifyProfile("birth",$("#birth").val());
})

const reg = {
	str: "a-zA-Z가-힣",
	num: "0-9",
	spe: `!@#$%^*()\\-_=+\\\\\\|\\[\\]{};:\\'",.<>\\/?`
};

function createRegExp(e) {
	let str = $(e.target).attr("data-str") == "on" ? `(?=.*[${reg.str}])` : "";
	let num = $(e.target).attr("data-num") == "on" ? `(?=.*[${reg.num}])` : "";
	let spe = $(e.target).attr("data-spe") == "on" ? `(?=.*[${reg.spe}])` : "";
	let lenMin = $(e.target).attr("data-lenmin") == null ? "0" : $(e.target).attr("data-lenmin");
	let lenMax = $(e.target).attr("data-lenmax") == null ? "100" : $(e.target).attr("data-lenmax");

	if (e.target.id == "pwc") {
		str = $("#pw").attr("data-str") == "on" ? `(?=.*[${reg.str}])` : "";
		num = $("#pw").attr("data-num") == "on" ? `(?=.*[${reg.num}])` : "";
		spe = $("#pw").attr("data-spe") == "on" ? `(?=.*[${reg.spe}])` : "";
		lenMin = $("#pw").attr("data-lenmin") == null ? "0" : $("#pw").attr("data-lenmin");
		lenMax = $("#pw").attr("data-lenmax") == null ? "100" : $("#pw").attr("data-lenmax");
	}

	const regExp = new RegExp(`^${str}${num}${spe}.{${lenMin},${lenMax}}$`);
	console.log(regExp);
	console.log($(e.target).val());
	console.log(regExp.test($(e.target).val()));
	if (regExp.test($(e.target).val())) {
		if (e.target.id == "pw" || e.target.id == "pwc") {
			if ($("#pw").val() == $("#pwc").val()) {
				pwCheck = true;
				$(".pw").html("비밀번호 확인").css("color", "green");
			} else {
				$(".pw").html("비밀번호 확인바랍니다.").css("color", "red");
			}
		}
	} else {
		if (e.target.id == "pw" || e.target.id == "pwc") {
			if ($("#pw").val() == $("#pwc").val()) {
				$(".pw").html("사용불가").css("color", "red");
			} else {
				$(".pw").html("비밀번호 확인바랍니다.").css("color", "red");
			}
		}
	}
}

$("#pw").on("keyup", function(e) {
	createRegExp(e);
})
$("#pwc").on("keyup", function(e) {
	createRegExp(e);
})

$("#des_add_btn").on("click", function() {
	$.getJSON("/loaddes1",{url,id:userId,label:$("#des_label").val()}, function() {
		alert("이미 존재하는 배송지명 입니다.");
		return false;
	})
	.fail(function() {

		const dData = {
				id:userId,url,
				label:$("#des_label").val(),
				name: $("#des_name").val(),
				address: $("#des_address").val(),
				phone: $("#des_phone").val(),
				memo: $("#des_memo").val()
		}

		$.ajax({
			type:"post",
			url:"/insertdes",
			data:JSON.stringify(dData),
			contentType:"application/json; charset=utf-8",
			success:function(){
				location.reload();
			}
		})
	})
})

$("#des_modify_btn").on("click", function() {
	const label = $("#des_select").val();
	if(label == "new"){
		alert("수정할 수 없습니다.");
		return false;
	}
	const dData = {
			id:userId,url,
			label:$("#des_label").val(),
			name: $("#des_name").val(),
			address: $("#des_address").val(),
			phone: $("#des_phone").val(),
			memo: $("#des_memo").val()
	}
	$.ajax({
		type:"put",
		url:"/updatedes",
		data:JSON.stringify(dData),
		contentType:"application/json; charset=utf-8",
		success:function(){
			location.reload();
		}
	})
})

$("#des_remove_btn").on("click", function() {
	const label = $("#des_select").val();
	if(label == "new"){
		alert("삭제할 수 없습니다.");
		return false;
	}
	const dData = {
			id,url,
			label:$("#des_label").val()
	}
	$.ajax({
		type:"delete",
		url:"/deletedes",
		data:JSON.stringify(dData),
		contentType:"application/json; charset=utf-8",
		success:function(){
			location.reload();
		}
	})
})

$.getJSON("/loaddes",{url,id:userId}, function(res) {
	res.forEach(function(r) {
		const option = `<option value="${r.label}">${r.label}</option>`;
		$("#des_select").append(option);
	})
})

$("#des_select").on("change", function() {
	const label = $(this).val();
	if(label == "new"){
		$("#des_label").val("");
		$("#des_name").val("");
		$("#des_address").val("");
		$("#des_phone").val("");
		$("#des_memo").val("");
		return false;
	}
	$.getJSON("/loaddes1",{url,id:userId,label}, function(res) {
		$("#des_label").val(res.label);
		$("#des_name").val(res.name);
		$("#des_address").val(res.address);
		$("#des_phone").val(res.phone);
		$("#des_memo").val(res.memo);
	})

})