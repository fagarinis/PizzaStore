<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<script>

function validateIngredienteForm() {
	tagNome = document.getElementById("nomeInputId");
	tagCognome = document.getElementById("cognomeInputId");
	tagUsername = document.getElementById("usernameInputId");
	tagPassword = document.getElementById("passwordInputId")

	if (isEmpty(tagNome.value)) {
		tagNome.focus();
		return false;
	}

	if (isEmpty(tagCognome.value)) {
		tagCognome.focus();
		return false;
	}

	if (isEmpty(tagUsername.value)) {
		tagUsername.focus();
		return false;
	}
	
	if (isEmpty(tagPassword.value)) {
		tagPassword.focus();
		return false;
	}
}

function isEmpty(str) {
	return (str.length === 0 || !str.trim());
}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Modifica Ingrediente</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<div class="container">

   <%@ include file="../../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Modifica Ingrediente</h3>
	</div>
	
	<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty ingredienteErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${ingredienteErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>

		<!-- da implementare onsubmit ="return validateIngredienteForm()" -->
      	<form  class="form-horizontal" action="${pageContext.request.contextPath}/pizzaiolo/ingredienti/ExecuteModificaIngredienteServlet" method="post">
      	
      		<input class="form-control" type="hidden" id="idInputId" name="idInput" 
					value = "${ingredienteAttr.id}">
      		
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="codiceInputId">Codice:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="codiceInputId" name="codiceInput" 
					value = "${ingredienteAttr.codice}">
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="descrizioneInputId">Descrizione:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="descrizioneInputId" name="descrizioneInput" 
					value = "${ingredienteAttr.descrizione}">
			 	</div>
  			</div>
  			
			<div class="form-group">
      			<label class="control-label col-sm-2" for="prezzoInputId">Prezzo:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type='number' step='0.01' id="prezzoInputId" name="prezzoInput" 
					value = "${ingredienteAttr.prezzo}">
			 	</div>
  			</div>
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Conferma</button>
		      </div>
		    </div>
		</form>
		
		<%@ include file="../../footer.jsp"%>
    </div><!-- /.container -->



</body>
</html>