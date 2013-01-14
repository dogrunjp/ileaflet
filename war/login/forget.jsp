<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="f" uri="http://www.slim3.org/functions"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>login Forget</title>
</head>
<body>
<p>Hello login Forget !!!</p>

<form method="POST" action="./sendForgetMail">
	<input type="text" name="mail">
	<input type="submit">
</form>

</body>
</html>
