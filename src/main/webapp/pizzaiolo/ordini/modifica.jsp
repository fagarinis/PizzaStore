<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script src="validateOrdineForm.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica Ordine</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jqueryUI/jquery-ui.min.css" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<div class="container">

   <%@ include file="../../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Modifica Ordine</h3>
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
      	<form onsubmit="return validateOrdineForm()" class="form-horizontal" action="${pageContext.request.contextPath}/pizzaiolo/ordini/ExecuteModificaOrdineServlet" method="post">
      	
      		<input class="form-control" type="hidden" id="idInputId" name="idInput" 
					value = "${ordineAttr.id}">
      		
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="codiceInputId">Codice:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="codiceInputId" name="codiceInput" 
					value = "${ordineAttr.codice}">
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
						name="clienteInput" value ="${ordineAttr.cliente.cognome}<c:if test="${ordineAttr.cliente != null}"> </c:if>${ordineAttr.cliente.nome}">
						
				</div>
			</div>
			
			<div class="form-group">
				<label class="control-label col-sm-2" for="clienteInputId">Fattorino:</label>
				<div class="col-sm-4">
				<!-- variabili hidden dell'ultima selezione corretta di fattorino -->
					<input readonly type="hidden" name="fattorinoId" id="fattorinoId" value ="${ordineAttr.utente.id}">
					<input readonly type="hidden" name="fattorinoNome" id="fattorinoNomeId" value ="${ordineAttr.utente.cognome}<c:if test="${ordineAttr.utente != null}"> </c:if>${ordineAttr.utente.nome}">
				
				<!-- visibile -->
					<input oninput ="new svuotaCampiFattorino()" class="form-control" type="text" id="fattorinoInputId"
						name="fattorinoInput" value ="${ordineAttr.utente.cognome}<c:if test="${ordineAttr.utente != null}"> </c:if>${ordineAttr.utente.nome}">
						
				</div>
			</div>
			
			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="prezzoTotaleInputId">Prezzo Totale:</label>
	    		<div class="col-sm-4">
					<input readonly class="form-control" type='number' step='0.01' id="prezzoTotaleInputId" name="prezzoTotaleInput" >
			 	</div>
  			</div>
  			
  			<!-- Checkbox di pizze -->
  			<c:set var = "i" value = "${0}"/>
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
						onclick="updateInputNumberPizza('${i}'); updatePrezzoTotaleOrdine(); " <c:if test="${check =='yes'}">checked="checked"</c:if> 
						type="checkbox" id="pizzaInputId" name="pizzaInput" data-i ="${i}" data-prezzo="${pizzaItem.getPrezzo()}" 
						value ="${pizzaItem.id}"
						>  
						
						<input oninput="updateCheckboxPizza('${i}'); updatePrezzoTotaleOrdine();" data-i ="${i}" type="number" min="1"  pattern="[0-9]" style="width: 3em;" id="numeroPizzaId" name="numeroPizza" 
						value ="${ordineAttr.getNumeroPizzeConId(pizzaItem.id)}">
							${pizzaItem.descrizione} +${pizzaItem.getPrezzo()} &euro; <br>
							
						<c:set var="i" value="${i + 1}"/>
						<c:set var = "check" value ="no"/>
					</c:forEach>
			 	</div>
  			</div>
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Conferma Modifica Ordine</button>
		      </div>
		    </div>
		</form>
		
		<%@ include file="../../footer.jsp"%>
    </div><!-- /.container -->

<script src="prezzoTotaleOrdine.js"></script>

</body>
</html>