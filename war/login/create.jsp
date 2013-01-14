<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login Create</title>
</head>
<body>

<form method="POST" action="./register">
	<input type="text" name="identity" value="${identity}">
	<input type="text" name="name" value="${name}">
	<input type="text" name="mail" value="${mail}">
	<input type="password" name="password">
	<input type="password" name="password2">
	<input type="submit">
</form>
</body>
</html>
