document.addEventListener("DOMContentLoaded", function() {
    var toggle = document.getElementById("textsize-toggle");
    
    toggle.addEventListener("click", function(e) {
        e.preventDefault();
        document.body.classList.toggle("large-text");
    });
});