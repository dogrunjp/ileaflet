<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="パスワード再設定"/> 
  <c:param name="content"> 
  
<div class="span12">
<form method="POST" action="./setPassword">
	pass:<input type="password" name="password">
	pass:<input type="password" name="password2">
	<input type="hidden" name="registerCode" value="${registerCode}">
	<input type="submit">
</form>
</div>
  </c:param> 
</c:import> 
