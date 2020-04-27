document.getElementById('v-pills-favorites-tab').addEventListener('click', function (event) {
    search();
});

document.getElementById("viewFavorites").addEventListener('click', function(event) {
    event.preventDefault();
    document.getElementById("v-pills-favorites-tab").click();
});

function search() {
    var context = document.querySelector('base').getAttribute('href');

    var url = context + "favorites";
    var options = {method: "GET"};
    fetch(url, options)
        .then(function (response) {
            return response.json();
        })
        .then(function (elements) {
            let container = document.querySelector("#favoritesContainer");
            let pars = "";
            if (elements.length > 0) {
                document.getElementById("noFavorites").style.display = "none";
                for (var i = 0; i < elements.length; i++) {
                    pars += createCard(elements[i])
                }
            } else {
                document.getElementById("noFavorites").style.display = "block";
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
        '<a class="text-decoration-none mx-3" href="/removeFavorite?user='+p.email+'">'+
        '<img alt="Rimuovi dai preferiti" id=favorite src="/icons/fav/star.png" width="30px">'+
        '</a>'+
        '</div>' +
        '</div>';

    return field;
}