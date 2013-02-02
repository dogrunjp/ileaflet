<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="ログイン"/> 
  <c:param name="content"> 
  
        <div class="span12">
<form method="POST" action="./logon">
	id:<input type="text" name="identity" value="${identity}">
	pass:<input type="password" name="password">
	<input type="hidden" name="redirect" value="${redirect}">
	<input type="submit">
</form>

<a href="./?type=create">アカウントの新規作成</a>
<a href="./?type=forget">パスワードを忘れたら。。。</a>

</div>

  </c:param> 
</c:import> 
