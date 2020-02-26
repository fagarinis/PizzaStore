<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="validateClienteForm.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica Cliente</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<div class="container">

   <%@ include file="../../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Modifica Cliente</h3>
	</div>
	
	<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty clienteErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${clienteErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>

      	<form onsubmit="return validateClienteForm()" class="form-horizontal" action="${pageContext.request.contextPath}/pizzaiolo/clienti/ExecuteModificaClienteServlet" method="post">
      	
      		<input class="form-control" type="hidden" id="idInputId" name="idInput" 
					value = "${clienteAttr.id}">
      		
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="nomeInputId">Nome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="nomeInputId" name="nomeInput" 
					value="${clienteAttr.nome }">
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="cognomeInputId">Cognome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="cognomeInputId" name="cognomeInput" 
					value="${clienteAttr.cognome }">
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="viaInputId">Via:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="viaInputId" name="viaInput"
					value="${clienteAttr.via }">			 	
				</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="civicoInputId">Civico:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="civicoInputId" name="civicoInput"
					value="${clienteAttr.civico }">			 	
				</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="cittaInputId">Città:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="cittaInputId" name="cittaInput"
					 value="${clienteAttr.citta }">
				</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="telefonoInputId">Telefono:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="telefonoInputId" name="telefonoInput" 
					value="${clienteAttr.telefono }">
				</div>
  			</div>
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Conferma Modifiche</button>
		      </div>
		    </div>
		</form>
		
		<%@ include file="../../footer.jsp"%>
    </div><!-- /.container -->



</body>
</html>