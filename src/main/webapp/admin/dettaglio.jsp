<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dettaglio Utente</title>
</head>
<body>
	<div class="container">
		<%@ include file="../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Dettaglio</h3>
		</div>

		<div class="container-fluid">
			<dl class="row">
				<dt class="col-sm-3 text-right">Nome</dt>
				<dd class="col-sm-9">${utenteAttr.nome }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Cognome</dt>
				<dd class="col-sm-9">${utenteAttr.cognome }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Username</dt>
				<dd class="col-sm-9">${utenteAttr.username }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Data Registrazione</dt>
				<dd class="col-sm-9">${utenteAttr.dataRegistrazione }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Stato</dt>
				<dd class="col-sm-9">${utenteAttr.stato}</dd>
			</dl>

			<dl class="row">
				<dt class="col-sm-3 text-right">Ruoli</dt>
				<dd class="col-sm-9"><c:forEach var="ruoloItem" items="${utenteAttr.ruoli}">
	    			${ruoloItem.descrizione} 
	    		</c:forEach></dd>

				



			</dl>




			<a href="javascript:history.back()" class="btn btn-primary btn-md">Torna
				Indietro</a>

			<%@ include file="../footer.jsp"%>
		</div>
	</div>

</body>
</html>