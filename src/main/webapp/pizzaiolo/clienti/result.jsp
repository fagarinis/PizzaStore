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
			<h3>Risultato Ricerca Cliente</h3>
		</div>

		<%-- alert conferma --%>
		<div
			class="alert alert-success ${messaggioConferma!=null?'':'d-none' }"
			role="alert">${messaggioConferma }</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Nome</th>
					<th>Cognome</th>
					
					<th>Action</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="clienteItem" items="${listaClientiAttr }">
					<tr>
						<td>
							<c:if test="${!clienteItem.isAttivo()}"><del>${clienteItem.nome }</del></c:if>
							<c:if test="${clienteItem.isAttivo()}">${clienteItem.nome }</c:if>
						</td>
						<td>
							<c:if test="${!clienteItem.isAttivo()}"><del>${clienteItem.cognome }</del></c:if>
							<c:if test="${clienteItem.isAttivo()}">${clienteItem.cognome }</c:if>
						</td>
						<td><a
							href="${pageContext.request.contextPath}/pizzaiolo/clienti/ExecuteDettaglioClienteServlet?idCliente=${clienteItem.id}"
							class="btn btn-info">Dettaglio</a> <a
							href="${pageContext.request.contextPath}/pizzaiolo/clienti/PrepareModificaClienteServlet?idCliente=${clienteItem.id}"
							class="btn btn-info">Modifica</a> <a
							href="${pageContext.request.contextPath}/pizzaiolo/clienti/PrepareEliminaClienteServlet?idCliente=${clienteItem.id}"
							class="btn btn-info">Elimina</a>
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
		
				<!-- Nuova ricerca -->
				<a	href="${pageContext.request.contextPath}/pizzaiolo/clienti/PrepareSearchClienteServlet" class="btn btn-primary btn-md">Nuova Ricerca</a>
				<!-- Inserisci nuovo -->
		        <a href="${pageContext.request.contextPath}/pizzaiolo/clienti/PrepareInsertClienteServlet" class="btn btn-primary btn-md">Inserisci Nuovo Cliente</a>
		      
		      </div>
		</div>

		<%@ include file="../../footer.jsp"%>

	</div>
</body>
</html>