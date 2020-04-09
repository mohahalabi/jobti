var numExperiences=0;
var numEducations=0;
// Example starter JavaScript for disabling form submissions if there are invalid fields
(function() {
    'use strict';
    window.addEventListener('load', function() {
        // Fetch all the forms we want to apply custom Bootstrap validation styles to
        var forms = document.getElementsByClassName('needs-validation');
        // Loop over them and prevent submission
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

function createWorkingExperience(){
    if (numExperiences<5){
        var container=document.getElementById("experience"+numExperiences);
        container.style.display = "block";
        numExperiences+=1;
        if (numExperiences ==5){
            document.getElementById("creator").disabled=true;
        }
    } else {
        document.getElementById("creator").disabled=true;
    }
}

function createEducation(){
    if (numEducations<5){
        var container=document.getElementById("education"+numEducations);
        container.style.display = "block";
        numEducations+=1;
        if (numEducations ==5){
            document.getElementById("eduCreator").disabled=true;
        }
    } else {
        document.getElementById("eduCreator").disabled=true;
    }
}