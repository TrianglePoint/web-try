<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write</title>
</head>
<body>
	<form action="writeAction.bo" method="post">
		<span>Writer </span><input type="text" name="writer" /><br />
		<span>Title </span><input type="text" name="title" /><br />
		<textarea rows="5" cols="50" name="text"></textarea><br />
		<span>Password </span><input type="password" name="pwd" /><br />
		<input type="submit" value="Submit" />
	</form>
</body>
</html>