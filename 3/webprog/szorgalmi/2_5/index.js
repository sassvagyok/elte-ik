const form = document.querySelector("form");
const url = document.querySelector("#kep");
const image = document.querySelector("#kephelye");

form.addEventListener("submit", showImage);

function showImage(e) {
    e.preventDefault();

    image.src = url.value;
}