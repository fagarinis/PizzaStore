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
					<th>Prezzo</th>
					
					<th>Action</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="ingredienteItem" items="${listaIngredientiAttr }">
					<tr>
						<td>${ingredienteItem.codice }</td>
						<td>${ingredienteItem.descrizione }</td>
						<td>${ingredienteItem.prezzo } &euro;</td>
						<td><a
							href="${pageContext.request.contextPath}/pizzaiolo/ingredienti/ExecuteDettaglioIngredienteServlet?idIngrediente=${ingredienteItem.id}"
							class="btn btn-info">Dettaglio</a> <a
							href="${pageContext.request.contextPath}/pizzaiolo/ingredienti/PrepareModificaIngredienteServlet?idIngrediente=${ingredienteItem.id}"
							class="btn btn-info">Modifica</a> <a
							href="${pageContext.request.contextPath}/pizzaiolo/ingredienti/PrepareEliminaIngredienteServlet?idIngrediente=${ingredienteItem.id}"
							class="btn btn-info">Elimina</a>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
		
				<!-- Nuova ricerca -->
				<a	href="${pageContext.request.contextPath}/pizzaiolo/ingredienti/PrepareSearchIngredienteServlet" class="btn btn-primary btn-md">Nuova Ricerca</a>
				<!-- Inserisci nuovo -->
		        <a href="${pageContext.request.contextPath}/pizzaiolo/ingredienti/PrepareInsertIngredienteServlet" class="btn btn-primary btn-md">Inserisci Nuovo Ingrediente</a>
		      
		      </div>
		</div>

		<%@ include file="../../footer.jsp"%>

	</div>
</body>
</html>