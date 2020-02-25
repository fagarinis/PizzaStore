<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Report Ordini</title>
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/favicon.ico"
	type="image/x-icon" />
</head>
<body>

	<div class="container">

		<%@ include file="../../header.jsp"%>
		<div class="page-header">
			<h3>Risultati Ricerca Report Ordini</h3>
		</div>

		<%-- alert conferma --%>
		<div
			class="alert alert-success ${messaggioConferma!=null?'':'d-none' }"
			role="alert">${messaggioConferma }</div>
			
		<%-- alert Errore --%>
		<div
			class="alert alert-success ${messaggioErrore!=null?'':'d-none' }"
			role="alert">${messaggioErrore }</div>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>Codice</th>
					<th>Costo Totale</th>
					<th>Stato Ordine</th>
					
					<th>Action</th>
				</tr>
			</thead>
			
			<tbody>
			
				<c:forEach var="ordineItem" items="${listaOrdiniAttr }">
					<tr>
						<td>${ordineItem.codice }</td>
						<td>${ordineItem.costoTotale } &euro;</td>
						<td>${ordineItem.isClosed()? 'Chiuso' : 'Aperto' }</td>
						<td><a
							href="${pageContext.request.contextPath}/pizzaiolo/ordini/ExecuteDettaglioOrdineServlet?idOrdine=${ordineItem.id}"
							class="btn btn-info">Dettaglio</a> 
							
					</tr>
				</c:forEach>
			</tbody>

		</table>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
		
				<!-- Nuova ricerca -->
				<a	href="${pageContext.request.contextPath}/admin/reportordini/PrepareSearchOrdineReportServlet" class="btn btn-primary btn-md">Nuova Ricerca</a>
		      
		      </div>
		</div>

		<%@ include file="../../footer.jsp"%>

	</div>
</body>
</html>