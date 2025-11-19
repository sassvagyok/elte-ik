const playButton = document.querySelector("#play");
const cardButton = document.querySelector("#card");
const nextButton = document.querySelector("#next");
const menuButton = document.querySelector("#menuback");
const resultsButton = document.querySelector("#resultsButton");
const roundH = document.querySelector(".roundtitle");
const menuDiv = document.querySelector("#menu");
const gameDiv = document.querySelector("#main");
const canvas = document.querySelector("#game");
const usernameInput = document.querySelector("#username");
const usernameTitle = document.querySelector("#display_username");
const typeTitle = document.querySelector("#type");
const stopwatchP = document.querySelector("#stopwatch");
const lineImg = document.querySelector("#line");
const linesImg = document.querySelectorAll("#lines");
const cardImg = document.querySelector("#cardimg");
const rPointsP = document.querySelectorAll(".rpoints");
const fPointsP = document.querySelectorAll(".fpoints");
const resultsDiv = document.querySelector("#results");
const ctx = canvas.getContext("2d");

const ROWS = 10;
const COLS = 10;
const SIZE = 80;
let username, selectedStation, currentCard, startTime, currentLine, remainingLines, round, cells, playedCards, metroLines, connections, progress, roundPoints, finalPoints;

const cards = [
    {
        type: "A",
        center: false
    },
    {
        type: "B",
        center: false
    },
    {
        type: "C",
        center: false
    },
    {
        type: "D",
        center: false
    },
    {
        type: "Joker",
        center: false
    },
    {
        type: "A",
        center: true
    },
    {
        type: "B",
        center: true
    },
    {
        type: "C",
        center: true
    },
    {
        type: "D",
        center: true
    },
    {
        type: "Joker",
        center: true
    }
];

canvas.addEventListener("click", createConnection);
playButton.addEventListener("click", startGame);
cardButton.addEventListener("click", drawNewCard);
nextButton.addEventListener("click", startNewLine);
resultsButton.addEventListener("click", showResults);
menuButton.addEventListener("click", showMenu);

async function startGame() {
    resetGame();
    username = usernameInput.value ? usernameInput.value : "Vendég";
    usernameTitle.innerHTML = username;

    startTime = Date.now();
    setInterval(startTimer, 1000);

    menuDiv.hidden = true;
    gameDiv.hidden = false;

    await getStations();
    startNewLine();
    renderGame();
    displayRemainingLines();
}

function startTimer() {
    const elapsedMs = Date.now() - startTime;
    const elapsedSeconds = Math.floor(elapsedMs / 1000);
    const minutes = Math.floor(elapsedSeconds / 60);
    const seconds = elapsedSeconds % 60;

    stopwatchP.innerHTML = `${minutes.toString().padStart(2, "0")}:${seconds.toString().padStart(2, "0")}`;
}

function resetGame() {
    stopwatchP.innerHTML = "00:00";
    progress = [];
    for (let i = 0; i < 4; i++) {
        progress.push(
            {
                line: i,
                districts: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0],
                crossed_duna: 0,
                train_stations: 0
            }
        );
    }

    cells = [];
    playedCards = [];
    metroLines = [];
    remainingLines = [];
    connections = [];
    round = 0;

    roundPoints = [
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 0],
        [0, 0, 0, 0]
    ];

    finalPoints = [0, 0, 0, 0, 0, 0];
    displayRoundPoints(true);
    displayFinalPoints(true);
}

async function getStations() {
    const res_stations = await fetch("stations.json");
    const stations = await res_stations.json();
    stations.forEach(s => {
        s.connections = [0, 0, 0, 0],
        s.occupiedBy = [false, false, false, false]
    });

    const res_lines = await fetch("lines.json");
    metroLines = await res_lines.json();
    remainingLines = metroLines.slice();
    remainingLines.sort(() => Math.random() - 0.5);

    for (let i = 0; i < 10; i++) {
        for (let j = 0; j < 10; j++) {
            const isStation = stations.some(s => s.x === i && s.y === j);

            if (!isStation) {
                cells.push(
                    {
                        x: i,
                        y: j,
                        occupiedBy: [false, false, false, false]
                    }
                );
            }
        }
    }

    cells = cells.concat(stations);
}

function showMenu() {
    menuDiv.hidden = false;
    gameDiv.hidden = true;
}

function renderGame() {
    const bgImage = new Image();
    bgImage.onload = () => {
        ctx.drawImage(bgImage, 0, 0, canvas.width, canvas.height);
    };

    bgImage.src = "media/frame.svg";
}

function createConnection(e) {
    if (!currentCard) return;

    const getIndex = n => Math.min(COLS - 1, Math.max(0, Math.floor(n / SIZE)));

    const currentX = getIndex(e.offsetX);
    const currentY = getIndex(e.offsetY);

    if (selectedStation !== undefined) {
        const currentStation = cells.find(s => s.x === currentX && s.y === currentY);
        const isSameCell = selectedStation === currentStation;
        const isDiagonal = Math.abs(selectedStation.x - currentStation.x) === Math.abs(selectedStation.y - currentStation.y);
        const isStraight = selectedStation.x === currentStation.x || selectedStation.y === currentStation.y;
        const isSameType = currentCard.type === currentStation.type || currentStation.type === "?" || currentCard.type === "Joker";

        if (isSameCell) {
            drawCircle(selectedStation.x, selectedStation.y, currentLine.color, 10);
            selectedStation = undefined;
            return;
        }

        if (hasConnection(selectedStation.x, selectedStation.y, currentStation.x, currentStation.y)) return;
        if (!(isDiagonal || isStraight) || (currentStation.id === undefined || currentStation.occupiedBy[currentLine.id])) return;
        if (!isSameType) return;
        if (currentStation.connections[currentLine.id] === 2) return;
        if (!isClear(isStraight, selectedStation.x, selectedStation.y, currentStation.x, currentStation.y)) return;

        selectedStation.connections[currentLine.id]++;

        if (!currentStation.occupiedBy[currentLine.id]) {
            currentStation.occupiedBy[currentLine.id] = true;
            currentStation.connections[currentLine.id]++;
        }

        drawCircle(selectedStation.x, selectedStation.y, currentLine.color, 10);
        drawCircle(currentStation.x, currentStation.y, currentLine.color, 10);
        drawLine(isStraight, selectedStation.x, selectedStation.y, currentStation.x, currentStation.y, currentLine);

        connections.push(
            {
                from: {
                    x: selectedStation.x,
                    y: selectedStation.y,
                },
                to: {
                    x: currentStation.x,
                    y: currentStation.y
                }
            }
        );

        updateProgress(selectedStation, currentStation, currentLine);
        selectedStation = undefined;
        drawNewCard();
    } else {
        const station = cells.find(s => s.x === currentX && s.y === currentY);
        if (station.id === undefined) return;
        const isCurrentStartingStation = metroLines.some(l => l.start === station.id && l.id === currentLine.id);

        if (station.connections[currentLine.id] === 2) return;
        if (!station.occupiedBy[currentLine.id] && !isCurrentStartingStation) return;
        if (station.connections[currentLine.number] === 2) return;

        selectedStation = station;

        drawCircle(currentX, currentY, "#4D0E5F", 6);
    }
}

function updateProgress(selectedStation, currentStation, line) {
    const lineProgress = progress.find(x => x.line === line.id);

    lineProgress.districts[currentStation.district]++;
    const isStartingStation = metroLines.some(s => s.start === selectedStation.id);

    if (isStartingStation && lineProgress.districts[selectedStation.district] === 0) lineProgress.districts[selectedStation.district]++;

    if (selectedStation.side !== currentStation.side) lineProgress.crossed_duna++;
    if (currentStation.train) lineProgress.train_stations++;

}

function hasConnection (x1, y1, x2, y2) {
    const hasConnection = connections.some(c =>
        (c.from.x === x1 && c.from.y === y1 && c.to.x === x2 && c.to.y === y2) ||
        (c.from.x === x2 && c.from.y === y2 && c.to.x === x1 && c.to.y === y1)
    );

    return hasConnection;
}

function drawCircle(x, y, color, width) {
    const cx = x * SIZE + SIZE / 2;
    const cy = y * SIZE + SIZE / 2;
    ctx.save();
    ctx.beginPath();
    ctx.strokeStyle = color;
    ctx.lineWidth = width;
    ctx.arc(cx, cy, SIZE / 3, 0, Math.PI * 2);
    ctx.stroke();
    ctx.restore();
}

function drawLine(straight, cx1, cy1, cx2, cy2, line) {
    const x1 = cx1 * SIZE + SIZE / 2;
    const y1 = cy1 * SIZE + SIZE / 2;
    const x2 = cx2 * SIZE + SIZE / 2;
    const y2 = cy2 * SIZE + SIZE / 2;

    const dx = x2 - x1;
    const dy = y2 - y1;
    const len = Math.hypot(dx, dy);
    if (len === 0) return;

    const nx = dx / len;
    const ny = dy / len;

    const sx = x1 + nx * SIZE * 0.4;
    const sy = y1 + ny * SIZE * 0.4;
    const ex = x2 - nx * SIZE * 0.4;
    const ey = y2 - ny * SIZE * 0.4;

    if (straight) {
        if (cx1 === cx2) {
            const yStart = Math.min(cy1, cy2);
            const yEnd = Math.max(cy1, cy2);
            for (let y = yStart; y <= yEnd; y++) {
                const st = cells.find(s => s.x === cx1 && s.y === y);
                st.occupiedBy[line.id] = true;
            }
        } else if (cy1 === cy2) {
            const xStart = Math.min(cx1, cx2);
            const xEnd = Math.max(cx1, cx2);
            for (let x = xStart; x <= xEnd; x++) {
                const st = cells.find(s => s.x === x && s.y === cy1);
                st.occupiedBy[line.id] = true;
            }
        }
    } else {
        const stepX = cx2 > cx1 ? 1 : -1;
        const stepY = cy2 > cy1 ? 1 : -1;

        let x = cx1, y = cy1;
        while (true) {
            const st = cells.find(s => s.x === x && s.y === y);
            st.occupiedBy[line.id] = true;

            if (x === cx2 && y === cy2) break;
            x += stepX;
            y += stepY;
        }
    }

    ctx.save();
    ctx.beginPath();
    ctx.strokeStyle = line.color;
    ctx.lineWidth = 13;
    ctx.lineCap = 'round';
    ctx.moveTo(sx, sy);
    ctx.lineTo(ex, ey);
    ctx.stroke();
    ctx.restore();
}

function isClear(straight, x1, y1, x2, y2) {
    if (straight) {
        if (x1 === x2) {
            const yStart = Math.min(y1, y2) + 1;
            const yEnd = Math.max(y1, y2) - 1;
            for (let y = yStart; y <= yEnd; y++) {
                const st = cells.find(s => s.x === x1 && s.y === y);
                if (st.id || st.occupiedBy.some(x => x)) return false;
            }
        } else if (y1 === y2) {
            const xStart = Math.min(x1, x2) + 1;
            const xEnd = Math.max(x1, x2) - 1;
            for (let x = xStart; x <= xEnd; x++) {
                const st = cells.find(s => s.x === x && s.y === y1);
                if (st.id || st.occupiedBy.some(x => x)) return false;
            }
        }
    } else {
        const stepX = x2 > x1 ? 1 : -1;
        const stepY = y2 > y1 ? 1 : -1;

        let x = x1 + stepX;
        let y = y1 + stepY;
        while (!(x === x2 && y === y2)) {
            const st = cells.find(s => s.x === x && s.y === y);
            if (st.id || st.occupiedBy.some(x => x)) return false;
            x += stepX;
            y += stepY;
        }
    }

    return true;
}

function drawNewCard() {
    if (selectedStation !== undefined) {
        drawCircle(selectedStation.x, selectedStation.y, currentLine.color, 10);
        selectedStation = undefined;
    }
    
    if (round === 8) {
        cardButton.innerHTML = "Új kártya";
        cardButton.disabled = true;

        if (remainingLines.length === 0) {
            nextButton.disabled = true;
            roundH.innerHTML = `Játék vége`;

            calculateFinalPoints();
            displayFinalPoints();   
            saveResults();
        }

        calculateRoundPoints(currentLine);
        displayRoundPoints();
        startNewLine();

        return;
    }

    const remainingCards = cards.filter(c => !playedCards.includes(c));
    currentCard = remainingCards[Math.floor(Math.random() * remainingCards.length)];
    playedCards.push(currentCard);
    cardImg.src = `media/${currentCard.type}.svg`;

    roundH.innerHTML = `${round + 1}. kör`;
    round++;

    if (round === 8) cardButton.innerHTML = "Elvetés";
}

function startNewLine() {
    if (selectedStation !== undefined) {
        drawCircle(selectedStation.x, selectedStation.y, currentLine.color, 10);
        selectedStation = undefined;
    }

    currentLine = remainingLines.shift();

    if (remainingLines.length === 0) nextButton.disabled = true;

    playedCards = [];
    lineImg.src = `media/${currentLine.name}.png`;

    cardButton.disabled = false;
    round = 0;
    drawNewCard();
    displayRemainingLines();
}

function displayRemainingLines() {
    for (let i = 0; i < 3; i++) linesImg[i].src = remainingLines[i] !== undefined ? `media/${remainingLines[i].name}_small.png` : "";
}

function calculateRoundPoints(line) {
    const currentLineProgress = progress.find(x => x.line === line.id);
    const PK = currentLineProgress.districts.filter(x => x !== 0).length;
    const PM = Math.max(...currentLineProgress.districts);
    const PD = currentLineProgress.crossed_duna;
    const FP = PK * PM + PD;

    roundPoints[line.id][0] = PK;
    roundPoints[line.id][1] = PM;
    roundPoints[line.id][2] = PD;
    roundPoints[line.id][3] = FP;
}

function calculateFinalPoints() {
    const PP_points = [0, 1, 2, 4, 6, 8, 11, 14, 17, 21, 25];
    const trains = progress.map(x => x.train_stations).reduce((x, y) => x + y, 0);
    const CSP = cells.filter(c => c.id).map(x => x.occupiedBy);
    const CSP2 = CSP.filter(x => x.filter(Boolean).length === 2).length;
    const CSP3 = CSP.filter(x => x.filter(Boolean).length === 3).length;
    const CSP4 = CSP.filter(x => x.filter(Boolean).length === 4).length;

    for (let i = 0; i < 4; i++) finalPoints[0] += roundPoints[i][3];

    finalPoints[1] = PP_points[trains];
    finalPoints[2] = CSP2 * 2;
    finalPoints[3] = CSP3 * 5;
    finalPoints[4] = CSP4 * 9;
    
    for (let i = 0; i < 5; i++) finalPoints[5] += finalPoints[i];
}

function displayRoundPoints(reset) {
    for (let i = 0; i < 4; i++) {
        if (reset) rPointsP[i].innerHTML = "-";
        else rPointsP[i].innerHTML = roundPoints[currentLine.id][i];
    }
}

function displayFinalPoints(reset) {
    for (let i = 0; i < 6; i++) {
        if (reset) fPointsP[i].innerHTML = "-";  
        else fPointsP[i].innerHTML = finalPoints[i];
    }
}

function getResultsData() {
    const data = localStorage.getItem("data");
    return data ? JSON.parse(data) : [];
}

function saveResults() {
    if (finalPoints[5] === 0) return;
    data = getResultsData();

    data.push(
        {
            username: usernameInput.value ? usernameInput.value : "Vendég",
            points: finalPoints[5],
            time: stopwatchP.innerHTML
        }
    );

    localStorage.setItem("data", JSON.stringify(data));
}

function showResults() {
    const data = getResultsData();

    if (data.length === 0) resultsDiv.innerHTML = "Nincs elmentett eredmény!";
    else {
        data.sort((a, b) => b.points - a.points);
        resultsDiv.innerHTML = `<ol>${data.map(r => `<li><b>${r.points} pont</b> (${r.username}) - ${r.time}</li>`).join("")}</ol>`;
    }
}