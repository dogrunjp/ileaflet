<%@page pageEncoding="UTF-8" isELIgnored="false" session="false"%>
<c:import url="../mainFrame.jsp"> 
  <c:param name="title" value="本のページ"/> 
  <c:param name="content"> 
  
<div class="span12 tabbable tabs-left">
	<ul class="nav nav-tabs" id="nav">
	</ul> 
<div class="tab-content" id="tab-content">
	<div class="tab-pane active" id="bookPanel" width="90%" height="100%">
		<iframe id="epub" width="90%" height="100%"></iframe>
	</div>
</div>
        
</div>
        
<script type="text/javascript" src="/js/epub/jquery.zip.js"></script> 
<script type="text/javascript" src="/js/epub/base64.js"></script> 
<script type="text/javascript" src="/js/epub/utf8.js" charset="utf-8"></script> 
<script type="text/javascript" src="/js/epub/epub.js" charset="utf-8"></script> 

<script type="text/javascript">
function setHTML(id) {
	var frameNode = document.getElementById("epub");
	frameNode.contentWindow.document.body.innerHTML = epub.getHTML(id);
}

//コンテンツIDからデータを取得
epub.init("../download/${content.key.id}",
	function() {
		for ( var idx = 0; idx < epub.pageList.length; ++idx ) {
		
			var id = epub.pageList[idx];
			
			var li = $("<li/>");
			li.attr("id",id);
			if ( idx == 0 ) {
				li.addClass("active");
				setHTML(id);
			}

			var a = $("<a/>");
			a.attr("href","#" + id);
			a.attr("data-toggle","tab");
			a.attr("onclick","setHTML('" + id + "')");
			a.text(id);

			li.append(a);

			$("#nav").append(li);
		}
	}
);




</script>
 
  </c:param> 
</c:import> 