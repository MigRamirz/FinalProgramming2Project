// Get all users
var url = "http://localhost:8082/miguelapi/agente/ordencasos";
var xhr = new XMLHttpRequest();
xhr.open('GET', url, true);
xhr.send();
xhr.onreadystatechange = function() {
	var users = JSON.parse(xhr.responseText);
	if (xhr.readyState == 4 && xhr.status == "302") {
		var res = document.querySelector('#nom');
		res.innerHTML = '';
		for(let i = 0; i < 1; i++) {
			res.innerHTML += `${users[0].nombre}`
		}
	} else {
		console.error("Error getting");
	}
}
