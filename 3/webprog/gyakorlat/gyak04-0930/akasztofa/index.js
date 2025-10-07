// Application state
const MAX_WRONG_GUESSES = 9;
const guesses = [];
const buttons = "aábcdeéfghiíjklmnoóöőpqrstuúüűvwxyz";
const theWord = wordList[random(0, wordList.length - 1)];

function random(a, b) {
    return Math.floor(Math.random() * (b - a + 1)) + a;
}

function getWrongGuesses() {
    return guesses.filter(x => !theWord.includes(x));
}

function isLost() {
    return MAX_WRONG_GUESSES === getWrongGuesses().length;
}

function isWon() {
    return theWord.split().every(x => guesses.includes(x));
}

// Elements
const divEnd = document.querySelector("#vege");
const divWord = document.querySelector("#szo");
const divButtons = document.querySelector("#betuk");
const divScore = document.querySelector("#eredmeny");

function renderEnd() {
    divEnd.innerHTML = isWon() ? "Nyertél" : isLost() ? "Vesztettél" : "";
}

function renderScore() {
    divScore.innerHTML = `${getWrongGuesses().length}/${MAX_WRONG_GUESSES}`;
}

function renderButtons() {
    divButtons.innerHTML = buttons.split("").map(x => `<button ${guesses.includes(x) ? "disabled" : ""}>${x}</button>`).join("");
}

function renderWord() {
    divWord.innerHTML = theWord.split("").map(x => `<span class=${isLost() && guesses.includes(x) ? "hianyzo" : ""}>${isLost() || guesses.includes(x) ? x : ""}</span>`).join("");
}

function updateSVG() {
    for (let i = 1; i <= getWrongGuesses().length; i++) {
        document.querySelector(`svg *:nth-child(${i})`).classList.add("rajzol");
    }
}

divButtons.addEventListener("click", function(e) {
    if (!isWon() && !isLost() && e.target.matches("button")) {
        const c = e.target.innerHTML;
        
        guesses.push(c);

        renderWord();
        renderButtons();
        renderEnd();
        renderScore();
        updateSVG();

        if (isWon) divWord.classList.add("nyer");
    }
});

// Start
renderWord();
renderButtons();
renderEnd();
renderScore();
updateSVG();