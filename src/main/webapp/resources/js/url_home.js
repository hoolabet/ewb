/**
 * 
 */
const url = $("#url").val();
$("#save").on("click", function(){
	$("#header_style").val($("#header").attr("style"));
	$("#footer_style").val($("#footer").attr("style"));
	$(".buttons").toggle();
	$.getJSON("/loadcontent",{url,type:"home_page"},function(){
		$.ajax({
			type:"put",
			url:"/modifycontent",
			data:JSON.stringify({url,content:$("#main_entry").html(),type:"home_page"}),
			contentType: "application/json; charset=utf-8",
			success: function() {
				$.ajax({
					type:"put",
					url:"/modifycontent",
					data:JSON.stringify({url,content:$("#header").html(),type:"header"}),
					contentType: "application/json; charset=utf-8",
					success:function(){
						$.ajax({
							type:"put",
							url:"/modifycontent",
							data:JSON.stringify({url,content:$("#footer").html(),type:"footer"}),
							contentType: "application/json; charset=utf-8",
							success:function(){
								alert("저장되었습니다.");
							}
						})
					}
				})
			}
		})

	})
})
$("#load").on("click", function(){
	$.getJSON("/loadcontent",{url,type:"home_page"},function(res){
		$("#main_entry").html(res.content);
		alert("불러오기를 실행했습니다.");
		$(".buttons").toggle();
		afterLoadRemoveElement();
		afterLoadCreateHome();
		$(".home_div").each(function(i,e){
			dragElement(e);
		})
		afterLoadCreateLogin();
		$(".login_div").each(function(i,e){
			dragElement(e);
		})
		afterLoadCreateUl();
		afterLoadCreateLi();
		$(".ul_div").each(function(i,e){
			dragElement(e);
		})
		$(".editable_div").each(function(i,e){
			dragElement(e);
		})
		$("body")
		.css("margin-top",$("#save_margin").data("margint")+"px")
		.css("margin-bottom",$("#save_margin").data("marginb")+"px")
		.css("margin-left",$("#save_margin").data("marginl")+"px")
		.css("margin-right",$("#save_margin").data("marginr")+"px");
		$("input[type='button']")
		.css("font-size", $("#save_button").data("ftsize")+"px")
		.css("padding", $("#save_button").data("padding")+"px")
		.css("background-color", $("#save_button").data("bgcolor"))
		.css("color", $("#save_button").data("ftcolor"))
		.css("border-width",$("#save_button").data("bdwidth"))
		.css("border-color",$("#save_button").data("bdcolor"));
		$("input[type='text']")
		.css("font-size", $("#save_text").data("ftsize")+"px")
		.css("padding", $("#save_text").data("padding")+"px")
		.css("background-color", $("#save_text").data("bgcolor"))
		.css("color", $("#save_text").data("ftcolor"))
		.css("border-width",$("#save_text").data("bdwidth"))
		.css("border-color",$("#save_text").data("bdcolor"));
	})
})



function dragElement(elmnt) {
	var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;

	$(elmnt).children().first().on("mousedown",function(){
		dragMouseDown();
	})

	function dragMouseDown(e) {
		e = e || window.event;
		e.preventDefault();
		// get the mouse cursor position at startup:
		pos3 = e.pageX;
		pos4 = e.pageY;
		document.onmouseup = closeDragElement;
		// call a function whenever the cursor moves:
		document.onmousemove = elementDrag;
		$("body").css("opacity","0.3");
	}

	function elementDrag(e) {
		e = e || window.event;
		e.preventDefault();
		// calculate the new cursor position:
		pos1 = pos3 - e.pageX;
		pos2 = pos4 - e.pageY;
		pos3 = e.pageX;
		pos4 = e.pageY;
		// set the element's new position:
		elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
		elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
	}

	function closeDragElement() {
		// stop moving when mouse button is released:
		document.onmouseup = null;
		document.onmousemove = null;
		$("body").css("opacity","1");
	}

}

$(document).on("contextmenu", function(e){
	e.preventDefault();
	if(e.target.id == "header" 
		|| e.target.id == "footer" 
			|| e.target.id == "main_content" 
				|| e.target.className == "home_div"
					|| e.target.className == "login_div" 
						|| e.target.className == "ul_div"
							|| e.target.className == "editable_div"
								|| e.target.className.includes("ul_li")
	){
		$("#controller").data("target",e.target.id);
		$("#target_name").html(e.target.id);
		$("#controller").css("display","none").css("display","flex");
		menuClose();
		console.log($("#controller").data("target"));
		if(e.target.className == "login_div" 
			|| e.target.className == "editable_div"
				|| e.target.className == "home_div"
		){
			$("#create_home").css("display","none");
			$("#create_element").css("display","none");
			$("#direction").css("display","none");
			$("#margin").css("display","none");
			$("#center").css("display","flex");
			$("#inner_margin").css("display","none");
			$("#place").css("display","flex");
		}else if(e.target.className == "ul_div"){
			$("#create_home").css("display","none");
			$("#create_element").css("display","none");
			$("#direction").css("display","flex");
			$("#inner_margin").css("display","flex");
			$("#margin").css("display","none");
			$("#center").css("display","flex");
			$("#place").css("display","flex");
		}else if(e.target.className.includes("ul_li")){
			$("#create_home").css("display","none");
			$("#create_element").css("display","none");
			$("#center").css("display","none");
			$("#direction").css("display","none");
			$("#inner_margin").css("display","none");
			$("#place").css("display","none");
		}else if(e.target.id == "header"){
			$("#create_home").css("display","block");
			$("#create_element").css("display","flex");
			$("#direction").css("display","none");
			$("#margin").css("display","flex");
			$("#center").css("display","none");
			$("#inner_margin").css("display","none");
			$("#place").css("display","none");
		}else{
			$("#create_home").css("display","none");
			$("#create_element").css("display","flex");
			$("#direction").css("display","none");
			$("#margin").css("display","flex");
			$("#center").css("display","none");
			$("#inner_margin").css("display","none");
			$("#place").css("display","none");
		}
	}
})
function menuClose(){
	$("#color_picker").css("display","none");
	$("#create_element_menu").css("display","none");
	$("#direction_menu").css("display","none");
	$("#border_menu").css("display","none");
	$("#margin_menu").css("display","none");
	$("#center_menu").css("display","none");
	$("#size_menu").css("display","none");
	$("#place_menu").css("display","none");
	$("#font_size_menu").css("display","none");
}
$("#body_controller_btn").on("click", function(){
	menuClose();
	$("#body_controller").css("display","flex");
	$("#main_entry").css("opacity","0.5");
})

$("#margin_controller_btn").on("click", function(){
	$("#margin_controller").css("display","flex");
	$("#body_top").val($("body").css("margin-top").replace("px",""));
	$("#body_bottom").val($("body").css("margin-bottom").replace("px",""));
	$("#body_left").val($("body").css("margin-left").replace("px",""));
	$("#body_right").val($("body").css("margin-right").replace("px",""));
})

$("#entry_margin_apply").on("click", function(){
	$("body").css("margin-top",$("#body_top").val()+"px")
	.css("margin-bottom",$("#body_bottom").val()+"px")
	.css("margin-left",$("#body_left").val()+"px")
	.css("margin-right",$("#body_right").val()+"px");
	$("#save_margin").attr("data-margint",$("#body_top").val())
	.attr("data-marginb",$("#body_bottom").val())
	.attr("data-marginl",$("#body_left").val())
	.attr("data-marginr",$("#body_right").val());
	$("#margin_controller").css("display","none");
})

$(".input_controller_btn").on("click", function(){
	const type = $(this).data("type");
	$("#entry_input_apply").data("type",type);
	$("#input_controller").css("display", "flex");
	$("#input_ftsize").val($(`input[type="${type}"]`).css("font-size").replace("px",""));
	$("#input_padding").val($(`input[type="${type}"]`).css("padding-top").replace("px",""));
	$("#input_bdwidth").val($(`input[type="${type}"]`).css("border-width").replace("px",""));
	$("#input_bdcolor").val($(`input[type="${type}"]`).css("border-color"));
	$("#input_bgcolor").val($(`input[type="${type}"]`).css("background-color"));
	$("#input_ftcolor").val($(`input[type="${type}"]`).css("color"));
})

$("#entry_input_apply").on("click", function(){
	const type = $(this).data("type");
	$("#input_controller").css("display", "none");
	$(`input[type="${type}"]`)
	.css("font-size", $("#input_ftsize").val()+"px")
	.css("padding", $("#input_padding").val()+"px")
	.css("background-color", $("#input_bgcolor").val())
	.css("color", $("#input_ftcolor").val())
	.css("border-width",$("#input_bdwidth").val())
	.css("border-color",$("#input_bdcolor").val());
	$(`#save_${type}`)
	.attr("data-ftsize",$("#input_ftsize").val())
	.attr("data-padding",$("#input_padding").val())
	.attr("data-bgcolor",$("#input_bgcolor").val())
	.attr("data-ftcolor",$("#input_ftcolor").val())
	.attr("data-bdwidth",$("#input_bdwidth").val())
	.attr("data-bdcolor",$("#input_bdcolor").val());
})

$(".close_btn").on("click", function(){
	$(this).parent().css("display","none");
	if($(this).parent().prop("id") == "body_controller"){
		$("#margin_controller").css("display","none");
		$("#main_entry").css("opacity","1");
	}else if($(this).parent().prop("id") == "controller"){
		menuClose();
	}
})
$('.cp').minicolors();


$(".colors").on("click", function(e){
	menuClose();
	$("#color_change").data("css",$(this).prop("id"));
	$("#color_picker").css("display","flex").css("top",e.clientY).css("left",e.clientX);
})

$("#color_change").on("click", function(){
	const css = $(this).data("css");
	const color = $("#cp").val();
	const target = $("#controller").data("target");
	if(target.includes("li_header_") && css == "color"){
		const target_li = $(`#${target}`).data("target");
		const ndnow = $(`#${target}`).data("ndnow");
		console.log(ndnow);
		$(`#li_span_${target_li}_${ndnow}`).css(css,color);
	}else if(target == "home_div_header" && css == "color"){
		$("#home_span_header").css(css,color);
	}else{
		$(`#${target}`).css(css, color);
	}
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
	$(".buttons").css("font-size","16px");
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

$("#margin").on("click", function(e){
	menuClose();
	const target = $("#controller").data("target");
	$("#margin_menu").css("display","flex").css("top",e.clientY).css("left",e.clientX);
	$("#margin_top").val($(`#${target}`).css("margin-top").replace("px",""));
	$("#margin_bottom").val($(`#${target}`).css("margin-bottom").replace("px",""));
	$("#margin_left").val($(`#${target}`).css("margin-left").replace("px",""));
	$("#margin_right").val($(`#${target}`).css("margin-right").replace("px",""));
})

$("#margin_apply").on("click", function(){
	const target = $("#controller").data("target");
	$(`#${target}`)
	.css("margin-top", $("#margin_top").val()+"px")
	.css("margin-bottom", $("#margin_bottom").val()+"px")
	.css("margin-left", $("#margin_left").val()+"px")
	.css("margin-right", $("#margin_right").val()+"px")
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

$("#place").on("click", function(e){
	menuClose();
	const target = $("#controller").data("target");
	$("#place_menu").css("display","flex").css("top",e.clientY).css("left",e.clientX);
	$("#place_top").val($(`#${target}`).css("top").replace("px",""));
	$("#place_left").val($(`#${target}`).css("left").replace("px",""));
})

$("#place_change").on("click", function(){
	const target = $("#controller").data("target");
	$(`#${target}`).css("top",$("#place_top").val()+"px").css("left",$("#place_left").val()+"px");
})

$("#center").on("click", function(e){
	menuClose();
	$("#center_menu").css("display","flex").css("top",e.clientY).css("left",e.clientX);
})

$(".center").on("click", function(){
	const target = $("#controller").data("target");
	const cen = $(this).data("center");
	if(cen == "x"){
		$(`#${target}`).css("left",($("html").width() - $(`#${target}`).width())/2 - $("body").css("margin-left").replace("px",""));
	}else if(cen == "y"){
		$(`#${target}`).css("top",($("html").height() - $(`#${target}`).height())/2 - $("body").css("margin-top").replace("px",""));
	}
})

$("#create_element").on("click", function(e){
	menuClose();
	$("#create_element_menu").css("display","flex").css("top",e.clientY).css("left",e.clientX);
})

function afterLoadRemoveElement(){
	$(".remo_element").on("click", function(){
		$(this).parent().remove();
	})
}

function afterLoadCreateHome(){
	$(".home_span").on("click", function(){
		const target = $(this).data("target");
		console.log(target)
		if($(`#home_span_${target}_modi`).val() == ""){
			alert("1 자 이상 적으세요.");
			return false;
		}
		$(`#home_span_${target}`).html($(`#home_span_${target}_modi`).val());
		$(`#home_span_${target}`).toggle();
		$(`#home_span_${target}_modi`).toggle();
	})
}

$("#create_home").on("click", function(){
	const target = $("#controller").data("target");
	const home = `
		<div id="home_div_${target}" class="home_div">
		<div class="move_divs_handler buttons" id="home_div_handler_${target}">✔</div>
		<div id="home_a_${target}" class="home_a">
		<a href="#"><span id="home_span_${target}">HOME</span></a>
		<input value="HOME" size="8" type="text" id="home_span_${target}_modi" style="display:none;">
		<span class="home_span buttons" data-target="${target}">🛠</span>
		</div>
		<div class="remo_element buttons">✖</div>
		</div>
		`;
	$(`#home_div_${target}`).remove();
	$(`#${target}`).append(home);
	afterLoadCreateHome();
	afterLoadRemoveElement();
	dragElement($(`#home_div_${target}`)[0]);
})

function afterLoadCreateLogin(){
	$(".modi_span").on("dblclick", function(){
		if($(this).attr("contenteditable") != "true"){
			$(this).css("border","1px solid black");
			$(this).attr("contenteditable",true);
		}else{
			$(this).css("border","0");
			$(this).attr("contenteditable",false);
		}
	})
}

$("#create_login").on("click", function(){
	const target = $("#controller").data("target");
	const login = `
		<div id="login_div_${target}" class="login_div">
		<div class="move_divs_handler buttons" id="login_div_handler_${target}">✔</div>
		<div id="login_table_${target}" class="login_table">
		<table>
		<tr>
		<td><span class="modi_span" id="modi_id_${target}">ID</span></td>
		<td><input type="text" id="login_id_${target}"></td>
		</tr>
		<tr>
		<td><span class="modi_span" id="modi_pw_${target}">PW</span></td>
		<td><input type="text" id="login_pw_${target}"></td>
		</tr>
		<tr>
		<td colspan=2>
		<input type="button" value="Log In" class="login_btn" data-target="${target}">
		<input type="button" value="Sign Up" class="signup_btn">
		</td>
		</tr>
		</table>
		</div>
		<div class="remo_element buttons">✖</div>
		</div>
		`;
	$(`#login_div_${target}`).remove();
	$(`#${target}`).append(login);
	afterLoadCreateLogin();
	afterLoadRemoveElement();
	dragElement($(`#login_div_${target}`)[0]);
})

function afterLoadCreateUl(){
	$(".add_li").off("click").on("click", function(){
		const target = $(this).parent().data("target");
		const dnow = $(this).parent().data("dnow");
		const ndnow = Date.now();
		const li = `
			<li class="ul_li li_${target}_${dnow}" id="li_${target}_${ndnow}" data-target="${target}" data-ndnow="${ndnow}">
			<a href="#" id="li_a_${target}_${ndnow}"><span id="li_span_${target}_${ndnow}">목록</span></a>
			<input value="#" size="4" type="text" id="li_a_${target}_${ndnow}_modi" style="display:none;">
			<input value="목록" size="4" type="text" id="li_span_${target}_${ndnow}_modi" style="display:none;">
			<span class="modi_li buttons" data-target="${target}" data-ndnow="${ndnow}" style="cursor:pointer">🛠</span>
			<span class="remo_li buttons" style="cursor:pointer">✖</span>
			</li>
			`;
		$(`#ul_${target}_${dnow}`).append(li);
		afterLoadCreateLi();
	})
}

function afterLoadCreateLi(){
	$(".modi_li").off("click").on("click", function(){
		const target = $(this).data("target");
		const ndnow = $(this).data("ndnow");

		if($(`#li_span_${target}_${ndnow}_modi`).val() == ""
			|| $(`#li_a_${target}_${ndnow}_modi`).val() == ""
		){
			alert("1 자 이상 적으세요.");
			return false;
		}
		$(`#li_a_${target}_${ndnow}`).attr("href",$(`#li_a_${target}_${ndnow}_modi`).val());
		$(`#li_span_${target}_${ndnow}`).html($(`#li_span_${target}_${ndnow}_modi`).val());

		$(`#li_span_${target}_${ndnow}`).toggle();
		$(`#li_span_${target}_${ndnow}_modi`).toggle();
		$(`#li_a_${target}_${ndnow}_modi`).toggle();
	})
	$(".remo_li").on("click", function(){
		$(this).parent().remove();
	})
}

$("#create_ul").on("click", function(){
	const target = $("#controller").data("target");
	const dnow = Date.now();
	const ul = `
		<div id="ul_div_${target}_${dnow}" class="ul_div" data-target="${target}" data-dnow="${dnow}">
		<div class="move_divs_handler buttons" id="ul_div_handler_${target}_${dnow}">✔</div>
		<ul id="ul_${target}_${dnow}" class="ul_">
		</ul>
		<div class="add_li buttons" style="cursor:pointer">➕</div>
		<div class="remo_element buttons">✖</div>
		</div>
		`;
	$(`#${target}`).append(ul);
	afterLoadCreateUl();
	afterLoadRemoveElement();
	dragElement($(`#ul_div_${target}_${dnow}`)[0]);
})

$("#direction").on("click", function(e){
	menuClose();
	$(".list_direction").prop("checked",false);
	$("#direction_menu").css("display","flex").css("top",e.clientY).css("left",e.clientX);
})

$(".list_direction").on("click", function(){
	const target = $("#controller").data("target");
	const target_li = $(`#${target}`).data("target");
	const dnow = $(`#${target}`).data("dnow");
	$(`.li_${target_li}_${dnow}`).css("display",$(this).val());
})

$("#inner_margin").on("click", function(e){
	menuClose();
	const target = $("#controller").data("target");
	const target_li = $(`#${target}`).data("target");
	const dnow = $(`#${target}`).data("dnow");

	$("#inner_margin_menu").css("display","flex").css("top",e.clientY-200).css("left",e.clientX);
	$("#inner_margin_top").val($(`.li_${target_li}_${dnow}`).css("margin-top").replace("px",""));
	$("#inner_margin_bottom").val($(`.li_${target_li}_${dnow}`).css("margin-bottom").replace("px",""));
	$("#inner_margin_left").val($(`.li_${target_li}_${dnow}`).css("margin-left").replace("px",""));
	$("#inner_margin_right").val($(`.li_${target_li}_${dnow}`).css("margin-right").replace("px",""));
})

$("#inner_margin_apply").on("click", function(){
	const target = $("#controller").data("target");
	const target_li = $(`#${target}`).data("target");
	const dnow = $(`#${target}`).data("dnow");
	$(`.li_${target_li}_${dnow}`)
	.css("margin-top", $("#inner_margin_top").val()+"px")
	.css("margin-bottom", $("#inner_margin_bottom").val()+"px")
	.css("margin-left", $("#inner_margin_left").val()+"px")
	.css("margin-right", $("#inner_margin_right").val()+"px")
	;
})

$("#create_editable").on("click", function(){
	const target = $("#controller").data("target");
	const dnow = Date.now();
	const editable = `
		<div id="editable_div_${target}_${dnow}" class="editable_div" data-target="${target}" data-dnow="${dnow}">
		<div class="move_divs_handler buttons" id="editable_div_handler_${target}_${dnow}">✔</div>
		<div class="remo_element buttons">✖</div>
		<div contenteditable="true"></div>
		</div>
		`;
	$(`#${target}`).append(editable);
	afterLoadRemoveElement();
	dragElement($(`#editable_div_${target}_${dnow}`)[0]);
})