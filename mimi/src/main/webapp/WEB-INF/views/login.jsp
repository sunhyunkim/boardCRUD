<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<form action="login" method="POST">
		id : <input type="text" name="loginid" /> </br>
		pw : <input type="password" name="loginpw"  /></br>
	<input type="submit" value="로그인"/></form>
	<form action="notyet" method="POST">
	<input type="submit" value="회원가입"/></br></form>
	<form action="find" method="POST">
	<input type="submit" value="아이디/비밀번호 찾기"/>
</form>

</table>
</body>
</html>