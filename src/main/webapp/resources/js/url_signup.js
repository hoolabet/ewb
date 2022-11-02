/**
 * 
 */
let url = $("#url").val();
if(url == ""){
	url = location.href.split("/")[3]
}

$('.cp').minicolors();
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

	})
	
	$.getJSON("/loadcontent",{type:"footer",url},function(res){
		$("#footer").html(res.content);
	})
}

function pageLoad(){
	$.getJSON("/loadcontent",{type:"signup_page",url},function(res){
		$("#signup_content").html(res.content);
		$("#signup_content").attr("style", $("#signup_style").val());
		$(".check_span").html("");
		$(".modi_input").val("");
		$(document).off("contextmenu").on("contextmenu", function (e) {
			e.preventDefault();
			contextmenuFunc(e);
		})
		loadFunc();
	})
	.fail(function() {
		$.ajax({
			type:"post",
			url:"/savecontent",
			data:JSON.stringify({url,content:$("#signup_content").html(),type:"signup_page"}),
			contentType: "application/json; charset=utf-8",
			success:function(){

			}
		})
	})
}

getHF();
pageLoad();

$("#load").on("click", function () {
	getHF();
	pageLoad();
	alert("불러오기를 실행했습니다.");
	$(document).off("contextmenu").on("contextmenu", function (e) {
		e.preventDefault();
		contextmenuFunc(e);
	})
})

$("#save").on("click", function() {
	$("#signup_style").val($("#signup_content").attr("style"));
	$.ajax({
		type:"put",
		url:"/modifycontent",
		data:JSON.stringify({url,content:$("#signup_content").html(),type:"signup_page"}),
		contentType: "application/json; charset=utf-8",
		success: function() {
			alert("저장되었습니다.");
		}
	})

})

$(document).off("contextmenu").on("contextmenu", function (e) {
	e.preventDefault();
	contextmenuFunc(e);
})

function contextmenuFunc(e) {
	if (e.target.className.includes("modi_input")) {
		$("#modify_input").data("id", e.target.id);
		$("#modify_input").css("top", e.pageY).css("left", e.pageX).css("display", "flex");
		$(".reg").css("top", "-22px").css("left", "100px");
		if ($(e.target).attr("data-str") == "on") {
			$("#reg_str").css("background-color", "#52afe8");
		} else {
			$("#reg_str").css("background-color", "#ebebeb");
		}
		if ($(e.target).attr("data-num") == "on") {
			$("#reg_num").css("background-color", "#52afe8");
		} else {
			$("#reg_num").css("background-color", "#ebebeb");
		}
		if ($(e.target).attr("data-spe") == "on") {
			$("#reg_spe").css("background-color", "#52afe8");
		} else {
			$("#reg_spe").css("background-color", "#ebebeb");
		}
	}
	if (e.target.id == "sign_div" || e.target.id == "signup_content") {
        $("#controller").data("target", e.target.id);
        $("#target_name").html(e.target.id);
        $("#controller").css("display", "none").css("display", "flex");
        menuClose();
        $("#controller").css("display", "flex");
        if(e.target.id == "signup_content"){
            $("#color").css("display","none");
            $("#font_size").css("display","none");
        }else{
            $("#color").css("display","flex");
            $("#font_size").css("display","flex");
        }
    }
}

$("#sign_img").on("click", function (e) {
	$("#sign_element").css("left", e.pageX).css("top", e.pageY).toggle();
	$(".tr_remove").each(function(i,t) {
		$(`#${$(t).data("id")}`).css("display","none");
	})
})

$(document).on("click", function (e) {
	if (e.target.id != "sign_img") {
		$("#sign_element").css("display", "none");
	}
	if (e.target.className != "modi_span") {
		$("#modify_span").css("display", "none");
	}
	$("#modify_input").css("display", "none");
})

$(".sign_element").on("click", function () {
	const id = $(this).prop("id");
	let tr = "";
	switch (id) {
	case "sign_name":
		tr = `
			<tr>
			<td><span class="modi_span" id="name_span">NAME</span><span class="tr_remove" data-id="${id}">❌</span></td>
			<td><input class="modi_input input" type="text" name="name" id="name" required data-able='f'><span class='name check_span'></span></td>
			</tr>
			`;

		break;
	case "sign_mail":
		tr = `
			<tr>
			<td><span class="modi_span" id="mail_span">E-MAIL</span><span class="tr_remove" data-id="${id}">❌</span></td>
			<td>
			<input class="modi_input input" type="text" id="fe" required>@
			<input class="modi_input input" type="text" id="dir_address" required readonly value="naver.com">
			<select id="address_select" class="input">
			<option value="naver.com">naver.com</option>
			<option value="google.com">google.com</option>
			<option value="dir">직접입력</option>
			</select>
			<span class='fe check_span'></span>
			<input type="hidden" name="mail" id="mail" data-able='f'>
			</td>
			</tr>
			`;

		break;
	case "sign_phone":
		tr = `
			<tr>
			<td><span class="modi_span" id="phone_span">PHONE</span><span class="tr_remove" data-id="${id}">❌</span></td>
			<td>
			<select id="fp" class="input">
			<option value="010">010</option>
			<option value="011">011</option>
			<option value="016">016</option>
			<option value="017">017</option>
			<option value="018">018</option>
			<option value="019">019</option>
			</select>
			<input class="modi_input input" type="text" id="bp" required data-lenmin="7" data-lenmax="8" data-num="on">
			<span class='bp check_span'></span>
			<input type="hidden" name="phone" id="phone" data-able='f'>
			</td>
			</tr>
			`;

		break;
	case "sign_birth":
		tr = `
			<tr>
			<td><span class="modi_span" id="birth_span">BIRTH</span><span class="tr_remove" data-id="${id}">❌</span></td>
			<td><input class="modi_input input" type="date" name="birth" id="birth" value="2000-01-01"></td>
			</tr>
			`;

		break;
	default:
		break;
	}
	$("#before").before(tr);
	$(`#${id}`).css("display","none");
	$("#address_select").off("change").on("change", function () {
		if ($(this).val() == "dir") {
			console.log("hi")
			$("#dir_address").removeAttr("readonly");
			$("#dir_address").val("");
		} else {
			console.log("bye")
			$("#dir_address").val($(this).val());
			$("#dir_address").attr("readonly", true);
		}
		$("#mail").val($("#fe").val() + "@" + $("#dir_address").val());
	})
	$("#dir_address").on("change", function () {
		$("#mail").val($("#fe").val() + "@" + $("#dir_address").val());
	})
	$("#fe").on("blur", function () {
		$("#mail").val($("#fe").val() + "@" + $("#dir_address").val());
	})
	$("#fp").on("change", function () {
		$("#phone").val($("#fp").val() + "" + $("#bp").val());
	})
	$("#bp").on("blur", function () {
		$("#phone").val($("#fp").val() + "" + $("#bp").val());
	})
	$(".tr_remove").off("click").on("click", function () {
		$(this).parent().parent().remove();
		$(`#${$(this).data("id")}`).css("display","block");
	})
//	$(".modi_span").off("click").on("click", function (e) {
//		$("#modify_span").css("left", e.pageX).css("top", e.pageY).toggle();
//		$("#ss_modify").data("id", $(this).prop("id"));
//	})
	$(".modi_span").on("dblclick", function () {
    if (!$(this).attr("contenteditable")) {
        $(this).attr("contenteditable", true);
    } else {
        $(this).attr("contenteditable", false);
    }
})
	$(".modi_input").off("keyup").on("keyup", function (e) {
		createRegExp(e);
	})
})

//$(".modi_span").on("click", function (e) {
//	$("#modify_span").css("left", e.pageX).css("top", e.pageY).toggle();
//	console.log($(this).prop("id"));
//	$("#ss_modify").data("id", $(this).prop("id"));
//})

//$("#ss_modify").on("click", function () {
//	const prom = prompt("변경할 내용을 적으세요.");
//	if(prom.replace(" ","") == ""){
//		alert("공백은 입력할 수 없습니다.");
//		return false;
//	}
//	$(`#${$(this).data("id")}`).html(prom);
//})
const reg = {
	str: "a-zA-Z가-힣",
	num: "0-9",
	spe: `!@#$%^*()\\-_=+\\\\\\|\\[\\]{};:\\'",.<>\\/?`
};
$(".reg").on("click", function () {
	const target = $("#modify_input").data("id");
	const regWhat = $(this).prop("id");

	if (regWhat == "reg_str") {
		if ($(`#${target}`).attr("data-str") == "on") {
			$(`#${target}`).removeAttr("data-str");
		} else {
			$(`#${target}`).attr("data-str", "on");
		}
	} else if (regWhat == "reg_num") {
		if ($(`#${target}`).attr("data-num") == "on") {
			$(`#${target}`).removeAttr("data-num");
		} else {
			$(`#${target}`).attr("data-num", "on");
		}
	} else if (regWhat == "reg_spe") {
		if ($(`#${target}`).attr("data-spe") == "on") {
			$(`#${target}`).removeAttr("data-spe");
		} else {
			$(`#${target}`).attr("data-spe", "on");
		}
	} else if (regWhat == "reg_free") {
		$(`#${target}`).removeAttr("data-str");
		$(`#${target}`).removeAttr("data-num");
		$(`#${target}`).removeAttr("data-spe");
	}

})

$("#len").on("click", function () {
	const target = $("#modify_input").data("id");
	const min = prompt("최소 글자 수를 적어주세요.");
	if (isNaN(min) || Number(min) <= 0 || Number(min) != Math.ceil(min)) {
		alert("0 보다 큰 정수만 입력가능합니다.");
		return false;
	}
	const max = prompt("최대 글자 수를 적어주세요.");
	if (isNaN(max) || Number(max) < Number(min) || Number(max) != Math.ceil(max)) {
		alert("최소 글자 수 보다 큰 정수만 입력가능합니다.");
		return false;
	}
	$(`#${target}`).attr("data-lenmin", min);
	$(`#${target}`).attr("data-lenmax", max);
})

$(".modi_input").off("keyup").on("keyup", function (e) {
	createRegExp(e);
})

$("#sign_submit").on("click", function (e) {
	e.preventDefault();
	console.log(url)
	let unable = false;
	$("input[data-able]").each(function (i, a) {
		if ($(a).attr("data-able") == "f") {
			alert("필수 입력정보를 입력하세요.");
			unable = true;
			return false;
		}
	})

	if (unable) {
		return false;
	}
	const id = $("#id").val();
	const pw = $("#pw").val();
	const name = $("#name").val();
	const email = $("#mail").val();
	const phone = $("#phone").val();
	const birth = $("#birth").val();

	const sData = {
			id, pw, name, email, phone, birth, url
	};
	console.log(sData)
	$.ajax({
		type: "post",
		url: "/signup",
		data: JSON.stringify(sData),
		contentType: "application/json; charset=utf-8",
		success: function () {
			alert("가입되었습니다.");
			location.href = `/${url}/home`;
		},
		error: function () {
			alert("가입 실패");
		}
	})
})

$("#dup_check").on("click", function () {
	if($(".id").css("color") != "rgb(0, 0, 255)"){
		alert("사용가능한 아이디로 작성하세요.");
		return false;
	}
	$.getJSON("/dupcheck", { id: $("#id").val() }, function () {
		alert("중복된 id 입니다.");
		$("#id").attr("data-able", "f");
		$(".id").html("중복된 id 입니다.").css("color", "red");
	})
	.fail(function () {
		alert("사용가능한 id 입니다.");
		$("#id").attr("data-able", "t");
		$(".id").html("사용가능한 id 입니다.").css("color", "green");
	})
})

function createRegExp(e) {
	let str = $(e.target).attr("data-str") == "on" ? `(?=.*[${reg.str}])` : "";
	let num = $(e.target).attr("data-num") == "on" ? `(?=.*[${reg.num}])` : "";
	let spe = $(e.target).attr("data-spe") == "on" ? `(?=.*[${reg.spe}])` : "";
	let lenMin = $(e.target).attr("data-lenmin") == null ? "0" : $(e.target).attr("data-lenmin");
	let lenMax = $(e.target).attr("data-lenmax") == null ? "100" : $(e.target).attr("data-lenmax");

	if (e.target.id == "pw_check") {
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
		if (e.target.id == "fe") {
			$(`.${$(e.target).prop("id")}`).html("사용가능").css("color", "green");
			$("#mail").attr("data-able", "t");
		} else if (e.target.id == "bp") {
			$(`.${$(e.target).prop("id")}`).html("사용가능").css("color", "green");
			$("#phone").attr("data-able", "t");
		} else if (e.target.id == "pw" || e.target.id == "pw_check") {
			if ($("#pw").val() == $("#pw_check").val()) {
				$(".pw").html("비밀번호 확인").css("color", "green");
				$("#pw").attr("data-able", "t");
			} else {
				$(".pw").html("비밀번호 확인바랍니다.").css("color", "red");
				$("#pw").attr("data-able", "f");
			}
		} else if (e.target.id == "id") {
			$(`.${$(e.target).prop("id")}`).html("중복 확인 해주세요.").css("color", "blue");
		} else {
			$(`.${$(e.target).prop("id")}`).html("사용가능").css("color", "green");
			$(e.target).attr("data-able", "t");
		}
	} else {
		if (e.target.id == "fe") {
			$(`.${$(e.target).prop("id")}`).html("사용불가").css("color", "red");
			$("#mail").attr("data-able", "f");
		} else if (e.target.id == "bp") {
			$(`.${$(e.target).prop("id")}`).html("사용불가").css("color", "red");
			$("#phone").attr("data-able", "f");
		} else if (e.target.id == "pw" || e.target.id == "pw_check") {
			if ($("#pw").val() == $("#pw_check").val()) {
				$(".pw").html("사용불가").css("color", "red");
			} else {
				$(".pw").html("비밀번호 확인바랍니다.").css("color", "red");
				$("#pw").attr("data-able", "f");
			}
		} else {
			$(`.${$(e.target).prop("id")}`).html("사용불가").css("color", "red");
			$(e.target).attr("data-able", "f");
		}
	}
}

$(".close_btn").on("click", function () {
    $(this).parent().css("display", "none");
    if ($(this).parent().prop("id") == "controller") {
        menuClose();
    }
})

function menuClose() {
    $("#color_picker").css("display", "none");
    $("#border_menu").css("display", "none");
    $("#size_menu").css("display", "none");
    $("#font_size_menu").css("display", "none");
}

$(".colors").on("click", function (e) {
    menuClose();
    $("#color_change").data("css", $(this).prop("id"));
    $("#color_picker").css("display", "flex").css("top", e.clientY).css("left", e.clientX);
})

$("#color_change").on("click", function () {
    const css = $(this).data("css");
    const color = $("#cp").val();
    const target = $("#controller").data("target");

    $(`#${target}`).css(css, color);

})

$("#font_size").on("click", function(e){
    menuClose();
    const target = $("#controller").data("target");
    $("#font_size_input").val($(`#${target}`).css("font-size").replace("px",""));
    $("#font_size_menu").css("display","flex").css("top",e.clientY).css("left",e.clientX);
})

$("#font_size_apply").on("click", function(){
    const target = $("#controller").data("target");
    $(`#${target}`).css("font-size",$("#font_size_input").val()+"px");
    $(".input").css("font-size",$("#font_size_input").val()+"px");
})

$("#border").on("click", function(e){
    menuClose();
    const target = $("#controller").data("target");
    $("#border_menu").css("display","flex").css("top",e.clientY).css("left",e.clientX);
    $("#border_top").val($(`#${target}`).css("border-top-width").replace("px",""));
    $("#border_bottom").val($(`#${target}`).css("border-bottom-width").replace("px",""));
    $("#border_left").val($(`#${target}`).css("border-left-width").replace("px",""));
    $("#border_right").val($(`#${target}`).css("border-right-width").replace("px",""));
    $("#border_color").val($(`#${target}`).css("border-color"));
    $("#border_radius").val($(`#${target}`).css("border-radius").replace("px",""));
})

$("#border_apply").on("click", function(){
    const target = $("#controller").data("target");
    $(`#${target}`)
    .css("border","solid")
    .css("box-sizing","border-box")
    .css("border-top-width", $("#border_top").val()+"px")
    .css("border-bottom-width", $("#border_bottom").val()+"px")
    .css("border-left-width", $("#border_left").val()+"px")
    .css("border-right-width", $("#border_right").val()+"px")
    .css("border-color", $("#border_color").val())
    .css("border-radius", $("#border_radius").val()+"px")
    ;
})

$("#size").on("click", function(e){
    menuClose();
    const target = $("#controller").data("target");
    $("#size_menu").css("display","flex").css("top",e.clientY).css("left",e.clientX);
    $("#size_width").val($(`#${target}`).css("width").replace("px",""));
    $("#size_height").val($(`#${target}`).css("height").replace("px",""));
})

$("#size_change").on("click", function(){
    const target = $("#controller").data("target");
    const width = $("#size_width").val();
    const height = $("#size_height").val();
    $(`#${target}`).css("width", width).css("height", height);
    $("#size_menu").css("display","none");
})

function loadFunc() {


	$(".modi_input").off("keyup").on("keyup",function(e){
		createRegExp(e);
	})

	$("#sign_img").off("click").on("click", function(e) {
		$("#sign_element").css("left",e.pageX).css("top",e.pageY).toggle();
		$(".tr_remove").each(function(i,t) {
			$(`#${$(t).data("id")}`).css("display","none");
		})
	})

	$(".tr_remove").off("click").on("click", function() {
		$(this).parent().parent().remove();
		$(`#${$(this).data("id")}`).css("display","block");
	})

	$("#address_select").off("change").on("change", function () {
		if ($(this).val() == "dir") {
			console.log("hi")
			$("#dir_address").removeAttr("readonly");
			$("#dir_address").val("");
		} else {
			console.log("bye")
			$("#dir_address").val($(this).val());
			$("#dir_address").attr("readonly", true);
		}
		$("#mail").val($("#fe").val() + "@" + $("#dir_address").val());
	})
	
	$("#dir_address").on("change", function () {
		$("#mail").val($("#fe").val() + "@" + $("#dir_address").val());
	})
	$("#fe").on("blur", function () {
		$("#mail").val($("#fe").val() + "@" + $("#dir_address").val());
	})
	$("#fp").on("change", function () {
		$("#phone").val($("#fp").val() + "" + $("#bp").val());
	})
	$("#bp").on("blur", function () {
		$("#phone").val($("#fp").val() + "" + $("#bp").val());
	})

//	$(".modi_span").off("click").on("click", function(e) {
//		$("#modify_span").css("left",e.pageX).css("top",e.pageY).toggle();
//		console.log($(this).prop("id"));
//		$("#ss_modify").data("id",$(this).prop("id"));
//	})
	$(".modi_span").on("dblclick", function () {
    if (!$(this).attr("contenteditable")) {
        $(this).attr("contenteditable", true);
    } else {
        $(this).attr("contenteditable", false);
    }
})

//	$("#ss_modify").off("click").on("click",function(){
//		const prom = prompt("변경할 내용을 적으세요.");
//		$(`#${$(this).data("id")}`).html(prom);
//	})

	$("#sign_submit").off("click").on("click", function(e) {
		e.preventDefault();
		let unable = false;
		$("input[data-able]").each(function(i,a) {
			if($(a).attr("data-able") == "f"){
				alert("필수 입력정보를 입력하세요.");
				unable = true;
				return false;
			}
		})

		if(unable){
			return false;
		}
		const id = $("#id").val();
		const pw = $("#pw").val();
		const name = $("#name").val();
		const email = $("#mail").val();
		const phone = $("#phone").val();
		const birth = $("#birth").val();

		const sData = {
				id,pw,name,email,phone,birth,url
		};
		console.log(sData)
		$.ajax({
			type:"post",
			url:"/signup",
			data:JSON.stringify(sData),
			contentType: "application/json; charset=utf-8",
			success:function(){
				alert("가입되었습니다.");
				location.href = `/${url}/home`;
			},
			error:function(){
				alert("가입 실패");
			}
		})
	})

	$("#dup_check").off("click").on("click", function() {
		$.getJSON("/dupcheck",{id:$("#id").val()},function(){
			alert("중복된 id 입니다.");
			$("#id").attr("data-able","f");
			$(".id").html("중복된 id 입니다.").css("color","red");
		})
		.fail(function() {
			alert("사용가능한 id 입니다.");
			$("#id").attr("data-able","t");
			$(".id").html("사용가능한 id 입니다.").css("color","green");
		})
	})
}