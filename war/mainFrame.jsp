<%@page pageEncoding="UTF-8" isELIgnored="false" session="true"%>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>${param.title}</title>
    <link type="text/css" rel="stylesheet" href="/css/global.css" />
    <link type="text/css" rel="stylesheet" href="/css/bootstrap.min.css" />
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/bootstrap.min.js"></script>
  </head>

  <body>

    <div class="navbar navbar-inverse navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="/">iLeaflet</a>
          <div class="nav-collapse collapse">
            <ul class="nav">
              <li class="active"><a href="/">Home</a></li>
              <li><a href="#about">About</a></li>
              <li><a href="#contact">Contact</a></li>
            </ul>

            <ul class="nav pull-right">
            
            <li>
<c:if test="${empty userName}">
            	<a href="/login/">ログイン</a>
</c:if>
<c:if test="${not empty userName}">
            	<a href="/dashboard/">${userName}</a>
</c:if>
            </li>
<c:if test="${not empty userName}">
            <li><a href="/login/logout">ログアウト</a></li>
</c:if>
            </ul>
          </div>
        </div>
      </div>
    </div>

    <div class="container-fluid">

<c:if test="${not empty errors}">

<div class="row-fluid">
  <div class="alert">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>Warning!</strong> 
    <ul>
      <c:forEach var="e" items="${f:errors()}">
      <li><span class="error">${f:h(e)}</span></li>
      </c:forEach>
    </ul>
  </div>
</div>

</c:if>

      <div class="row-fluid">
  		${param.content}
  
      </div>
    </div>

  <div id="footer">
  &copy;2013 dogrun inc.
  </div>

  </body>
</html>