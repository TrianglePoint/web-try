<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Delete</title>
</head>
<body>
<form action="deleteAction.bo" method="post">
	<input type="hidden" name="num" value="${param.num}" />
	<input type="password" name="pwd" /><br />
	<input type="submit" value="Delete" />
</form>
</body>
</html>