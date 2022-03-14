
function isNumber(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
	document.getElementById("err").innerText="*please enter numbers only";
        return false;
    }
    return true;
}


function isLetter(evt) {
    evt = (evt) ? evt : window.event;
    var charCode = (evt.which) ? evt.which : evt.keyCode;
    if (charCode==32 ||charCode > 64 && charCode < 91 || charCode > 96 && charCode < 123) {
        return true;
    }
	document.getElementById("err").innerText="*please enter alphabets only";
    return false;
}
