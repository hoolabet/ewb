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
<script>
	const url = document.querySelector("#url");
	setTimeout(() => {
		location.href = `/${url}/home`;
	}, 3000);
</script>
</body>
</html>