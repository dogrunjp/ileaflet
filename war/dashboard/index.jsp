<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="ダッシュボード"/> 
  <c:param name="content"> 
  
  <div class="span2">
    <div class="well sidebar-nav">
      <ul class="nav nav-list">
        <li class="nav-header">Sidebar</li>
        <li class="active"><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li class="nav-header">Sidebar</li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li class="nav-header">Sidebar</li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
        <li><a href="#">Link</a></li>
      </ul>
    </div>
  </div>

  <div class="span10">
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
  </div>
 
  </c:param> 
</c:import> 