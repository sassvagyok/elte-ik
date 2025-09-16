function kerulet(r) {
    return 2 * r * Math.PI;
}

const gomb = document.querySelector("#szamol");

gomb.addEventListener("click", kattintas);

function kattintas() {
    const input = document.querySelector("#sugar");
    const output = document.querySelector("#kerulet");

    const r = parseFloat(input.value);

    input.classList.toggle("hibas", isNaN(r));
    if (isNaN(r)) return output.innerHTML = "";

    const ker = kerulet(r);
    output.innerHTML = ker;
}