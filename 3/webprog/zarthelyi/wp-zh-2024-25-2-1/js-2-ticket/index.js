const seatingTable = document.querySelector('#seating');
const costTable = document.querySelector("#cost-info");
const addButton = document.querySelector("#add-standing");
const subButton = document.querySelector("#sub-standing");
const total = document.querySelector("#total");
const purchase = document.querySelector("#purchase");
const amount1 = document.querySelector("#sector-1-amount");
const amount2 = document.querySelector("#sector-2-amount");
const amount3 = document.querySelector("#sector-3-amount");
const total1 = document.querySelector("#sector-1-total");
const total2 = document.querySelector("#sector-2-total");
const total3 = document.querySelector("#sector-3-total");
const rowNum = 10;
const colNum = 14;
let sector1, sector2, sector3;

//                    0.   1.   2.   3.
const sectorCosts = [250, 350, 200, 100];
let seatsTaken = [0, 0, 0, 0];

// 1.
for (let i = 0; i < costTable.rows.length; i++) {
    const span = document.querySelector(`#sector-${i}-cost`);
    span.innerHTML = sectorCosts[i];
}

// 2.
addButton.addEventListener("click", addStanding);
subButton.addEventListener("click", subStanding);

function addStanding(e) {
    const amountSpan = document.querySelector("#sector-0-amount");
    amountSpan.innerHTML++;

    const costSpan = document.querySelector("#sector-0-cost");
    const totalSpan = document.querySelector("#sector-0-total");
    totalSpan.innerHTML = amountSpan.innerHTML *costSpan.innerHTML;
}

function subStanding(e) {
    const amountSpan = document.querySelector("#sector-0-amount");

    const tempAmount = amountSpan.innerHTML - 1;
    amountSpan.innerHTML = tempAmount < 0 ? 0 : tempAmount;

    const costSpan = document.querySelector("#sector-0-cost");
    const totalSpan = document.querySelector("#sector-0-total");
    totalSpan.innerHTML = amountSpan.innerHTML *costSpan.innerHTML;
}

// 3.
for (let i = 0; i < 10; i++) {
    let row = "<tr>";
    for (let j = 0; j < 14; j++) {
        row += `<td class="seat ${i < 3 ? "sector-1" : i < 6 ? "sector-2" : "sector-3"}"></td>`;
    }
    row += "</tr>";
    seatingTable.innerHTML += row;
}

// 4-5-6.
const seats = seatingTable.querySelectorAll('.seat');
for (let seat of seats) seat.addEventListener('click', select);

function select(e) {
    if (e.target.classList.contains("taken")) return;
    e.target.classList.toggle('selected');

    sector1 = seatingTable.querySelectorAll('.selected.sector-1');
    sector2 = seatingTable.querySelectorAll('.selected.sector-2');
    sector3 = seatingTable.querySelectorAll('.selected.sector-3');
    const selected = [sector1, sector2, sector3];

    for (let i = 0; i < selected.length; i++) {
        const amountSpan = document.querySelector(`#sector-${i + 1}-amount`);
        amountSpan.innerHTML = selected[i].length;

        const costSpan = document.querySelector(`#sector-${i + 1}-cost`);
        const totalSpan = document.querySelector(`#sector-${i + 1}-total`);
        totalSpan.innerHTML = costSpan.innerHTML * amountSpan.innerHTML;
    }
    total.innerHTML = Number(total1.innerHTML) + Number(total2.innerHTML) + Number(total3.innerHTML);
}

// 7-8.
purchase.addEventListener("click", buy);

function buy() {
    total.innerHTML = 0;
    const toReset = [amount1, amount2, amount3, total1, total2, total3];

    for (let reset of toReset) {
        reset.innerHTML = 0;
    }

    const selected = [sector1, sector2, sector3];

    for (let i = 0; i < selected.length; i++) {
        for (let j = 0; j < selected[i].length; j++) {
            selected[i][j].classList.toggle('selected');
            selected[i][j].classList.toggle('taken');
        }
    }
}