<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<head>
<meta charset="utf-8" />
<style>
h1 {
	text-align: center;
}

* {
	margin: 0;
	padding: 0;
}

body {
	front: 17px 'Nanum Gothic', sans-serif;
}

a {
	text-decoration: none;
	color: #404040;
	background-color: #98EOAD;
}

li {
	list-style: none;
}

#Nav {
	background-color: #98EOAD;
}

#Nav ul {
	width: 25% margin: 0 auto;
	overflow: hidden;
}

#Nav ul li {
	float: left;
	width: 25%;
	height: 50px;
	line-height: 50px;
	text-align: center;
}

#Nav ul li a {
	display: block;
}

#Nav ul li a:hover {
	background: #138535;
	color: #fff;
}
</style>

</head>
<body>
	<h1>MiMi</h1>

	<nav id="Nav">
		<ul>
			<li><a href="#">외투</a></li>
			<li><a href="bottom">상의</a></li>
			<li><a href="#">하의</a></li>
			<li><a href="#">악세서리</a></li>
		</ul>
	</nav>
	<a href="a">
	<img src="image/a.jpg" width="200px" height="200px" /></a>
	<li><a href="a">상품명: 몰랑이</a></li>
	<li><a href="a">가격: 4500원</a></li>
</body>
</html>