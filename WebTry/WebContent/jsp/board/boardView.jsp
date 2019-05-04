<%@page import="net.board.db.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	BoardBean bean = (BoardBean)request.getAttribute("boardBean");
	pageContext.setAttribute("bean", bean);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
	<tr>
		<td colspan="3">Writer : ${bean.writer}</td>
		<td>count : ${bean.read_count}</td>
	</tr>
	<tr>
		<td colspan="2">Title : ${bean.title}</td>
		<td colspan="2">Date : ${bean.write_date}</td>
	</tr>
	<tr>
		<td colspan="4">${bean.text}</td>
	</tr>
</table>
<a href="list.bo">Back</a>
</body>
</html>