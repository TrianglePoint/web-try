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
		for(int i = beans.size()-1; i >= 0; i--){
			boardList += "<tr><td>" + beans.get(i).getNum() + "</td>" +
					"<td><a href='view.bo?num=" + beans.get(i).getNum() + "'>" + 
						beans.get(i).getTitle() + "</a></td>" +
					"<td>" + beans.get(i).getWriter() + "</td>" +
					"<td>" + beans.get(i).getWrite_date() + "</td>" +
					"<td>" + beans.get(i).getRead_count() + "</td></tr>";
		}
    }
    pageContext.setAttribute("boardList", boardList);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>List</title>
<link rel="stylesheet" type="text/css" href="./css/board/list.css" />
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
	<a href="write.bo">Write</a>
</body>
</html>