<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dettaglio Ingrediente</title>
</head>
<body>
	<div class="container">
		<%@ include file="../../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Dettaglio</h3>
		</div>

		<div class="container-fluid">
		
				<dl class="row">
					<dt class="col-sm-3 text-right">Id</dt>
					<dd class="col-sm-9">${pizzaAttr.id }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Codice</dt>
					<dd class="col-sm-9">${pizzaAttr.codice }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Descrizione</dt>
					<dd class="col-sm-9">${pizzaAttr.descrizione }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Prezzo Base</dt>
					<dd class="col-sm-9">${pizzaAttr.prezzoBase } &euro;</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Prezzo Totale</dt>
					<dd class="col-sm-9">${pizzaPrezzoTotaleAttr } &euro;</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Ingredienti</dt>
					<dd class="col-sm-9">
					<c:forEach var="ingredienteItem" items="${pizzaAttr.ingredienti }">
						<li>${ingredienteItem.descrizione }<br>
					</c:forEach>
					
					</dd>
				</dl>
				

			<a href="javascript:history.back()" class="btn btn-primary btn-md">Torna
				Indietro</a>

			<%@ include file="../../footer.jsp"%>
		</div>
	</div>

</body>
</html>