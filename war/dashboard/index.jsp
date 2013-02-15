<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="ダッシュボード"/> 
  <c:param name="content"> 
 
<div class="span12 tabbable tabs-left">

<ul class="nav nav-tabs">
  <li class="active">
  <a href="#sns" data-toggle="tab">Section 1</a>
  </li>
  <li>
  <a href="#manage" data-toggle="tab">Section 2</a>
  </li>
  <li>
  <a href="#upload" data-toggle="tab">Section 3</a>
  </li>
</ul>


<div class="tab-content">

<div class="tab-pane active" id="sns">
TOP
</div>
<div class="tab-pane" id="manage">
<table class="table table-bordered">
<c:forEach var="obj" items="${contentList}" varStatus="status">
   <tr>
　　<td>編集</td>
    <td><a href="/view/${obj.key.id}"><c:out value="${obj.title}"/></a></td>
    <td><a href="/download/${obj.key.id}">ダウンロード</a></td>
   </tr>
</c:forEach> 
</table>
</div>

<div class="tab-pane" id="upload">
電子書籍をアップロード：
<form method="POST" action="./upload" enctype="multipart/form-data">
	<input type="file" name="epubFile"> <input type="submit" value="アップロード">
</form>
</div>

</div>

</div>
 
  </c:param> 
</c:import> 