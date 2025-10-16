const canvas = document.querySelector("#jatek");
const ctx = canvas.getContext("2d");

const madar = {
    x: 10,
    y: canvas.height / 2 - 30,
    width: 50,
    height: 30,
    ay: 300,
    vy: -300
};

let elozoIdo = 0;
const oszlopok = [];
const RES = 150;
const OSZLOP_SEBESSEG = -200;
const OSZLOP_TAVOLSAG = 300;
let vege = false;

function utkozik(a, b) {
    return !(
        b.y + b.height < a.y ||
        a.x + a.width < b.x ||
        a.y + a.height < b.y ||
        b.x + b.width < a.x
    );
}

function kirepul() {
    return madar.y < 0 - madar.height || madar.y > canvas.height + madar.height;
}

function random(a, b) {
    return Math.floor(Math.random() * (b - a + 1)) + a;
}

function jatekciklus(most = 0) {
    if (!vege) {
        requestAnimationFrame(jatekciklus);

        const dt = (most - elozoIdo) / 1000;

        elozoIdo = most;
        valtoztat(dt);
        rajzol();
    }
}

function valtoztat(dt) {
    madar.vy += madar.ay * dt;
    madar.y += madar.vy * dt;

    if (oszlopok.length > 20) {
        oszlopok.shift();
        oszlopok.shift();
    }

    const utolso = oszlopok[oszlopok.length - 1];

    if (!utolso || canvas.width - utolso.x > OSZLOP_TAVOLSAG) {
        const felsoOszlopMagassag = random(10, 300);
        oszlopok.push(
            {
                width: 50,
                height: felsoOszlopMagassag,
                x: canvas.width,
                y: 0
            },
            {
                width: 50,
                height: canvas.height - felsoOszlopMagassag - RES,
                x: canvas.width,
                y: felsoOszlopMagassag + RES
            }
        );
    }

    for (const oszlop of oszlopok) {
        oszlop.x += OSZLOP_SEBESSEG * dt;
        if (utkozik(madar, oszlop)) {
            vege = true;
        }
    }

    vege = kirepul();
}

function rajzol() {
    const bgImage = new Image();
    bgImage.src = "bg.png";
    ctx.drawImage(bgImage, 0, 0, canvas.width, canvas.height);

    const birdImage = new Image();
    birdImage.src = "bird.png";
    ctx.drawImage(birdImage, madar.x, madar.y, madar.width, madar.height);

    const columnImage = new Image();
    columnImage.src = "column.png";

    oszlopok.forEach((oszlop) => {
        ctx.drawImage(columnImage, oszlop.x, oszlop.y, oszlop.width, oszlop.height);
    });

    if (vege) {
        ctx.fillStyle = "red";
        ctx.font = "100px serif";
        ctx.fillText("Vége!", canvas.width / 2, canvas.height / 2);
    }
}

jatekciklus();

document.addEventListener("keydown", bill);

function bill(e) {
    madar.vy = -200;
}