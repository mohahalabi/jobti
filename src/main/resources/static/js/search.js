function toggler() {
    var txt = document.getElementById("txtFilter");
    if ($("#toggler").hasClass("collapsed")) {
        $("#toggler i").removeClass("fa-plus");
        $("#toggler1 i").removeClass("fa-plus");
        $("#toggler i").addClass("fa-minus");
        $("#toggler1 i").addClass("fa-minus");

        txt.innerText = "Nascondi filtri";
    } else {
        $("#toggler i").removeClass("fa-minus");
        $("#toggler1 i").removeClass("fa-minus");
        $("#toggler i").addClass("fa-plus");
        $("#toggler1 i").addClass("fa-plus");
        txt.innerText = "Visualizza più filtri";
    }
}

$(document).ready(function () {
    populateSector();
    populateProfession("")
});

$('#sector').change(function () {
    populateProfession("");
})
var old ="old";
$('#profession').change(function () {
    var val = document.querySelector("#profession").value;
    if(val===""){
        populateProfession();
    } else {
        old =val;
        var selectList = document.getElementsByName("profession");
        selectList = selectList[0]
        var selectOptions = selectList.getElementsByTagName('option');
        for (var i = 1; i < selectOptions.length; i++) {
            var opt = selectOptions[i];
            if (opt.value == val) {
                selectList.removeChild(opt);
                selectList.insertBefore(opt, selectOptions[0]);
            }
        }
        $('#profession').selectpicker('refresh');
    }

})

function populateSector() {
    var context = document.querySelector('base').getAttribute('href');
    var url = context + "sectors";
    var options = {method: "GET"};
    fetch(url, options)
        .then(function (response) {
            return response.json();
        })
        .then(function (elements) {
            for (let i = 0; i < elements.length; i++) {
                $('#sector').append('<option value="' + elements[i].name + '">' + elements[i].name + '</option>');
            }
        })
        .then(function () {
            $('#sector').selectpicker('refresh');
        })
}

function populateProfession(firstOpt) {
    $("#profession").empty();
    var context = document.querySelector('base').getAttribute('href');
    var url = context + "professions?sector=" + $("#sector").val();
    var options = {method: "GET"};
    fetch(url, options)
        .then(function (response) {
            return response.json();
        })
        .then(function (elements) {
            for (let i = 0; i < elements.length; i++) {
                if(firstOpt===elements[i].name){
                    $('#profession').prepend('<option value="' + elements[i].name + '">' + elements[i].name + '</option>');
                } else {
                    $('#profession').append('<option value="' + elements[i].name + '">' + elements[i].name + '</option>');
                }

            }
        })
        .then(function () {
            $('#profession').selectpicker('refresh');
        })
}

document.getElementById('searchButton').addEventListener('click', function (event) {
    event.preventDefault();
    search();
    return false;
});

function search() {
    var context = document.querySelector('base').getAttribute('href');
    var sector = document.querySelector("#sector").value;
    var profession = document.querySelector("#profession").value;
    if(sector===""&& profession===""){
        document.getElementById("noResult").style.display = "none";
        document.querySelector("#results").innerHTML=""
        document.getElementById("numResult").innerHTML=""
        document.getElementById("noChoice").style.display = "block";
        return;
    }else{
        document.getElementById("noChoice").style.display = "none";
    }

    var lang = $('#lang').val();
    var edu = $('#edu').val();
    var age = $('#age').val();
    var exp = $('#experience').val();
    var url = context + "filter?sector=" + sector + "&profession=" + profession + "&edu=" + edu + "&lang=" + lang + "&age=" + age+ "&experience=" + exp;
    var options = {method: "GET"};
    fetch(url, options)
        .then(function (response) {
            return response.json();
        })
        .then(function (elements) {
            let container = document.querySelector("#results");
            let pars = "";
            if (elements.length > 0) {
                document.getElementById("noResult").style.display = "none";
                document.getElementById("numResult").style.display = "block";
                if (elements.length == 1)
                    document.getElementById("numResult").innerHTML = "1 Risultato trovato";
                else
                    document.getElementById("numResult").innerHTML = elements.length + " Risultati trovati";
                for (var i = 0; i < elements.length; i++) {
                    pars += createCard(elements[i])
                }
            } else {
                document.getElementById("numResult").style.display = "none";
                document.getElementById("noResult").style.display = "block";
            }
            container.innerHTML = pars;
        })
}

function createCard(p) {
    var context = document.querySelector('base').getAttribute('href');
    var field = '<div class="card m-2" style="min-width: 200px;max-width: 200px">';
    emailencoded = encodeURIComponent(p.email);
    if (p.image!=null && p.image.length != 0)
        field += '<img class="card-img-top p-2" src="' + context + 'user/' + p.email + '/image">';
    field += '<div class="card-body">' +
        '<h5 class="card-title text-center">' + p.surname + ' ' + p.name + '</h5>' +
        '<p class="card-text text-center">' + p.sector.name + '<br>' + p.profession.name + '</p>' +
        '<p class="card-text text-center"><small class="text-muted">' + p.city + '</small></p>' +
        '</div>' +
        '<div class="card-footer text-center">' +
        '<a href="/profile/'+emailencoded+'" class="btn btn-primary">Visita</a>' +
        '<a class="text-decoration-none mx-3" href="/">'+
        '<img alt="Aggiungi ai preferiti" id=favorite src="/icons/fav/empty-star.png" width="30px">'+
        '</a>'+
        '</div>' +
        '</div>';

    return field;
}