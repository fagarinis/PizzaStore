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
				<dt class="col-sm-3 text-right">Nome</dt>
				<dd class="col-sm-9">${clienteAttr.nome }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Cognome</dt>
				<dd class="col-sm-9">${clienteAttr.cognome }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Via</dt>
				<dd class="col-sm-9">${clienteAttr.via }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Civico</dt>
				<dd class="col-sm-9">${clienteAttr.civico }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Città</dt>
				<dd class="col-sm-9">${clienteAttr.citta }</dd>
			</dl>
			<dl class="row">
				<dt class="col-sm-3 text-right">Telefono</dt>
				<dd class="col-sm-9">${clienteAttr.telefono }</dd>
			</dl>

			<a href="javascript:history.back()" class="btn btn-primary btn-md">Torna
				Indietro</a>

			<%@ include file="../../footer.jsp"%>
		</div>
	</div>

</body>
</html>