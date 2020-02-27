//svuota i campi di cliente se si modifica la selezione
function svuotaCampiCliente(){
	//campo hidden Id
	clienteId = document.getElementById("clienteId");
	//campo hidden descrizione
	clienteNome = document.getElementById("clienteNomeId")
	
	//campo visibile scelto
	clienteInput = document.getElementById("clienteInputId")
	
	if(clienteNome.value != ''){
		if(clienteInput.value != clienteNome.value){
			clienteId.value = '';
			clienteInput.value = '';
			clienteNome.value = '';
			return false;
		}
	}
	return false;
 }


//autocomplete di cliente
$( "#clienteInputId" ).autocomplete({
	 source: function(request, response) {
	        $.ajax({
	            url: "/PizzaStore/SearchClienteAjaxServlet",
	            datatype: "json",
	            data: {
	                term: request.term,   
	            },
	            success: function(data) {
	                response($.map(data, function(item) {
	                    return {
	                    	value: item.value, //id
	                    	label: item.name +" "+item.label //cognome
		                    
	                    }
	                }))
	            }
	        })
	    },
	//quando seleziono la voce nel campo deve valorizzarsi la descrizione
   focus: function(event, ui) {
       $("#clienteInputId").val(ui.item.label);
       
       $('#clienteId').val(ui.item.value);
      	$('#clienteNomeId').val(ui.item.label);
       
       //console.log($('#clienteInputId').val());
       return false;
   },
   minLength: 1,
   //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
   select: function( event, ui ) {
   	$('#clienteId').val(ui.item.value);
   	$('#clienteNomeId').val(ui.item.label);
   	//console.log($('#clienteId').val());
       return false;
   },
  
});




//svuota i campi di cliente se si modifica la selezione
function svuotaCampiPizza(){
	//campo hidden Id
	pizzaId = document.getElementById("pizzaId");
	//campo hidden descrizione
	pizzaNome = document.getElementById("pizzaNomeId")
	
	//campo visibile scelto
	pizzaInput = document.getElementById("pizzaInputId")
	
	if(pizzaNome.value != ''){
		if(pizzaInput.value != pizzaNome.value){
			pizzaId.value = '';
			pizzaInput.value = '';
			pizzaNome.value = '';
			return false;
		}
	}
	return false;
 }


//autocomplete di pizza
$( "#pizzaInputId" ).autocomplete({
	 source: function(request, response) {
	        $.ajax({
	            url: "/PizzaStore/SearchPizzaAjaxServlet",
	            datatype: "json",
	            data: {
	                term: request.term,   
	            },
	            success: function(data) {
	                response($.map(data, function(item) {
	                    return {
	                    	value: item.value, //id
	                    	label: item.label //descrizione pizza
	                    }
	                }))
	            }
	        })
	    },
	//quando seleziono la voce nel campo deve valorizzarsi la descrizione
   focus: function(event, ui) {
       $("#pizzaInputId").val(ui.item.label);
       
       $('#pizzaId').val(ui.item.value);
      	$('#pizzaNomeId').val(ui.item.label);
       
       return false;
   },
   minLength: 1,
   //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
   select: function( event, ui ) {
   	$('#pizzaId').val(ui.item.value);
   	$('#pizzaNomeId').val(ui.item.label);
       return false;
   },
  
});