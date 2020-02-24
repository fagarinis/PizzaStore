<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Risultati Ricerca</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
</head>
<body>

	<div class="container">

		<%@ include file="../../header.jsp"%>
		<div class="page-header">
			<h3>Pagina dei Risultati</h3>
		</div>

		<%-- alert conferma --%>
		<div
			class="alert alert-success ${messaggioConferma!=null?'':'d-none' }"
			role="alert">${messaggioConferma }</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Codice</th>
					<th>Descrizione</th>
					<th>Disponibilità</th>
					
					<th>Action</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="pizzaItem" items="${listaPizzeAttr }">
					<tr>
						<td>${pizzaItem.codice }</td>
						<td>${pizzaItem.descrizione }</td>
						<td>${pizzaItem.isAttivo()? 'Disponibile' : 'Non Disponibile' }</td>
						<td><a
							href="${pageContext.request.contextPath}/pizzaiolo/pizze/ExecuteDettaglioPizzaServlet?idPizza=${pizzaItem.id}"
							class="btn btn-info">Dettaglio</a> <a
							href="${pageContext.request.contextPath}/pizzaiolo/pizze/PrepareModificaPizzaServlet?idPizza=${pizzaItem.id}"
							class="btn btn-info">Modifica</a> <a
							href="${pageContext.request.contextPath}/pizzaiolo/pizze/PrepareEliminaPizzaServlet?idPizza=${pizzaItem.id}"
							class="btn btn-info">Elimina</a>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
		
				<!-- Nuova ricerca -->
				<a	href="${pageContext.request.contextPath}/pizzaiolo/pizze/PrepareSearchPizzaServlet" class="btn btn-primary btn-md">Nuova Ricerca</a>
				<!-- Inserisci nuovo -->
		        <a href="${pageContext.request.contextPath}/pizzaiolo/pizze/PrepareInsertPizzaServlet" class="btn btn-primary btn-md">Inserisci Nuova Pizza</a>
		      
		      </div>
		</div>

		<%@ include file="../../footer.jsp"%>

	</div>
</body>
</html>