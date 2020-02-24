<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci Nuovo Ordine</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jqueryUI/jquery-ui.min.css" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<div class="container">

   <%@ include file="../../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Inserimento Ordine</h3>
	</div>
	
	<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty ordineErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${ordineErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>


		<!-- da implementare onsubmit ="return validateIngredienteForm()" -->
      	<form  class="form-horizontal" action="${pageContext.request.contextPath}/pizzaiolo/ordini/ExecuteInsertOrdineServlet" method="post">
      	
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="codiceInputId">Codice:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="codiceInputId" name="codiceInput" 
					value = "${ordineAttr.codice}">
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="dataInputId">Data:</label>
	    		<div class="col-sm-4">
					<input readonly class="form-control" type="date" id="dataInputId" name="dataInput" 
					value = "${ordineAttr.data}">
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="prezzoTotaleInputId">Prezzo Totale:</label>
	    		<div class="col-sm-4">
					<input readonly class="form-control" type='number' step='0.01' id="prezzoTotaleInputId" name="prezzoTotaleInput" >
			 	</div>
  			</div>
  			
  			
  			<div class="form-group">
				<label class="control-label col-sm-2" for="clienteInputId">Cliente:</label>
				<div class="col-sm-4">
				<!-- variabili hidden dell'ultima selezione corretta di municipio -->
					<input readonly type="text" name="clienteId" id="clienteId" value ="${ordine.cliente.id}">
					<input readonly type="text" name="clienteNome" id="clienteNomeId" value ="${ordine.cliente.cognome} ${ordine.cliente.nome}">
				
				<!-- c'era oninput ="new svuotaCampiCliente()" sul tag input sotto-->
					<input oninput ="new svuotaCampiCliente()" class="form-control" type="text" id="clienteInputId"
						name="clienteInput" value ="">
						
				</div>
			</div>
  			
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="pizzeInputId">Pizze:</label>
	    		<div class="col-sm-4">
	    			<c:forEach var = "pizzaItem" items ="${pizzeListAttr}">
	    			
	    				<c:forEach var ="ordinePizzaItem" items ="${ordineAttr.pizze}">
	    					<c:if test="${pizzaItem.id == ordinePizzaItem.id}">
	    						<c:set var = "check" value ="yes"/>
	    					</c:if>
	    				</c:forEach>
	    			
						<input 
						onclick="updatePrezzoTotaleOrdine()" <c:if test="${check =='yes'}">checked="checked"</c:if> 
						type="checkbox" id="pizzaInputId" name="pizzaInput" data-prezzo="${pizzaItem.getPrezzo()}" 
						value ="${pizzaItem.id}"
						>  <input type="number" min="1" max="500" style="width: 3em;" id="numeroPizzaId" name="numeroPizza" >
						${pizzaItem.descrizione} +${pizzaItem.getPrezzo()} &euro; <br>
						<c:set var = "check" value ="no"/>
					</c:forEach>
			 	</div>
  			</div>
  			
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Conferma Inserimento</button>
		      </div>
		    </div>
		    
		</form>
		
		<%@ include file="../../footer.jsp"%>
		
    </div><!-- /.container -->

<script src="prezzoTotaleOrdine.js"></script>

</body>
</html>