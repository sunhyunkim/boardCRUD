<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="bdupdate" method="post">
		<input type="hidden" name="seq" value="${dto.seq}"}>
		<table border="1">
<tr>
<th>NAME</th>
<td>${dto.writer}</td>
</tr>
<tr>
<th>TITLE</th>
<td><input type="text" name="title" value="${dto.title}"></td>
</tr>
<tr>
<th>CONTENT</th>
<td><textarea rows="10" cols="60" name="content">${dto.content}</textarea>
</tr>
<tr>
<td colspan="2" align="right">
<input type="submit" value="완료">
<input type="button" value="취소" onclick="location.href='goback'">
			</td>
			</tr>
		</table>
	</form>
</body>
</html>