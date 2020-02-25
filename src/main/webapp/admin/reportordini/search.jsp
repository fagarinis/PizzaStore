<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca Report Ordini</title>
</head>
<body>

<div class="container">

   <%@ include file="../../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Ricerca Report Ordini</h3>
	</div>
	
	<div class="alert alert-success ${messaggioConferma==null?'d-none':''}" role="alert">
	  ${messaggioConferma }
	</div>

      	<form class="form-horizontal" action="${pageContext.request.contextPath}/admin/reportordini/ExecuteSearchReportOrdineServlet" method="post">
      		
			<div class="form-group">
      			<label class="control-label col-sm-2" for="dataInputId">Data:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="date" id="dataInputId" name="dataInput" >
			 	</div>
  			</div>
  			
  			<div class="form-group">
				<label class="control-label col-sm-2" for="clienteInputId">Cliente:</label>
				<div class="col-sm-4">
				<!-- variabili hidden dell'ultima selezione corretta di cliente -->
					<input readonly type="hidden" name="clienteId" id="clienteId" value ="${ordineAttr.cliente.id}">
					<input readonly type="hidden" name="clienteNome" id="clienteNomeId" value ="${ordineAttr.cliente.cognome}<c:if test="${ordineAttr.cliente != null}"> </c:if>${ordineAttr.cliente.nome}">
				
				<!-- visibile -->
					<input oninput ="new svuotaCampiCliente()" class="form-control" type="text" id="clienteInputId"
						name="clienteInput">
						
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="pizzaInputId">Pizza:</label>
				<div class="col-sm-4">
				<!-- variabili hidden dell'ultima selezione corretta di cliente -->
					<input readonly type="hidden" name="pizzaId" id="pizzaId" >
					<input readonly type="hidden" name="pizzaNome" id="pizzaNomeId">
				
				<!-- visibile -->
					<input oninput ="new svuotaCampiPizza()" class="form-control" type="text" id="pizzaInputId"
						name="pizzaInput">
						
				</div>
			</div>
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Effettua Ricerca Report</button>
		      </div>
		    </div>
		    
		    
		</form>
		
		
		<%@ include file="../../footer.jsp"%>
    </div><!-- /.container -->

<script src="autocomplete.js"></script>

</body>
</html>