//post nuevo agente 
formularioagente.addEventListener('input', () => {
	if (camponombre.value.length > 0
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
	var url = "http://localhost:8082/miguelapi/agente";
	var nombre = '?nombre=' + document.getElementById("camponombre").value;
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
	xhr.open("POST", url + nombre + cedula + fnacimiento + sexo + fentrinstit + rangoactual + ncasosatendidos, true);
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

//get 1 agente por id
formularioagente1.addEventListener('input', () => {
	if (campoid.value.length > 0) {
		enviar1.removeAttribute('disabled');
	} else {
		enviar1.setAttribute('disabled', 'disabled');
	}
});
//valida los campos para 1 agente por id
function validarCampos1() {
	// Get a user
	var url = "http://localhost:8082/miguelapi/agente";
	var xhr = new XMLHttpRequest()
	var id = '/' + document.getElementById("campoid").value;
	xhr.open('GET', url + id, true)
	xhr.onload = function() {
		var user = JSON.parse(xhr.responseText);
		var res = document.querySelector('#res2');
		res.innerHTML = '';
		if (xhr.readyState == 4 && xhr.status == "202") {
			//console.table(user);
			res.innerHTML += `
					<tr>
						<th>${user.id}</th>
						<th>${user.nombre}</th>
						<th>${user.cedula}</th>
						<th>${user.fnacimiento}</th>
						<th>${user.edad}</th>
						<th>${user.sexo}</th>
						<th>${user.fentradainstit}</th>
						<th>${user.rangoactual}</th>
						<th>${user.ncasosatendidos}</th>	
					</tr>
				`
		} else {
			console.error("Error getting");
		}
	}
	xhr.send(null);
}

//delete 1 agente por id
formularioagente2.addEventListener('input', () => {
	if (campoid2.value.length > 0) {
		enviar2.removeAttribute('disabled');
	} else {
		enviar2.setAttribute('disabled', 'disabled');
	}
});
//valida los campos para DELETE 1 agente por id
function validarCampos2() {
	// Delete a user
	var url = "http://localhost:8082/miguelapi/agente";
	var id = '/{id}?id=' + document.getElementById("campoid2").value;
	var xhr = new XMLHttpRequest();
	xhr.open("DELETE", url + id, true);
	xhr.onload = function() {
		var users = JSON.parse(xhr.responseText);
		if (xhr.readyState == 4 && xhr.status == "202") {
			//console.table(users);
		} else {
			console.error("Error deleting");
		}
	}
	xhr.send(null);
}

//get 1 agente por ncasosatend
formularioagente3.addEventListener('input', () => {
	if (campocasos.value.length > 0) {
		enviar3.removeAttribute('disabled');
	} else {
		enviar3.setAttribute('disabled', 'disabled');
	}
});
//valida los campos para GET 1 agente por ncasosatendidos
function validarCampos3() {
	// Get a user
	var url = "http://localhost:8082/miguelapi/agente/ncasosatendidos";
	var xhr = new XMLHttpRequest()
	var casos = '/' + document.getElementById("campocasos").value;
	xhr.open('GET', url + casos, true)
	xhr.onload = function() {
		var users = JSON.parse(xhr.responseText);
		if (xhr.readyState == 4 && xhr.status == "202") {
			var res = document.querySelector('#res3');
			res.innerHTML = '';
			//console.table(users);
			for (let item of users) {
				res.innerHTML += `
					<tr>
						<th>${item.id}</th>
						<th>${item.nombre}</th>
						<th>${item.cedula}</th>
						<th>${item.fnacimiento}</th>
						<th>${item.edad}</th>
						<th>${item.sexo}</th>
						<th>${item.fentradainstit}</th>
						<th>${item.rangoactual}</th>
						<th>${item.ncasosatendidos}</th>	
					</tr>
				`
			}
		} else {
			console.error("Error getting one");
		}
	}
	xhr.send(null);
}
document.querySelector('#actlista').addEventListener('click', actualizarListaDeAgentes);

function actualizarListaDeAgentes() {
	// Get all users
	var url = "http://localhost:8082/miguelapi/agente";
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
				//console.log(item.nombre);
				res.innerHTML += `
					<tr>
						<th>${item.id}</th>
						<th>${item.nombre}</th>
						<th>${item.cedula}</th>
						<th>${item.fnacimiento}</th>
						<th>${item.edad}</th>
						<th>${item.sexo}</th>
						<th>${item.fentradainstit}</th>
						<th>${item.rangoactual}</th>
						<th>${item.ncasosatendidos}</th>	
					</tr>
				`
			}
		} else {
			console.error("Error getting");
		}
	}
}

//Actualizar 1 agente por cedula
formularioagente4.addEventListener('input', () => {
	if (campocedul.value.length > 0 && camporangonuevo.value.length > 0) {
		actualizar.removeAttribute('disabled');
	} else {
		actualizar.setAttribute('disabled', 'disabled');
	}
});

function validarCampos4() {
	// Update a user
	var url = "http://localhost:8082/miguelapi/agente/cedula";
	var cedula = '/{cedula}?cedula=' + document.getElementById("campocedul").value;
	var rangoact = '&rangoactual=' + document.getElementById("camporangonuevo").value;

	var data = {};
	data.rangoact = document.getElementById("camporangonuevo").value;
	var json = JSON.stringify(data);

	var xhr = new XMLHttpRequest();
	xhr.open("PUT", url + cedula + rangoact, true);
	xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
	xhr.onload = function() {
		var users = JSON.parse(xhr.responseText);
		if (xhr.readyState == 4 && xhr.status == "200") {
			//console.table(users);
		} else {
			console.error("Error update");
		}
	}
	xhr.send(json);
}

$(document).on("click", "#PDF1", function() {
	html2canvas($('#listaagente')[0], {
		onrendered: function(canvas) {
			var data = canvas.toDataURL();
			var docDefinition = {
				content: [{
					image: data,
					width: 150
				}]
			};
			pdfMake.createPdf(docDefinition).download("lista_agentes.pdf");
		}
	});
});