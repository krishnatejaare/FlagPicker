function searchContinent(str) {
var xmlhttp = new XMLHttpRequest();
var url = "/continents/" + str;
document.getElementById("responseText1").innerHTML = "";
document.getElementById("responseText2").innerHTML = "";

xmlhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var myArr = JSON.parse(this.responseText);
        myFunction(myArr);
    }
};
xmlhttp.open("GET", url, true);
xmlhttp.send();
}

function getAllContinents() {
	var xmlhttp = new XMLHttpRequest();
	var url = "/continents";
	document.getElementById("responseText1").innerHTML = "";
	document.getElementById("responseText2").innerHTML = "";

	xmlhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	        var myArr = JSON.parse(this.responseText);
	        myFunction(myArr);
	    }
	};
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
	}

function myFunction(arr) {
    var out = "";
    var i;
    for(i = 0; i < arr.length; i++) {
        out += '<a href="' + arr[i].continent + '">' +
        arr[i].continent + '</a><br>';
    }
    document.getElementById("livesearch").innerHTML = out;
}

function getCountries(e) {
	e.preventDefault();

	var continentValue = document.getElementById("input1").value;
	document.getElementById("input1").value = "";
	document.getElementById("input2").value = "";

	var xmlhttp = new XMLHttpRequest();
	var url = "/continent/" + continentValue + "/countries";

	xmlhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	        var myArr = JSON.parse(this.responseText);
	        myFunction1(myArr);
	    }
	};
	xmlhttp.open("GET", url, true);
	xmlhttp.send();
}

function myFunction1(arr) {
    var out = "";
    var i;
    var cboxstart = '<input type="checkbox" name="countries" value="';
    var cboxend = '">';

    for(i = 0; i < arr.length; i++) {
       out += cboxstart + arr[i].flag + cboxend + arr[i].name +'<br>';
    }

    document.getElementById("countrylist").innerHTML = out;
}

function clearAll(e) {
    document.getElementById("countrylist").innerHTML = "";
    document.getElementById("flaglist").innerHTML = "";
    document.getElementById("livesearch").innerHTML = "";
    document.getElementById("responseText2").innerHTML ="";
}

