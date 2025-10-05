const form = document.querySelector("form");
const inputs = document.querySelectorAll("input");
const table = document.querySelector("table");

form.addEventListener("submit", onSubmit);

function onSubmit(e) {
    e.preventDefault();

    const inputArray = Array.from(inputs, i => i.value);

    table.innerHTML += `<tr>${inputArray.map(x => `<td>${x}</d>`).join("")}</tr>`
}