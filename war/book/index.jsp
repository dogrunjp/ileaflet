<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="本紹介ページ"/> 
  <c:param name="content"> 
 
<div class="span12">

twitter
facebook
instagram
mixi
pintarest
hatena
google+
evernote
gree




<button class="btn btn-large btn-primary" type="button" onclick="document.location='../view/${content.key.id}'">この本を読む</button>

</div>
 
  </c:param> 
</c:import> 