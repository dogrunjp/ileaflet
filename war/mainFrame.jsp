<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${param.title}</title>
    <link type="text/css" rel="stylesheet" href="/css/global.css" />
    ${param.stylesheet}
    ${param.javascript}
  </head>
  <body id="body">

  <div id="header">
  	<div id="logo">
  	iLeaflet <ileaflet:login/>
    </div>

    <div id="mainMenu">
    ${param.mainMenu}
<c:if test="${not empty errors}">
<ul>
<c:forEach var="e" items="${f:errors()}">
<li><span class="error">${f:h(e)}</span></li>
</c:forEach>
</ul>
</c:if>
    </div>
  </div>

  <div id="content">
  <pre>
  ${param.content}
  </pre>
  </div>
  
  <div id="footer">
  &copy;2013 dogrun inc.
  </div>

  </body>
</html>