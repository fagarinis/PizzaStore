<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca Cliente</title>
</head>
<body>

<div class="container">

   <%@ include file="../../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Ricerca Cliente</h3>
	</div>
	
	<div class="alert alert-success ${messaggioConferma==null?'d-none':''}" role="alert">
	  ${messaggioConferma }
	</div>

      	<form class="form-horizontal" action="${pageContext.request.contextPath}/pizzaiolo/clienti/ExecuteSearchClienteServlet" method="post">
      		
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="nomeInputId">Nome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="nomeInputId" name="nomeInput" >
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="cognomeInputId">Cognome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="cognomeInputId" name="cognomeInput" >
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="viaInputId">Via:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="viaInputId" name="viaInput">			 	
				</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="civicoInputId">Civico:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="civicoInputId" name="civicoInput">			 	
				</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="cittaInputId">Città:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="cittaInputId" name="cittaInput">
				</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="telefonoInputId">Telefono:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="telefonoInputId" name="telefonoInput">
				</div>
  			</div>
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Effettua Ricerca</button>
		        <a href="${pageContext.request.contextPath}/pizzaiolo/clienti/PrepareInsertClienteServlet" class="btn btn-primary btn-md">Inserisci Nuovo Cliente</a>
		      </div>
		    </div>
		</form>
		
		
		<%@ include file="../../footer.jsp"%>
    </div><!-- /.container -->



</body>
</html>