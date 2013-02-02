<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="パスワード忘れ"/> 
  <c:param name="content"> 
  
        <div class="span12">
<form method="POST" action="./sendForgetMail">
	mail:<input type="text" name="mail">
	<input type="submit">
</form>
</div>
  </c:param> 
</c:import> 