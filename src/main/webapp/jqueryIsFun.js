function partialRefresh() {
	var name = document.getElementById('username');
	var myName = name.value;
	alert("hello world");
	$('#result').load(window.location.href + "#result");
}

function callServlet() {
	var name = $('#username').val();
	var hiddenContent = document.getElementById("hideDiv");
	hiddenContent.style.display = "none";
	$.ajax({
		url: 'greetingFromAjax',
		method: 'GET',
		data: {
			username: name
		},
		success: function(resultText) {

			var message = JSON.stringify(resultText);
			let respondedMessage = JSON.parse(message);

			var is_fail = respondedMessage.localeCompare("false");

			if (is_fail == 0) {
				alert("user not found");
			} else {

				hiddenContent.style.display = "block";
				$('#hideDiv').load(' #hideDiv');
			}

		},
		error: function(jqXHR, exception) {
			alert('Error occured!!');
		}
	});
}