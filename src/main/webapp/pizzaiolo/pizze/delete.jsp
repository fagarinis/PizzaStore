<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Cancella Pizza</title>
</head>
<body>
	<div class="container">
	
		<%@ include file="../../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Cancellazione</h3>
		</div>


		<form action="${pageContext.request.contextPath}/pizzaiolo/pizze/ExecuteEliminaPizzaServlet" method="post">
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
					<dd class="col-sm-9">${pizzaAttr.getPrezzo() } &euro;</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Ingredienti</dt>
					<dd class="col-sm-9">
					<c:forEach var="ingredienteItem" items="${pizzaAttr.ingredienti }">
						<li>${ingredienteItem.descrizione }<br>
					</c:forEach>
					
					</dd>
				</dl>
				
				<dl class="row">
					<dt class="col-sm-3 text-right">Disponibile</dt>
					<dd class="col-sm-9">${pizzaAttr.isAttivo()? 'Sì' : 'No' }</dd>
				</dl>


			<input type=hidden name="idPizza" value="${pizzaAttr.id }">
			
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
				<a href="javascript:history.back()" class="btn btn-primary btn-md">Torna
						Indietro</a>
					<button  type="submit" class="btn btn-primary btn-md">Conferma Cancellazione</button>
					
				</div>
			</div>
			<%@ include file="../../footer.jsp"%>
		</form>
	</div>

</body>
</html>