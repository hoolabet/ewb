<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>create new page ...</title>
</head>
<body>
	create ${url} ...
	<input type="hidden" value="${url}" id="url">
	<input type="hidden" value="${opt}" id="opt">
	<input type="hidden" value="${ewbUser.id}" id="ewb_id">
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script src="../resources/js/newpage.js"></script>
</body>
</html>