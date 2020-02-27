function updatePrezzoTotaleOrdine() {
	tagPrezzoTotale = document.getElementById("prezzoTotaleInputId");
		
	if(tagPrezzoTotale == null ){
		return;
	}

	var prezzoTotale = 0;

	pizze = getCheckedBoxes("pizzaInput");

	for (i = 0; i < pizze.length; i++) {
		if(checkboxPizza(i).checked){
			pizzaPrezzo = checkboxPizza(i).getAttribute('data-prezzo');
			numeroPizze = parseInt(inputNumeroPizza(i).value);
			
			prezzoTotale += pizzaPrezzo * numeroPizze;
			
		}
		
		//ingredientePrezzo = parseFloat($(checkedIngredienti[i]).data('prezzo'));
		//prezzoTotale += ingredientePrezzo;
	}

	tagPrezzoTotale.value = prezzoTotale.toFixed(2);

	return tagPrezzoTotale.value;
}

function checkboxPizza(i){
	return document.getElementsByName("pizzaInput")[i];
}

function inputNumeroPizza(i){
	return document.getElementsByName("numeroPizza")[i];
}

//si attiva quando si clicca un checkbox di pizza
function updateInputNumberPizza(i){
	
	if(checkboxPizza(i).checked){
		if(isNaN(parseInt(inputNumeroPizza(i).value))|| parseInt(inputNumeroPizza(i).value) < 1 ){
			
			inputNumeroPizza(i).value = 1;
			return;
		}
	}
	else{
		inputNumeroPizza(i).value = "";
	}
	
}

//si attiva quando si modifica un input di numero pizze
function updateCheckboxPizza(i){
	
	number = parseInt(inputNumeroPizza(i).value);
	if(isNaN(number)){
		checkboxPizza(i).checked = false;
		inputNumeroPizza(i).value ="";
		return;
	}
	else{
		checkboxPizza(i).checked = true;
		return;
	}
	
	
}

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

//svuota i campi di cliente se si modifica la selezione
function svuotaCampiFattorino(){
	//campo hidden Id
	fattorinoId = document.getElementById("fattorinoId");
	//campo hidden descrizione
	fattorinoNome = document.getElementById("fattorinoNomeId")
	
	//campo visibile scelto
	fattorinoInput = document.getElementById("fattorinoInputId")
	
	if(fattorinoNome.value != ''){
		if(fattorinoInput.value != fattorinoNome.value){
			fattorinoId.value = '';
			fattorinoInput.value = '';
			fattorinoNome.value = '';
			return false;
		}
	}
	return false;
 }

//prende tutti i tag con lo stesso nome
function getCheckedBoxes(chkboxName) {
	var checkboxes = document.getElementsByName(chkboxName);
	var checkboxesChecked = [];
	// loop over them all
	for (var i = 0; i < checkboxes.length; i++) {
		// And stick the checked ones onto an array...
		//if (checkboxes[i].checked) {
			checkboxesChecked.push(checkboxes[i]);
		//}
	}
	// Return the array if it is non-empty, or null
	return checkboxesChecked.length > 0 ? checkboxesChecked : [];
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


//autocomplete di fattorino
$( "#fattorinoInputId" ).autocomplete({
	 source: function(request, response) {
	        $.ajax({
	            url: "SearchUtenteAjaxServlet",
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
      $("#fattorinoInputId").val(ui.item.label);
      
      $('#fattorinoId').val(ui.item.value);
     	$('#fattorinoNomeId').val(ui.item.label);
      
      //console.log($('#clienteInputId').val());
      return false;
  },
  minLength: 1,
  //quando seleziono la voce nel campo hidden deve valorizzarsi l'id
  select: function( event, ui ) {
  	$('#fattorinoId').val(ui.item.value);
  	$('#fattorinoNomeId').val(ui.item.label);
  	//console.log($('#clienteId').val());
      return false;
  },
 
});



// aggiornamento del prezzo totale quando carica la pagina
$(document).ready(function() {
	updatePrezzoTotaleOrdine();

	if(document.getElementById('dataInputId') != null){
		document.getElementById('dataInputId').value = new Date().toISOString().substring(0, 10);
	}
	
	return 
});