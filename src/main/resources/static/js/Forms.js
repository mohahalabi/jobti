function showP(n) {
    var back = n-1;
    var x = document.getElementById("formP" + n);
    var y = document.getElementById("formP" + back);
    y.style.display = "none";
    if (x.style.display === "none") {
        x.style.display = "block";
    } else {
        x.style.display = "none";
    }
}