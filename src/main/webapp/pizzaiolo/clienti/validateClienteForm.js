function validateClienteForm() {
	var tagNome = document.getElementById("nomeInputId");
	var tagCognome = document.getElementById("cognomeInputId");
	var tagVia = document.getElementById("viaInputId");
	var tagCivico = document.getElementById("civicoInputId");
	var tagCitta = document.getElementById("cittaInputId");
	var tagTelefono = document.getElementById("telefonoInputId");

	if (isEmpty(tagNome.value)) {
		tagNome.focus();
		return false;
	}

	if (isEmpty(tagCognome.value)) {
		tagCognome.focus();
		return false;
	}

	if (isEmpty(tagVia.value)) {
		tagVia.focus();
		return false;
	}

	if (isEmpty(tagCivico.value)) {
		tagCivico.focus();
		return false;
	}

	if (isEmpty(tagCitta.value)) {
		tagCitta.focus();
		return false;
	}

	if (isEmpty(tagTelefono.value)) {
		tagTelefono.focus();
		return false;
	}

}

function isEmpty(str) {
	return (str.length === 0 || !str.trim());
}