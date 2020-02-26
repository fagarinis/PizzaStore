function validatePizzaForm() {
	var tagCodice = document.getElementById("codiceInputId");
	var tagDescrizione = document.getElementById("descrizioneInputId");
	var tagPrezzoBase = document.getElementById("prezzoBaseInputId");

	if (isEmpty(tagCodice.value)) {
		tagCodice.focus();
		return false;
	}
	
	if (isEmpty(tagDescrizione.value)) {
		tagDescrizione.focus();
		return false;
	}
	
	if (isEmpty(tagPrezzoBase.value) || parseInt(tagPrezzoBase.value) < 0) {
		tagPrezzoBase.focus();
		return false;
	}
}

function isEmpty(str) {
	return (str.length === 0 || !str.trim());
}