<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>

function validateUtenteForm() {
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
<title>Inserisci Municipio</title>
<link rel="shortcut icon" href="${pageContext.request.contextPath}/favicon.ico" type="image/x-icon" />
</head>
<body>

<div class="container">

   <%@ include file="../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Inserimento Utente</h3>
	</div>
	
	<%-- alert con lista errori --%>
	<div class="alert alert-danger ${not empty utenteErrors?'':'d-none' }" role="alert">
		<c:forEach var = "errorItem" items="${utenteErrors }">
        	<ul>
				<li> ${errorItem }</li>	
			</ul>
      	</c:forEach>
	</div>





      	<form onsubmit ="return validateUtenteForm()" class="form-horizontal" action="${pageContext.request.contextPath}/admin/ExecuteInsertUtenteServlet" method="post">
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="nomeInputId">Nome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="nomeInputId" name="nomeInput" 
					value = "${utenteAttr.nome}">
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="cognomeInputId">Cognome:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="cognomeInputId" name="cognomeInput" 
					value = "${utenteAttr.cognome}">
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="usernameInputId">Username:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="usernameInputId" name="usernameInput" 
					value = "${utenteAttr.username}">
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="passwordInputId">Password:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="password" id="passwordInputId" name="passwordInput" 
					value = "${utenteAttr.password}">
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="ruoliInputId">Ruoli:</label>
	    		<div class="col-sm-4">
	    			<c:forEach var = "ruoloItem" items ="${ruoliListAttr}">
	    			
	    				<c:forEach var ="utenteRuoloItem" items ="${utenteAttr.ruoli}">
	    					<c:if test="${ruoloItem.id == utenteRuoloItem.id}">
	    						<c:set var = "check" value ="yes"/>
	    					</c:if>
	    				</c:forEach>
	    			
						<input <c:if test="${check =='yes'}">checked="checked"</c:if> type="checkbox" id="ruoloInputId" name="ruoloInput" value ="${ruoloItem.id}"> ${ruoloItem.descrizione} 
						
						<c:set var = "check" value ="no"/>
					</c:forEach>
			 	</div>
  			</div>
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Conferma</button>
		      </div>
		    </div>
		</form>
		
    </div><!-- /.container -->



</body>
</html>