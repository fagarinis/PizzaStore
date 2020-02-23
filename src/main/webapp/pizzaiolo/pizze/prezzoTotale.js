function updatePrezzoTotale() {
	tagPrezzoBase = document.getElementById("prezzoBaseInputId");
	tagPrezzoTotale = document.getElementById("prezzoTotaleInputId");
	
	if(tagPrezzoTotale == null || tagPrezzoBase == null ){
		return;
	}

	var prezzoTotale = tagPrezzoBase.value == ""? 0: parseFloat(tagPrezzoBase.value);

	checkedIngredienti = getCheckedBoxes("ingredienteInput");
	for (i = 0; i < checkedIngredienti.length; i++) {
		ingredientePrezzo = parseFloat($(checkedIngredienti[i]).data('prezzo'));
		prezzoTotale += ingredientePrezzo;
	}

	tagPrezzoTotale.value = prezzoTotale.toFixed(2);

	return tagPrezzoTotale.value;
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

// aggiornamento del prezzo totale quando carica la pagina
$(document).ready(function() {
	return updatePrezzoTotale();
});