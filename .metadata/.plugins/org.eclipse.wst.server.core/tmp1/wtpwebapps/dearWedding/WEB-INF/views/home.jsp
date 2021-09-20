<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>

	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성일</th>
				<th>작성자</th>
				<th>조회수</th>
			</tr>
		</thead>

		<tbody>

			<c:forEach items="${viewAll}" var="viewAll">
				<tr>
					<td>${viewAll.TB_IDX}</td>
					<td>${viewAll.TB_TITLE}</td>
					<td>${viewAll.TB_WRITE_DTM}</td>
					<td>${viewAll.TB_WRITE_ID}</td>
					<td>${viewAll.TB_CONTENT_CNT}</td>
				</tr>
			</c:forEach>



		</tbody>

	</table>




</body>
</html>
