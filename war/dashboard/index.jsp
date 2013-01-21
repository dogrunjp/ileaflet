<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="ダッシュボード"/> 
  <c:param name="stylesheet"> 
  </c:param> 
  <c:param name="javascript"> 
  </c:param> 
  <c:param name="mainMenu"> 

<form method="POST" action="./upload" enctype="multipart/form-data">
	<input type="file" name="epubFile">
	<input type="submit" value="アップロード">
</form>
  
  </c:param> 
  <c:param name="content"> 
  </c:param> 
</c:import> 