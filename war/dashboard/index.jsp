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
電子書籍をアップロード：
<form method="POST" action="./upload" enctype="multipart/form-data">
	<input type="file" name="epubFile"> <input type="submit" value="アップロード">
</form>

<br>
<br>
<br>

<c:forEach var="obj" items="${contentList}" varStatus="status">
　　編集｜<a href="/view/?id=${obj.key.id}"><c:out value="${obj.title}"/></a>｜<a href="/download/?id=${obj.key.id}">ダウンロード</a>
</c:forEach> 
 
 
  </c:param> 
</c:import> 