var currentTab = 0; // Current tab is set to be the first tab (0)
showTab(currentTab); // Display the current tab
var caller = document.currentScript.getAttribute('user');

function isEmpty(field) {
    var valid = true;
    if (document.getElementById(field).value === "") {
        valid = false;
        document.getElementById(field).classList.remove("green-border");
        document.getElementById(field).classList.add("red-border");
    } else {
        document.getElementById(field).classList.remove("red-border");
        document.getElementById(field).classList.add("green-border");
    }
    return valid;
}

function isSelected(field) {
    var valid = true;
    var z = document.getElementById(field);
    //Sorryy :( @Generale
    var b = document.getElementById(field + "field").children[1].children[1];
    var text = z.options[z.selectedIndex].text;
    if (text === "") {
        valid = false;
        b.classList.remove("btn-outline-secondary");
        b.classList.remove("green-border");
        b.classList.add("red-border");
    } else {
        b.classList.remove("red-border");
        b.classList.add("green-border");
    }
    return valid;
}

function isSelectedByName(field) {
    var valid = true;
    var z = document.getElementsByName(field)
    //Sorryy :( @Generale
    var b = document.getElementById(field + "field").children[1].children[1];
    var text = z[0].options[z[0].selectedIndex].text;
    if (text === "") {
        valid = false;
        b.classList.remove("btn-outline-secondary");
        b.classList.remove("green-border");
        b.classList.add("red-border");
    } else {
        b.classList.remove("red-border");
        b.classList.add("green-border");
    }
    return valid;
}

function search() {
    var context = document.querySelector('base').getAttribute('href');
    var url = context + "verifyemail?q=" + document.querySelector('#email').value;
    var options = {method: "GET"};
    var x = fetch(url, options)
        .then(function (response) {
            response.json();
        })
        .then(function (present) {
            return present;
        });
    return x;
}

function showTab() {
    // This function will display the specified tab of the form...
    var x = document.getElementsByClassName("tab");
    x[currentTab].style.display = "block";
    //... and fix the Previous/Next buttons:
    if (currentTab === 0) {
        document.getElementById("prevBtn").style.display = "none";
    } else {
        document.getElementById("prevBtn").style.display = "inline";
    }
    if (currentTab === (x.length - 1)) {
        document.getElementById("nextBtn").innerHTML = "Conferma";
    } else {
        document.getElementById("nextBtn").innerHTML = "Avanti";
    }
    //... and run a function that will display the correct step indicator:
    fixStepIndicator(currentTab)
}

function validateEmailPass() {
    var valid = true;
    valid = isEmpty("email") && valid;
    valid = isEmpty("password") && valid;
    var email = document.getElementById("email").value;
    valid =(/^\w+@[a-zA-Z_]+?.[a-zA-Z]{2,3}$/g).test(email) && valid;
    if (!valid) {
        document.getElementById("email").classList.remove("green-border");
        document.getElementById("email").classList.add("red-border");
    } else {
        document.getElementById("email").classList.remove("red-border");
        document.getElementById("email").classList.add("green-border");
    }
    return valid;
}

function validateNameSocial() {
    var valid = true;
    valid = isEmpty("name") && valid;
    valid = isSelected("socialReason") && valid;

    return valid;
}

function validateNameSurSector() {
    var valid = true;
    valid = isEmpty("name") && valid;
    valid = isEmpty("surname") && valid;
    valid = isSelected("sector") && valid;

    return valid;
}

function validateSiteSector() {
    var valid = true;
    valid = isEmpty("site") && valid;
    valid = isSelected("sector") && valid;

    return valid;
}

function validateaddress() {
    var valid = true;
    valid = isSelected("country") && valid;
    valid = isSelected("region") && valid;
    valid = isEmpty("city") && valid;
    valid = validate4digitInteger("postcode") && valid;
    valid = isEmpty("address") && valid;

    return valid;
}

function validateDate() {
    var valid = true;
    valid = isSelected("day") && valid;
    valid = isSelectedByName("month") && valid;
    valid = isSelectedByName("year") && valid;

    return valid
}

function validate4digitInteger(field) {
    var valid = true;
    var l = document.getElementById(field);
    if (l.value === "" || isNaN(parseInt(l.value)) || l.value.length !== 4) {
        valid = false;
        l.classList.remove("green-border");
        l.classList.add("red-border");
    } else {
        l.classList.remove("red-border");
        l.classList.add("green-border");
    }
    return valid;
}

function validatePrivateForm() {
    var valid = true;
    switch (currentTab) {
        case 0:
            valid = valid && validateEmailPass();
            break;
        case 1:
            valid = valid && validateNameSurSector();
            break;
        case 2:
            valid = valid && validateaddress();
            break;
        case 3:
            valid = valid && validateDate();
            break;
    }
    return valid;
}

function validateCompanyForm() {
    var valid = true;
    switch (currentTab) {
        case 0:
            valid = valid && validateEmailPass();
            break;
        case 1:
            valid = valid && validateNameSocial();
            break;
        case 2:
            valid = valid && validateSiteSector();
            break;
        case 3:
            valid = valid && validateaddress();
            break;
        case 4:
            valid = valid && validate4digitInteger("year");
            break;
    }
    return valid;
}

function nextPrev(n) {
    //validation
    var valid = true;
    if (n>0){
        if (caller === "private")
            valid = validatePrivateForm() && valid;
        else if (caller === "company")
            valid = validateCompanyForm() && valid;
        if (!valid) return false;

        //if valid
        if (valid) {
            document.getElementsByClassName("step")[currentTab].className += " finish";
        }
    }
    var x = document.getElementsByClassName("tab");
    x[currentTab].style.display = "none";

    currentTab = currentTab + n;

    if (n>0 && currentTab >= x.length) {
        // ... the form gets submitted:
        document.getElementById("regForm").submit();
        return false;
    }

    showTab();
}

function fixStepIndicator(n) {
    // This function removes the "active" class of all steps...
    var c = document.getElementsByClassName("step");
    for (let j = 0; j < c.length; j++) {
        c[j].classList.remove("active");
    }
    //... and adds the "active" class on the current step:
    c[n].className += " active";
}