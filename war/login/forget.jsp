<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="パスワード忘れ"/> 
  <c:param name="stylesheet"> 
  </c:param> 
  <c:param name="javascript"> 
  </c:param> 
  <c:param name="mainMenu"> 
  </c:param> 
  <c:param name="content"> 
<form method="POST" action="./sendForgetMail">
	mail:<input type="text" name="mail">
	<input type="submit">
</form>
  </c:param> 
</c:import> 