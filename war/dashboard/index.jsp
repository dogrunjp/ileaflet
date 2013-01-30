<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="ダッシュボード"/> 
  <c:param name="stylesheet"> 
  </c:param> 
  <c:param name="javascript"> 
  </c:param> 
  <c:param name="mainMenu"> 
  </c:param> 
  <c:param name="content"> 
  
<form method="POST" action="./upload" enctype="multipart/form-data">
	<input type="file" name="epubFile">
	<input type="submit" value="アップロード">
</form>

<br>
<br>
<br>

<c:forEach var="obj" items="${contentList}" varStatus="status">
　　名前：<c:out value="${obj.title}"/>,Revision:<c:out value="${obj.targetRevision}"/><br/>
</c:forEach> 
 
 
  </c:param> 
</c:import> 