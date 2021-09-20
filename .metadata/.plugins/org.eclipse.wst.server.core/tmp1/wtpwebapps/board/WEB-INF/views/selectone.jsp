<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table border="1">
<tr>
<th>NAME</th>
<td>${dto.writer}</td>
</tr>
<tr>
<th>TITLE</th>
<td>${dto.title}</td>
</tr>
<tr>
<th>CONTENT</th>
<td><textarea rows="10" cols="60" readonly="readonly">${dto.content}</textarea></td>
</tr>
<tr>
<td colspan="2" align="right">
<input type="button" value="수정" onclick="location.href='update?seq=${dto.seq}'">
<input type="button" value="삭제" onclick="location.href='delete?seq=${dto.seq}'">
<input type="button" value="목록" onclick="location.href='goback'">
</td>
</tr>
</table>
</body>
</html>