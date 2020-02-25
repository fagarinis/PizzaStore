<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Cancella Ordine</title>
</head>
<body>
	<div class="container">
	
		<%@ include file="../../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Cancellazione</h3>
		</div>


		<form action="${pageContext.request.contextPath}/pizzaiolo/ordini/ExecuteEliminaOrdineServlet" method="post">
			<div class="container-fluid">
			
				<dl class="row">
					<dt class="col-sm-3 text-right">Id</dt>
					<dd class="col-sm-9">${ordineAttr.id }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Codice</dt>
					<dd class="col-sm-9">${ordineAttr.codice }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Data</dt>
					<dd class="col-sm-9">${ordineAttr.data }</dd>
				</dl>
				<dl class="row">
					<dt class="col-sm-3 text-right">Costo Totale Ordine</dt>
					<dd class="col-sm-9">${ordineAttr.costoTotale } &euro;</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Cliente</dt>
					<dd class="col-sm-9">${ordineAttr.cliente.nome} ${ordineAttr.cliente.cognome }</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Fattorino</dt>
					<dd class="col-sm-9">${ordineAttr.utente.nome} ${ordineAttr.utente.cognome }</dd>
				</dl>
				
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Pizze</dt>
					<dd class="col-sm-9">
					<c:forEach var="pizzaItem" items="${ordineAttr.pizze }">
						<li>${pizzaItem.descrizione }<br>
					</c:forEach>
					
					</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Stato Ordine</dt>
					<dd class="col-sm-9">${ordineAttr.isClosed()? 'Chiuso' : 'Aperto' }</dd>
				</dl>

			</div>
			<input type=hidden name="idOrdine" value="${ordineAttr.id }">
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				<a href="javascript:history.back()" class="btn btn-primary btn-md">Torna
						Indietro</a>
					<button  type="submit" class="btn btn-primary btn-md">Conferma Cancellazione Ordine</button>
					
				</div>
			</div>
			<%@ include file="../../footer.jsp"%>
		</form>
	</div>

</body>
</html>