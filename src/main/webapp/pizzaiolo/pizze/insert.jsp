<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>



<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inserisci Pizza</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/jqueryUI/jquery-ui.min.css" />
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<div class="container">

   <%@ include file="../../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Inserimento Pizza</h3>
	</div>
	
	<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty pizzaErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${pizzaErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>




		<!-- da implementare onsubmit ="return validateIngredienteForm()" -->
      	<form  class="form-horizontal" action="${pageContext.request.contextPath}/pizzaiolo/pizze/ExecuteInsertPizzaServlet" method="post">
      	
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="codiceInputId">Codice:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="codiceInputId" name="codiceInput" 
					value = "${pizzaAttr.codice}">
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="descrizioneInputId">Descrizione:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="descrizioneInputId" name="descrizioneInput" 
					value = "${pizzaAttr.descrizione}">
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="prezzoBaseInputId">Prezzo Base:</label>
	    		<div class="col-sm-4">
					<input onkeyup ="updatePrezzoTotale()" class="form-control" type='number' step='0.01'   id="prezzoBaseInputId" name="prezzoBaseInput" 
					value = "${pizzaAttr.prezzoBase}">
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="prezzoTotaleInputId">Prezzo Totale:</label>
	    		<div class="col-sm-4">
					<input readonly class="form-control" type='number' step='0.01'   id="prezzoTotaleInputId" name="prezzoTotaleInput" >
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="ingredientiInputId">Ingredienti:</label>
	    		<div class="col-sm-4">
	    			<c:forEach var = "ingredienteItem" items ="${ingredientiListAttr}">
	    			
	    				<c:forEach var ="pizzaIngredienteItem" items ="${pizzaAttr.ingredienti}">
	    					<c:if test="${ingredienteItem.id == pizzaIngredienteItem.id}">
	    						<c:set var = "check" value ="yes"/>
	    					</c:if>
	    				</c:forEach>
	    			
						<input onclick="updatePrezzoTotale()" <c:if test="${check =='yes'}">checked="checked"</c:if> type="checkbox" id="ingredienteInputId" name="ingredienteInput" data-prezzo="${ingredienteItem.prezzo}" value ="${ingredienteItem.id}"> ${ingredienteItem.descrizione} +${ingredienteItem.prezzo} &euro; 
						<br>
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

<script src="prezzoTotale.js"></script>

</body>
</html>