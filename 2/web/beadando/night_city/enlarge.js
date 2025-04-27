document.addEventListener("DOMContentLoaded", function() {
    var toggles = document.querySelectorAll("#textsize-toggle");

    toggles.forEach(function(toggle) {
        toggle.addEventListener("click", function(e) {
            e.preventDefault();
            document.body.classList.toggle("large-text");
        });
    });
});