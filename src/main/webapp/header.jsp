<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">


<!-- Static navbar -->
<nav class="navbar navbar-expand-lg navbar-light " style="background-color: #e3f2fd;">
	<a class="navbar-brand" href="${pageContext.request.contextPath}/home.jsp">Gestione Municipio Abitante</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="collapse navbar-collapse" id="navbarSupportedContent">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active"><a class="nav-link" href="${pageContext.request.contextPath}/home.jsp">Home
					<span class="sr-only">(current)</span>
			</a></li>
			<li class="nav-item dropdown"><a
				class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
				role="button" data-toggle="dropdown" aria-haspopup="true"
				aria-expanded="false"> Dropdown </a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdown">
					<a class="dropdown-item" href="#">Action</a> <a
						class="dropdown-item" href="#">Another action</a>
					<div class="dropdown-divider"></div>
					<a class="dropdown-item" href="#">Something else here</a>
				</div></li>
		</ul>
		 <ul class="nav navbar-nav navbar-right">
            <li><p class="navbar-text">Utente: ${userInfo.username }(${userInfo.nome } ${userInfo.cognome })
            <a href="${pageContext.request.contextPath}/LogoutServlet">Logout</a></p> 
            </li>
          </ul>
	</div>
</nav>




<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="${pageContext.request.contextPath}/js/jquery-1.10.2.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.js"></script>
<script	src="${pageContext.request.contextPath}/js/jqueryUI/jquery-ui.min.js"></script>
