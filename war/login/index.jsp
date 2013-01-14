<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login Index</title>
</head>
<body>

<form method="POST" action="./logon">
	<input type="text" name="identity">
	<input type="text" name="password">
	<input type="hidden" name="redirect" value="${redirect}">
	<input type="submit">
</form>

<a href="./?type=create">アカウントの新規作成</a>

<a href="./?type=forget">パスワードを忘れたら。。。</a>

</body>
</html>
