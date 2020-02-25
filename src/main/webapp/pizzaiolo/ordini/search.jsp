<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ricerca Ordini</title>
</head>
<body>

<div class="container">

   <%@ include file="../../header.jsp" %>
      
    <div class="page-header">
	  <h3>Pagina di Ricerca Ordini</h3>
	</div>
	
	<div class="alert alert-success ${messaggioConferma==null?'d-none':''}" role="alert">
	  ${messaggioConferma }
	</div>

      	<form class="form-horizontal" action="${pageContext.request.contextPath}/pizzaiolo/ordini/ExecuteSearchOrdineServlet" method="post">
      		
      		<div class="form-group">
      			<label class="control-label col-sm-2" for="codiceInputId">Codice:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="codiceInputId" name="codiceInput" >
			 	</div>
  			</div>
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="costoTotaleInputId">Costo Totale:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="text" id="costoTotaleInputId" name="costoTotaleInput" >
			 	</div>
  			</div>
			<div class="form-group">
      			<label class="control-label col-sm-2" for="dataInputId">Data:</label>
	    		<div class="col-sm-4">
					<input class="form-control" type="date" id="dataInputId" name="dataInput" >
			 	</div>
  			</div>
  			
  			<div class="form-group">
      			<label class="control-label col-sm-2" for="chiusoInputId">Stato Ordine:</label>
	    		<div class="col-sm-4">
	    		<select name = chiusoInput id = "chiusoInputId">
	    			<option selected></option>
	    			<option value = "false"> Aperto</option>
	    			<option value = "true"> Chiuso</option>
	    		</select>
			 	</div>
  			</div>
  			
  			
  			<div class="form-group">        
		      <div class="col-sm-offset-2 col-sm-10">
		        <button type="submit" class="btn btn-primary btn-md">Effettua Ricerca</button>
		        <a href="${pageContext.request.contextPath}/pizzaiolo/ordini/PrepareInsertOrdineServlet" class="btn btn-primary btn-md">Nuovo Ordine</a>
		      </div>
		    </div>
		</form>
		
		
		<%@ include file="../../footer.jsp"%>
    </div><!-- /.container -->



</body>
</html>