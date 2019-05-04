<%@page import="net.board.db.BoardBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String boardList = "";

    List<BoardBean> beans = (List<BoardBean>)request.getAttribute("boardBeans");
    if(beans.size() == 0){
    	boardList += "<tr><td colspan='5'>Not List</td></tr>";
    }else{
		for(BoardBean bean : beans){
			boardList += "<tr><td>" + bean.getBoard_num() + "</td>" +
					"<td>" + bean.getTitle() + "</td>" +
					"<td>" + bean.getWriter() + "</td>" +
					"<td>" + bean.getWrite_date() + "</td>" +
					"<td>" + bean.getRead_count() + "</td></tr>";
		}
    }
    pageContext.setAttribute("boardList", boardList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./board/boardList.css" />
</head>
<body>
	<table border="1">
		<tr>
			<th>Number</th>
			<th>Title</th>
			<th>Writer</th>
			<th>Date</th>
			<th>Read</th>
		</tr>
		${boardList}
	</table>
	<a href="./write.bo">Write</a>
</body>
</html>