/**
 * 
 */
const url = $("#url").val();
const opt = $("#opt").val();
const ewbId = $("#ewb_id").val();
let sc = "";
if(opt == "shopping"){
	sc = "product";
}else if(opt == "community"){
	sc = "board";
}
//$.ajax({
//	type:"post",
//	url:"/savecontent",
//	data:JSON.stringify({id:ewbId,url,content:"",type:"home_page",opt}),
//	contentType: "application/json; charset=utf-8",
//	success: function() {
//		$.ajax({
//			type:"post",
//			url:"/savecontent",
//			data:JSON.stringify({url,content:"",type:"header"}),
//			contentType: "application/json; charset=utf-8",
//			success:function(){
//				$.ajax({
//					type:"post",
//					url:"/savecontent",
//					data:JSON.stringify({url,content:"",type:"footer"}),
//					contentType: "application/json; charset=utf-8",
//					success:function(){
//						$.ajax({
//							type:"post",
//							url:"/savecontent",
//							data:JSON.stringify({url,content:"",type:"signup_page"}),
//							contentType: "application/json; charset=utf-8",
//							success:function(){
//								$.ajax({
//									type:"post",
//									url:"/savecontent",
//									data:JSON.stringify({url,content:"",type:`${sc}_page`}),
//									contentType: "application/json; charset=utf-8",
//									success:function(){
//									}
//								})
//							}
//						})
//					}
//				})
//			}
//		})
//	}
//})

setTimeout(() => {
	location.href = `/${url}/home`;
}, 5000);