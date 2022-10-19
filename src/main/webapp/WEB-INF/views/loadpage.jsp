<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>loading ${url}</title>
</head>
<body>
	loading ${url}...
	<script>
	setTimeout(() => {
		location.href = "/management";
	}, 3000);	
	</script>
</body>
</html>