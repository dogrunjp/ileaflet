<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="ログイン"/> 
  <c:param name="content"> 
  
        <div class="span12">
        
<form method="POST" action="./logon" class="form-horizontal form-inline">
<div class="control-group">
  <label class="control-label">ID:</label>
  <div class="controls">
    <input type="text" class="input-xlarge" placeholder="ID" name="identity">
  </div>
</div>
<div class="control-group">
  <label class="control-label">PASSWORD:</label>
  <div class="controls">
    <input type="password" class="input-xlarge" placeholder="Password" name="password">
  </div>
</div>
<button type="submit" class="btn btn-primary">Sign in</button> 
<input type="hidden" name="redirect" value="${redirect}">
</form>

<a href="./?type=create">アカウントの新規作成</a>
<a href="./?type=forget">パスワードを忘れたら。。。</a>

</div>

  </c:param> 
</c:import> 
