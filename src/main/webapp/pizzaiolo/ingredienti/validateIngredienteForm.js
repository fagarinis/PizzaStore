function validateIngredienteForm() {
	var tagCodice = document.getElementById("codiceInputId");
	var tagDescrizione = document.getElementById("descrizioneInputId");
	var tagPrezzo = document.getElementById("prezzoInputId");

	if (isEmpty(tagCodice.value)) {
		tagCodice.focus();
		return false;
	}

	if (isEmpty(tagDescrizione.value)) {
		tagDescrizione.focus();
		return false;
	}

	if (isEmpty(tagPrezzo.value) || parseInt(tagPrezzo.value) < 0) {
		tagPrezzo.focus();
		return false;
	}

}

function isEmpty(str) {
	return (str.length === 0 || !str.trim());
}