<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="登録"/> 
  <c:param name="stylesheet"> 
  </c:param> 
  <c:param name="javascript"> 
  </c:param> 
  <c:param name="mainMenu"> 
  </c:param> 
  <c:param name="content"> 
<form method="POST" action="./register">
	id:<input type="text" name="identity" value="${identity}">
	name:<input type="text" name="name" value="${name}">
	mail:<input type="text" name="mail" value="${mail}">
	pass:<input type="password" name="password">
	pass:<input type="password" name="password2">
	<input type="submit" value="">
</form>
  </c:param> 
</c:import> 