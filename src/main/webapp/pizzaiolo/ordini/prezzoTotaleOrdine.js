function updatePrezzoTotaleOrdine() {
	tagPrezzoTotale = document.getElementById("prezzoTotaleInputId");
	
	if(tagPrezzoTotale == null ){
		return;
	}

	var prezzoTotale = 0;

	checkedIngredienti = getCheckedBoxes("pizzaInput");
	for (i = 0; i < checkedIngredienti.length; i++) {
		ingredientePrezzo = parseFloat($(checkedIngredienti[i]).data('prezzo'));
		prezzoTotale += ingredientePrezzo;
	}

	tagPrezzoTotale.value = prezzoTotale.toFixed(2);

	return tagPrezzoTotale.value;
}

//svuota i campi di cliente se si modifica la selezione
function svuotaCampiCliente(){
	//campo hidden Id
	clienteId = document.getElementById("clienteId");
	//campo hidden descrizione
	clienteNome = document.getElementById("clienteNomeId")
	
	//campo visibile scelto
	clienteInput = document.getElementById("clienteInputId")
	
	
	console.log(clienteNome.value)
	
	if(clienteNome.value != ''){
		if(clienteInput.value != clienteNome.value){
			clienteId.value = '';
			clienteInput.value = '';
			clienteNome.value = '';
			console.log("elimina id memorizzato");
			return false;
		}
	}
	return false;
 }


function getCheckedBoxes(chkboxName) {
	var checkboxes = document.getElementsByName(chkboxName);
	var checkboxesChecked = [];
	// loop over them all
	for (var i = 0; i < checkboxes.length; i++) {
		// And stick the checked ones onto an array...
		if (checkboxes[i].checked) {
			checkboxesChecked.push(checkboxes[i]);
		}
	}
	// Return the array if it is non-empty, or null
	return checkboxesChecked.length > 0 ? checkboxesChecked : [];
}


$( "#clienteInputId" ).autocomplete({
	 source: function(request, response) {
	        $.ajax({
	            url: "SearchClienteAjaxServlet",
	            datatype: "json",
	            data: {
	                term: request.term,   
	            },
	            success: function(data) {
	                response($.map(data, function(item) {
	                    return {
	                    	value: item.value, //id
	                    	label: item.label +" "+item.name //cognome
		                    
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


// aggiornamento del prezzo totale quando carica la pagina
$(document).ready(function() {

	document.getElementById('dataInputId').value = new Date().toISOString().substring(0, 10);
	return updatePrezzoTotaleOrdine();
});