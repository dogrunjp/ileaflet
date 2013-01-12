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
<p>Hello login Index !!!</p>


<form method="POST" action="./logon">
	<input type="text" name="identity">
	<input type="text" name="password">
	<input type="submit">
</form>



</body>
</html>
