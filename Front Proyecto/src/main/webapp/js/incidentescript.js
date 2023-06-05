//post nuevo incidente 
formularioincidente.addEventListener('input', () => {
	if (camponombrei.value.length > 0
		&& campoempresa.value.length > 0
		&& campofocurrencia.value.length > 0
		&& campotiempoafectacion.length > 0
		&& camponpersonasafectadas.value.length > 0
		&& campolocalidad.value.length > 0
		&& campoagente.value.length > 0
		&& camponombre.value.length > 0
		&& campocedula.value.length > 0
		&& campofnacimiento.value.length > 0
		&& campofentradainstit.value.length > 0
		&& camporangoactual.value.length > 0
		&& camponcasosatendidos.value.length > 0) {
		enviar.removeAttribute('disabled');
	} else {
		enviar.setAttribute('disabled', 'disabled');
	}
});

// validar campos nuevo agente
function validarCampos() {
	// Post a user
	var url = "http://localhost:8082/miguelapi/incidente";
	var ecasonombre = '?nombredelcaso=' + document.getElementById("camponombrei").value;
	var enombre = '&empresaafectada=' + document.getElementById("campoempresa").value;
	var etiempoafec = '&tiempodeafectacion=' + document.getElementById("campotiempoafectacion").value;
	var ecantpersafect = '&cantpersonasafectadas=' + document.getElementById("camponpersonasafectadas").value;
	var efocurrencia = '&focurrencia=' + document.getElementById("campofocurrencia").value;
	var ebarriohechos = '&barriodeloshechos=' + document.getElementById("campolocalidad").value;
	var enagentes = '&nagentesenelcaso=' + document.getElementById("campoagente").value;

	var nombre = '&nombre=' + document.getElementById("camponombre").value;
	var cedula = '&cedula=' + document.getElementById("campocedula").value;;
	var fnacimiento = '&fnacimiento=' + document.getElementById("campofnacimiento").value;
	var sexo = '&sexo=';
	if (camposexo.checked == true) {
		sexo = sexo + document.getElementById("camposexo").value;

	} else if (camposexo2.checked == true) {
		sexo = sexo + document.getElementById("camposexo2").value;

	}
	var fentrinstit = '&fentrinstit=' + document.getElementById("campofentradainstit").value;
	var rangoactual = '&rangoactual=' + document.getElementById("camporangoactual").value;
	var ncasosatendidos = '&ncasosatendidos=' + document.getElementById("camponcasosatendidos").value;



	var data = {};

	data.ecasonombre = document.getElementById("camponombrei").value;
	data.enombre = document.getElementById("campoempresa").value;
	data.etiempoafec = document.getElementById("campotiempoafectacion").value;
	data.ecantpersafect = document.getElementById("camponpersonasafectadas").value;
	data.efocurrencia = document.getElementById("campofocurrencia").value;
	data.ebarriohechos = document.getElementById("campolocalidad").value;
	data.enagentes = document.getElementById("campoagente").value;

	data.nombre = document.getElementById("camponombre").value;
	data.cedula = document.getElementById("campocedula").value;
	data.fnacimiento = document.getElementById("campofnacimiento").value;
	if (camposexo.checked == true) {
		data.sexo = document.getElementById("camposexo").value;

	} else if (camposexo2.checked == true) {
		data.sexo = document.getElementById("camposexo2").value;
	}
	data.fentrinstit = document.getElementById("campofentradainstit").value;
	data.camporangoactual = document.getElementById("camporangoactual").value;
	data.camponcasosatendidos = document.getElementById("camponcasosatendidos").value;
	var json = JSON.stringify(data);

	var xhr = new XMLHttpRequest();
	xhr.open("POST", url + ecasonombre + enombre + etiempoafec + ecantpersafect + efocurrencia + ebarriohechos + enagentes + nombre + cedula + fnacimiento + sexo + fentrinstit + rangoactual + ncasosatendidos, true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.onload = function() {
		var users = JSON.parse(xhr.responseText);
		if (xhr.readyState == 4 && xhr.status == "201") {
			console.table(users);
		} else {
			console.error(users);
		}
	}
	xhr.send(json);
}

document.querySelector('#actlista').addEventListener('click', actualizarListaDeIncidentes);

function actualizarListaDeIncidentes() {
	// Get all users
	var url = "http://localhost:8082/miguelapi/incidente";
	var xhr = new XMLHttpRequest();
	xhr.open('GET', url, true);
	xhr.send();
	xhr.onreadystatechange = function() {
		var users = JSON.parse(xhr.responseText);
		if (xhr.readyState == 4 && xhr.status == "302") {
			//console.table(users);
			var res = document.querySelector('#res');
			res.innerHTML = '';
			for (let item of users) {
				//console.log(item.fechadeocurrencia);
				res.innerHTML += `
					<tr>
						<th>${item.id}</th>
						<th>${item.nombredelcaso}</th>
						<th>${item.empresaafectada}</th>
						<th>${item.tiempodeafectacion}</th>
						<th>${item.cantpersonasafectadas}</th>
						<th>${item.fechadeocurrencia}</th>
						<th>${item.barriodeloshechos}</th>
						<th>${item.agentequeatendioelcaso.nombre}</th>
					</tr>
				`
			}
		} else {
			console.error("Error");
		}
	}
}

$(document).on("click", "#PDF", function() {
	html2canvas($('#listareporte')[0], {
		onrendered: function(canvas) {
			var data = canvas.toDataURL();
			var docDefinition = {
				content: [{
					image: data,
					width: 150
				}]
			};
			pdfMake.createPdf(docDefinition).download("lista_incidentes.pdf");
		}
	});
});
