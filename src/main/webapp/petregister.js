
function register() {
	
	var failBox = document.getElementById('failbox');
	failBox.style.display ="none";
	
	//store value of petname
	var petname = document.getElementById('petname').value;

	//store value of selected pet gender
	var genderRadio = document.getElementsByName('gender');
	var gender = null;

	for (i = 0; i < genderRadio.length; i++) {
		if (genderRadio[i].checked)
			gender = genderRadio[i].value;
	}

	//store value of petType
	var petTypeSelector = document.getElementsByName('pettype');
	var pettype = null;

	for (i = 0; i < petTypeSelector.length; i++) {
		if (petTypeSelector[i].checked)
			pettype = petTypeSelector[i].id;
	}

	//store value of furtype
	var furselection = document.getElementById('fur-type');
	var furtype = furselection.options[furselection.selectedIndex].value;

	//store value of pet age
	var ageselection = document.getElementById('age-class');
	var ageclass = ageselection.options[ageselection.selectedIndex].value;

	alert("petname: " + petname + "\n" +
		"gender: " + gender + "\n" +
		"pet type: " + pettype + "\n" +
		"fur type: " + furtype + "\n" +
		"age class: " + ageclass);

	if (petname.length != 0 && gender != null && pettype != null &&
		furtype != "Fur type" && ageclass != "Age class") {

		$.ajax({
			url: 'pet-register-controller',
			method: 'POST',
			data: {
				petname: petname,
				gender: gender,
				pettype: pettype,
				furtype: furtype,
				ageclass: ageclass
			},
			success: function(resultText /*resultText ni AJAX punya variable*/) {
				var message = JSON.stringify(resultText);
				let respondedMessage = JSON.parse(message);
				alert("sent");

				var is_success = respondedMessage
					.localeCompare("true");
				var is_fail = respondedMessage
					.localeCompare("false");

				if (is_success == 0) {
					alert("pet registration successful, redirecting you to booking page..");
					window.location.replace("init-user-pet");
				} else if (is_fail == 0) {
					failBox.style.display = "block";
				} else {
					alert("could not load hidden message box");
				}
			},
			error: function(jqXHR, exception) {
				alert('Error occured!!');
			}
		});

	}
	else if (petname.length == 0)
		alert("please enter petname");
	else if (gender == null)
		alert("please select pet gender");
	else if (pettype == null)
		alert("please select pet type");
	else if (furtype == "Fur type")
		alert("please select fur type");
	else if (ageclass == "Age class")
		alert("please select age class");

}
