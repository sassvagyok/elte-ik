// Állapottér
let pixels = [];

function initPixels(w, h) {
    pixels = [];

    for (let i = 0; i < h; i++) {
        const row = [];

        for (let j = 0; j < w; j++) {
            row.push("");
        }

        pixels.push(row);
    }
}

function select(x, y, color) {
    pixels[y][x] = color;
}

function drawColorShower(color) {
    colorShower.style.backgroundColor = color.target ? color.target.value : color.value;
}

function xyCoord(td) {
    const tr = td.parentNode;
    const x = td.cellIndex;
    const y = tr.sectionRowIndex;

    return {x, y};
}

// Elements
const editor = document.querySelector("#editor");
const btnGenerate = document.querySelector("#btnGenerate");
const inputWidth = document.querySelector("#width");
const inputHeight = document.querySelector("#height");
const colorShower = document.querySelector("#color-shower");
const color = document.querySelector("#color");

// HTML generálók
function genTable(pixels) {
    return `<table class="edit">${pixels.map((row) => `<tr>${row.map(() => `<td></td>`).join("")}</tr>`).join("")}</table>`;
}

// Eseménykezelés
btnGenerate.addEventListener("click", onGenerate);
editor.addEventListener("click", onCellClick);
color.addEventListener("input", drawColorShower);

function onGenerate() {
    const w = inputWidth.valueAsNumber;
    const h = inputHeight.valueAsNumber;

    initPixels(w, h);
    editor.innerHTML = genTable(pixels);
    drawColorShower(color);
}

function onCellClick(e) {
    if (e.target.matches("td")) {
        const {x, y} = xyCoord(e.target);

        select(x, y, color.value);
        e.target.style.backgroundColor = color.value;
    }
}