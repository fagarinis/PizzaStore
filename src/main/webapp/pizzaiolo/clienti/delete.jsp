<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Cancella Cliente</title>
</head>
<body>
	<div class="container">
	
		<%@ include file="../../header.jsp"%>

		<div class="page-header">
			<h3>Pagina di Cancellazione</h3>
		</div>


		<form  action="${pageContext.request.contextPath}/pizzaiolo/clienti/ExecuteEliminaClienteServlet" method="post">
			<div class="container-fluid">
			
			<dl class="row">
				<dt class="col-sm-3 text-right">Id</dt>
				<dd class="col-sm-9">${clienteAttr.id }</dd>
			</dl>
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
			</div>

			<input type=hidden name="idCliente" value="${clienteAttr.id }">
			
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