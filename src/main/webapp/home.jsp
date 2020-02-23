<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home Page</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<div class="container">
 <%@ include file="header.jsp" %>
 
 	<%-- alert accesso non consentito --%>
		<div
			class="alert alert-danger ${messaggio!=null?'':'d-none' }"
			role="alert">${messaggio }</div>
   	
   	
   	<c:if test="${userInfo.isAdmin()}"> 
       	 <div class="jumbotron">
      		<div class="container">
       	 		<h1 class="display-4">(Admin) Gestione Utenze</h1>
       	 		<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/admin/PrepareSearchUtenteServlet" role="button">Vai alla Gestione &raquo;</a></p>
     	 	</div>
     	 </div>
     	 
     	 <div class="jumbotron">
      		<div class="container">
       	 		<h1 class="display-4">(Admin) Report Ordini</h1>
       	 		<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/admin/PrepareSearchOrdineServlet" role="button">Visualizza Report &raquo;</a></p>
     	 	</div>
     	 </div>
     	 
     </c:if>
     
     <c:if test="${userInfo.isPizzaiolo()}"> 
     
    	 <div class="jumbotron">
      		<div class="container">
       	 		<h1 class="display-4"> Gestione Ordini</h1>
       	 		<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/pizzaiolo/ordini/PrepareSearchOrdineServlet" role="button">Vai alla Gestione &raquo;</a></p>
     	 	</div>
     	 </div>
     
     	
       	 <div class="jumbotron">
      		<div class="container">
       	 		<h1 class="display-4"> Gestione Clienti</h1>
       	 		<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/pizzaiolo/clienti/PrepareSearchClienteServlet" role="button">Vai alla Gestione &raquo;</a></p>
     	 	</div>
     	 </div>
     	 
     	 <div class="jumbotron">
      		<div class="container">
       	 		<h1 class="display-4"> Gestione Pizze</h1>
       	 		<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/pizzaiolo/pizze/PrepareSearchPizzaServlet" role="button">Vai alla Gestione &raquo;</a></p>
     	 	</div>
     	 </div>
     	 
     	 <div class="jumbotron">
      		<div class="container">
       	 		<h1 class="display-4"> Gestione Ingredienti</h1>
       	 		<p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/pizzaiolo/ingredienti/PrepareSearchIngredienteServlet" role="button">Vai alla Gestione &raquo;</a></p>
     	 	</div>
     	 </div>
     	 
     	 
     	 <div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <a href="${pageContext.request.contextPath}/pizzaiolo/ordini/PrepareInsertOrdineServlet" class="btn btn-primary btn-md">Nuovo Ordine</a>
		      </div>
		 </div>
		 
     </c:if>



	 <%@ include file="footer.jsp" %>
</div>

</body>
</html>