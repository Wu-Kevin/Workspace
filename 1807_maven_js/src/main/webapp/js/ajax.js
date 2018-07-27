/**
 *  http status code
 *  100: information
 *  200: success
 *  300: redirect
 *  400: client error
 *  500: server error
 */

window.onload = () => {
	let gButton = document.getElementById('giftButton');
	gButton.addEventListener('click', gifts);
}

//could also place this inside the addEventListener function by using an anonymous call
function gifts() {
	let gDisplay = document.getElementById('display');
	var xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function(){
		console.log(xhr.readyState);
		if((xhr.readyState === 4) & (xhr.status === 200)) {
			gDisplay.innerHTML = xhr.responseText;
		}
		
	}
	xhr.open('get', 'https://api.myjson.com/bins/iw60z');
	xhr.send();
}