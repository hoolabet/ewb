/**
 * 
 */
const id = $("#id").val();
$("#begin").on("click", function() {
	if(id != ""){
		location.href = "/main";
	}else{
		$(this).css("display","none");
		$("#login").css("display","flex");
		$("#login_id").focus();
	}
})

$("#login_btn").on("click",function(){
	const id = $("#login_id").val();
	const pw = $("#login_pw").val();
	$.getJSON("/ewblogin",{id,pw},function(res){
		location.href = "/main";
	})
	.fail(function() {
		alert("아이디나 비밀번호를 다시 확인하세요.");
	})
})

$("#signup_open_btn").on("click",function(){
	$("#login").css("display","none");
	$("#signup").css("display","flex");
})

$("#signup_btn").on("click", function() {
	const id = $("#signup_id").val();
	const pw = $("#signup_pw").val();
	const name = $("#signup_name").val();
	
	$.ajax({
		type:"post",
		url:"/ewbsignup",
		data:JSON.stringify({id,pw,name}),
		contentType: "application/json; charset=utf-8",
		success: function() {
			$("#login").css("display","flex");
			$("#signup").css("display","none");
		},
		error: function() {
			alert("ID 를 다시 설정하세요.")
		}
	})
})

$(document).on("keyup", function(e) {
	if($("#login").css("display") == "flex"){
		if(e.keyCode == 13){
			$("#login_btn").click()
		}
	}
})