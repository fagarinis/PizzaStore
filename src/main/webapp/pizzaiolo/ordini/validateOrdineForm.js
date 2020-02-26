function validateOrdineForm() {
	var tagCodice = document.getElementById("codiceInputId");
	var tagClienteId = document.getElementById("clienteId");
	var tagUtenteId = document.getElementById("fattorinoId");
	var checkedPizze = getCheckedBoxesPizza("pizzaInput");
	

	if (isEmpty(tagCodice.value)) {
		tagCodice.focus();
		return false;
	}

	if (isEmpty(tagClienteId.value)) {
		document.getElementById("clienteInputId").focus();
		return false;
	}

	if (isEmpty(tagUtenteId.value)) {
		document.getElementById("fattorinoInputId").focus();
		return false;
	}
	
	if(checkedPizze.length == 0){
		return false;
	}
	

}


function isEmpty(str) {
	return (str.length === 0 || !str.trim());
}

function getCheckedBoxesPizza(chkboxName) {
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