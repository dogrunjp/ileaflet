<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="ダッシュボード"/> 
  <c:param name="content"> 
 
<div class="span12 tabbable tabs-left">

<ul class="nav nav-tabs">
  <li class="active">
  <a href="#sns" data-toggle="tab">SNS</a>
  </li>
  <li>
  <a href="#manage" data-toggle="tab">Manage</a>
  </li>
  <li>
  <a href="#upload" data-toggle="tab">Upload</a>
  </li>
  
  <li>
  <a href="#gcsUpload" data-toggle="tab">GCS Upload</a>
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
　　<td><button>編集</button></td>
    <td><a href="/book/${obj.key.id}"><c:out value="${obj.title}"/></a></td>
    <td><a href="/download/${obj.key.id}">ダウンロード</a></td>
　　<td><button>削除</button></td>
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

<div class="tab-pane" id="gcsUpload">
電子書籍をアップロード：

<form id="fieldsForm" method="POST">
</form>

<form method="POST" action="${uploadUrl}" enctype="multipart/form-data">

	<input type="hidden" name="key" value="${key}"/>
	<input type="hidden" name="bucket" value="${bucket}">
	<input type="hidden" name="Content-Type" value="${contentType}">
	<input type="hidden" name="GoogleAccessId" value="${accessId}">
	<input type="hidden" name="acl" value="${acl}">
	<input type="hidden" name="success_action_redirect" value="${success_action_redirect}">
	<input type="hidden" name="policy" value="${policy}">
	<input type="hidden" name="signature" value="${signature}">

	<input type="file" name="file">
	<input type="submit" value="アップロード">
</form>
</div>

</div>

</div>
 
  </c:param> 
</c:import> 