<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca Ingrediente</title>
</head>
<body>

<div class="container">

   <%@ include file="../../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Ricerca Ingredienti</h3>
	</div>
	
	<div class="alert alert-success ${messaggioConferma==null?'d-none':''}" role="alert">
	  ${messaggioConferma }
	</div>

      	<form class="form-horizontal" action="${pageContext.request.contextPath}/pizzaiolo/ingredienti/ExecuteSearchIngredienteServlet" method="post">
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="codiceInputId">Codice:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="codiceInputId" name="codiceInput" >
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="descrizioneInputId">Descrizione:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="descrizioneInputId" name="descrizioneInput" >
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="prezzoInputId">Prezzo:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type='number' step='0.01' id="prezzoInputId" name="prezzoInput" >
			 	</div>
  			</div>
  			
  			
  			
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Effettua Ricerca</button>
		        <a href="${pageContext.request.contextPath}/pizzaiolo/ingredienti/PrepareInsertIngredienteServlet" class="btn btn-primary btn-md">Inserisci Nuovo Ingrediente</a>
		      </div>
		    </div>
		</form>
		
		
		<%@ include file="../../footer.jsp"%>
    </div><!-- /.container -->



</body>
</html>