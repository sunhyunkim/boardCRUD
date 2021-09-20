<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>insert title here</title>
</head>
<body>
<body>
<h1>게시판 목록</h1>
<table border="1">
 <colgroup>
 <col width="50">
 <col width="100">
 <col width="300">
 <col width="100">
 </colgroup>
  <tr>
   <th>번호</th>
   <th>작성자</th>
   <th>제목</th>
   <th>날짜</th>
  </tr>
<c:forEach items="${list}" var="dto">
 <tr>
  <td>${dto.seq}</td>
  <td>${dto.writer}</td>
  <td><a href="one?seq=${dto.seq}">${dto.title}</a></td>
  <td>${dto.regdate}</td>
 </tr>
</c:forEach> 
<tr>
<td colspan="4" align="right">
<input type="button" value="글쓰기" onclick="location.href='insert'">
</td>
</tr>
 </table>
</body>
</html>
